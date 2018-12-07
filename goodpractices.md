## Good Practices

As we mention in the introduction good practices are the key to avoid insults and be blamed by coworkers when they have to modify or debug your code. When you write java applications without frameworks you need a ton of architectural and design patterns in order to follow good practices and to write flexible code.

But who is the enemy?.. the coupling. Coupling is a two headed beast, one head appears when you have to add features on an existing code (even worst when the code is on prod) and the other is on the development process side, I mean if you tie the code you will tie the features and stories, tasks, etc.

Spring as many frameworks help us to avoid common mistakes in terms of design and good practices. Lets dive a little bit on some of the advantages.

### Avoid Boiler Plate Code

Getters, setters, builders, constructors, equals, hash code are the things that convert a class of 6 lines into 40 lines. OOP establish the encapsulation as a code concepts but that force us to make private the attributes and generate getters and setters to get or modify the attribute value. We can use libraries like [lombok](https://projectlombok.org/) to avoid all the repetitive code between dtos (data transfer objects). Remember the KISS principle.. "keep it simple simon!". Having a lot of lines is an anti-pattern.

### Error Handling (on rest)

Errors are a condition of the execution of something. In this case we use REST so we can user the HTTP status error mapping. Spring provides a standard way to handle exceptions and errors. Errors are very important in REST APIs, they inform consumers that something went wrong :)

Http Status codes are issued by a server in response to a client's request made to the server. You can find an explanation and the codes [here](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes). But retrieving a integer is not enough information from the client point of view.

In order to treat the errors in a well designed way you can read the following articles:
- [Article 1](https://dzone.com/articles/global-exception-handling-with-controlleradvice)
- [Article 2](https://www.baeldung.com/exception-handling-for-rest-with-spring)

### Cloud Computing
Currently IT is consuming a lot of IaaS (infrastructure as a service) or PaaS (platform as a service). In the 90's and early 2000's to create a webpage was a pain in the eye.. we had to buy hardware, connect it to internet, deal with DNS's service provider, hostnames, install the application server, reverse proxy, etc, etc.. trust me, it was a pain. Now we can buy a server with some clics and use the infrastructure magically appears. Lets see an intro with the following tutorial:

- [Tutorial](https://aws.amazon.com/getting-started/tutorials/launch-a-virtual-machine/)

### Cloud design first!
Design software for cloud has it own restrictions or rules, I mean you have to think about scalability by default. Scalability could be horizontal or vertical. Vertical scalability is when you add more resources to your servers like memory, disk, etc. Horizontal is a bit more complicated, suppose you have a SignUp service deployed on the cloud. That servce can be instanciated 10 times or 100 times depends on the amount of request. It means that automatically the cloud will instanciate more and more instances of a service and obviously shrink it back.
Horizontal scaling has some complexity associated, like service discovery or just the load balancer. Some tips to create services on the cloud are:
- Stateless: you must avoid state on the service to improve the ability of scale up and shrink it back. Commonly we have to move the state to shared memory (redis?) or just to avoid it.
- Easy to deploy: because the cloud can create several instances in some minutes you have to be sure that all the service start it up quickly and clean.
- Resilience: ussually when the shrink it back process start, the cloud solution sends a custom signal for each container. Do not trust on the cloud services.

Complete this goals is not easy. Some cloud platforms offer some services to achieve scalability and some others not. A good cloud application design generally follows the [twelve-factor](https://12factor.net/) methodology. In order to be more practical we wont dive into the twelve factors, instead of that we will explore some of them.

- **Factor II: Dependencies** Explicitly declare and isolate dependencies. (from [twelve-factor](https://12factor.net/)) A twelve-factor app never relies on implicit existence of system-wide packages. It declares all dependencies, completely and exactly, via a dependency declaration manifest. Furthermore, it uses a dependency isolation tool during execution to ensure that no implicit dependencies “leak in” from the surrounding system. The full and explicit dependency specification is applied uniformly to both production and development. This factor is not so important in golang like another programming languages, but obviously we can mess the design just trusting on the system or any other application, so.. if you have dependencies you should declare it explicitly using some tool ([dep](https://github.com/golang/dep), [glide](https://github.com/Masterminds/glide), etc). In golang the applications are (typically) deployed as a single binary but you may have to deal with external applications or event C libraries on the system, then the best option is just to prepare it on the deploy time and here is when docket appears to shine.

- **Factor III. Config**. Store config in the environment. (from [twelve-factor](https://12factor.net/)) An app’s config is everything that is likely to vary between deploys (staging, production, developer environments, etc). This includes:

    * Resource handles to the database, Memcached, and other backing services
    * Credentials to external services such as Amazon S3 or Twitter
    * Per-deploy values such as the canonical hostname for the deploy

    Apps sometimes store config as constants in the code. This is a violation of twelve-factor, which requires strict separation of config from code. Config varies substantially across deploys, code does not. A litmus test for whether an app has all config correctly factored out of the code is whether the codebase could be made open source at any moment, without compromising any credentials.

    Note that this definition of “config” does not include internal application config, such as config/routes.rb in Rails, or how code modules are connected in Spring. This type of config does not vary between deploys, and so is best done in the code.

    Another approach to config is the use of config files which are not checked into revision control, such as config/database.yml in Rails. This is a huge improvement over using constants which are checked into the code repo, but still has weaknesses: it’s easy to mistakenly check in a config file to the repo; there is a tendency for config files to be scattered about in different places and different formats, making it hard to see and manage all the config in one place. Further, these formats tend to be language- or framework-specific.

    **The twelve-factor app stores config in environment variables** (often shortened to env vars or env). Env vars are easy to change between deploys without changing any code; unlike config files, there is little chance of them being checked into the code repo accidentally; and unlike custom config files, or other config mechanisms such as Java System Properties, they are a language- and OS-agnostic standard.

- **Factor IV. Backing services**. Treat backing services as attached resources. (from [twelve-factor](https://12factor.net/)) A backing service is any service the app consumes over the network as part of its normal operation. Examples include datastores (such as MySQL or CouchDB), messaging/queueing systems (such as RabbitMQ or Beanstalkd), SMTP services for outbound email (such as Postfix), and caching systems (such as Memcached).

    Backing services like the database are traditionally managed by the same systems administrators as the app’s runtime deploy. In addition to these locally-managed services, the app may also have services provided and managed by third parties. Examples include SMTP services (such as Postmark), metrics-gathering services (such as New Relic or Loggly), binary asset services (such as Amazon S3), and even API-accessible consumer services (such as Twitter, Google Maps, or Last.fm).

    **The code for a twelve-factor app makes no distinction between local and third party services.** To the app, both are attached resources, accessed via a URL or other locator/credentials stored in the config. A deploy of the twelve-factor app should be able to swap out a local MySQL database with one managed by a third party (such as Amazon RDS) without any changes to the app’s code. Likewise, a local SMTP server could be swapped with a third-party SMTP service (such as Postmark) without code changes. In both cases, only the resource handle in the config needs to change.

    Each distinct backing service is a resource. For example, a MySQL database is a resource; two MySQL databases (used for sharding at the application layer) qualify as two distinct resources. The twelve-factor app treats these databases as attached resources, which indicates their loose coupling to the deploy they are attached to.

- **Factor VI. Processes** Execute the app as one or more stateless processes. (from [twelve-factor](https://12factor.net/)) The app is executed in the execution environment as one or more processes.

    In the simplest case, the code is a stand-alone script, the execution environment is a developer’s local laptop with an installed language runtime, and the process is launched via the command line (for example, python my_script.py). On the other end of the spectrum, a production deploy of a sophisticated app may use many process types, instantiated into zero or more running processes.

    Twelve-factor processes are stateless and share-nothing. Any data that needs to persist must be stored in a stateful backing service, typically a database.

    The memory space or filesystem of the process can be used as a brief, single-transaction cache. For example, downloading a large file, operating on it, and storing the results of the operation in the database. The twelve-factor app never assumes that anything cached in memory or on disk will be available on a future request or job – with many processes of each type running, chances are high that a future request will be served by a different process. Even when running only one process, a restart (triggered by code deploy, config change, or the execution environment relocating the process to a different physical location) will usually wipe out all local (e.g., memory and filesystem) state.

    Asset packagers like django-assetpackager use the filesystem as a cache for compiled assets. A twelve-factor app prefers to do this compiling during the build stage. Asset packagers such as Jammit and the Rails asset pipeline can be configured to package assets during the build stage.

    Some web systems rely on “sticky sessions” – that is, caching user session data in memory of the app’s process and expecting future requests from the same visitor to be routed to the same process. Sticky sessions are a violation of twelve-factor and should never be used or relied upon. Session state data is a good candidate for a datastore that offers time-expiration, such as Memcached or Redis.
    
- **Factor IX. Disposability**. (from [twelve-factor](https://12factor.net/)) Maximize robustness with fast startup and graceful shutdown. The twelve-factor app’s processes are disposable, meaning they can be started or stopped at a moment’s notice. This facilitates fast elastic scaling, rapid deployment of code or config changes, and robustness of production deploys.

    Processes should strive to minimize startup time. Ideally, a process takes a few seconds from the time the launch command is executed until the process is up and ready to receive requests or jobs. Short startup time provides more agility for the release process and scaling up; and it aids robustness, because the process manager can more easily move processes to new physical machines when warranted.

    Processes shut down gracefully when they receive a SIGTERM signal from the process manager. For a web process, graceful shutdown is achieved by ceasing to listen on the service port (thereby refusing any new requests), allowing any current requests to finish, and then exiting. Implicit in this model is that HTTP requests are short (no more than a few seconds), or in the case of long polling, the client should seamlessly attempt to reconnect when the connection is lost.

    For a worker process, graceful shutdown is achieved by returning the current job to the work queue. For example, on RabbitMQ the worker can send a NACK; on Beanstalkd, the job is returned to the queue automatically whenever a worker disconnects. Lock-based systems such as Delayed Job need to be sure to release their lock on the job record. Implicit in this model is that all jobs are reentrant, which typically is achieved by wrapping the results in a transaction, or making the operation idempotent.

    Processes should also be robust against sudden death, in the case of a failure in the underlying hardware. While this is a much less common occurrence than a graceful shutdown with SIGTERM, it can still happen. A recommended approach is use of a robust queueing backend, such as Beanstalkd, that returns jobs to the queue when clients disconnect or time out. Either way, a twelve-factor app is architected to handle unexpected, non-graceful terminations. Crash-only design takes this concept to its logical conclusion.

- **Factor XI. Logs**. Treat logs as event streams (from [twelve-factor](https://12factor.net/)) Logs provide visibility into the behavior of a running app. In server-based environments they are commonly written to a file on disk (a “logfile”); but this is only an output format.

    Logs are the stream of aggregated, time-ordered events collected from the output streams of all running processes and backing services. Logs in their raw form are typically a text format with one event per line (though backtraces from exceptions may span multiple lines). Logs have no fixed beginning or end, but flow continuously as long as the app is operating.

    A twelve-factor app never concerns itself with routing or storage of its output stream. It should not attempt to write to or manage logfiles. Instead, each running process writes its event stream, unbuffered, to stdout. During local development, the developer will view this stream in the foreground of their terminal to observe the app’s behavior.

    In staging or production deploys, each process’ stream will be captured by the execution environment, collated together with all other streams from the app, and routed to one or more final destinations for viewing and long-term archival. These archival destinations are not visible to or configurable by the app, and instead are completely managed by the execution environment. Open-source log routers (such as Logplex and Fluentd) are available for this purpose.

    The event stream for an app can be routed to a file, or watched via realtime tail in a terminal. Most significantly, the stream can be sent to a log indexing and analysis system such as Splunk, or a general-purpose data warehousing system such as Hadoop/Hive. These systems allow for great power and flexibility for introspecting an app’s behavior over time, including:

    * Finding specific events in the past.
    * Large-scale graphing of trends (such as requests per minute).
    * Active alerting according to user-defined heuristics (such as an alert when the quantity of errors per minute exceeds a certain threshold).

## What about micro-services?
Ok, up to now we know that golang is a modern and very performant language with some characteristics to achieve modern solutions veeeery easily. We know too about how to design a good cloud application or at least we have some clues to do it :D. Now we need an application architecture to use as a framework to work with and the buzz work micro-services appears.

Applications with large amount of developers will became very complex in the time, imagine lot of requirements from different business units with different priorities, with different life cycle, bug fixes, new developers appears and others leave the project or the company.. a mess of information, requirements and business times that **obviously** impact in the code.

Micro-services architecture propose to divide the whole application in several atomic and isolates units or services. In this way you can be focused on the life of each one instead on the whole system. Micro-services communicate each others using network protocols, are deployed separately.. think about it as a single program with external dependencies. For more information check [this article](https://martinfowler.com/articles/microservices.html) out.

This architecture is focused on the simplicity and because that is centered on the single units of work, but adds some complexity on the devops side, you can see this complexity when a service is scaled from one instance to ten, the rest of services needs to know about the new instances, how the instances should work when the instances are shrinking, etc. But trust me, for some applications the micro-services are the response and we will focus on them in this tutorial.

## Deploying micro-services
We say that micro-services are *micro* and isolated units then we can think that is easy to deploy vs the big monolith application and that is partially true. If you follow the twelve factors, you can deploy a micro-service and scale it horizontally very quickly as well as shrink the instances. Think about if you have different languages in your architecture, like python for login services and golang for the rest, that can start to be complex in any time. There.. the docker containers starts to shine again. You can build an image and upload it to amazon ecs and voilá, that solve several infrastructure and dev-ops issues. We will cover this later, but there is some other options like kubernetes, istio, apache mesos, docker-swarm and more.

## Inter micro services communication
So.. we said micro services are a self contained components of an architecture, they have self control and it owns rules, they encapsulate business logic in a very versatile way, we can later to have more than one version in production, etc.. but how can we interact from one service to another one?. Essentially we have two ways to communicate services, the synchronous and the asynchronous one. The synchronous sounds like we are coupling things and basically yes, but how wrong is the coupling?, the response depends on the implementation itself and take in account that the business process of an application has serialized operations and that are mainly represented as synchronous calls between micro services. The synchronous calls can be made using REST, gRPC, RPC or any other artifact.. in the extreme way could be a socket / web socket, usually people uses REST because is world wide known and easy to maintain, but if we can parallelize tasks?, like "I register my user in a platform, that platform creates a wallet, assign it to me and then give me 10 coins", in first place that platform is extremely stingy, second we can (depends on the business rules) create a sync call between user service and wallet service, and an async call between wallet service and virtual currency service. Remember that the example is tied to some business rules and I assumed that we use a kind of virtual currency. Back the to inter service communication, we can connect synchronously using REST and asynchronously using a queue like SQS or an event stream like kafka. We will receive eventually the money in our wallet, maybe 10 seconds later, or in some milliseconds, but we will receive that.

The learned lesson should be: *we must communicate the services depends on the business rules, some of then must be synchronous and others can be asynchronous*

What is the tradeoff?, if we use async inter service communication we are adding complexity, more components to maintain, more entropy on the logging and tracing of any action, etc, etc. Despite that.. you are working more like a real process and the response time is much better in some cases.

