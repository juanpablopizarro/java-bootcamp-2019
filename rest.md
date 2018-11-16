## First week: The Mission.. REST (cont)
Before to create a REST service we will add an important subject here: Spring & Spring Boot. Spring is a framework that helps to Java (and not just Java) developers to develop production grade applications removing complexity adding a lot of artifacts that helps developers to go ahead quickly to the business logic instead adding third party libraries. Spring boot is another framework that runs on top of spring and helps developers to get all the stuff configured and running out of the box.

From spring.io website:
>**The Spring Framework** provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform.
>A key element of Spring is infrastructural support at the application level: Spring focuses on the "plumbing" of enterprise applications so that teams can focus on application-level business logic, without unnecessary ties to specific deployment environments.
>
>**Spring Boot** is a brand new framework from the team at Pivotal, designed to simplify the bootstrapping and development of a new **Spring** application. The framework takes an opinionated approach to configuration, freeing developers from the need to define boilerplate configuration.

#### Spring boot 'Hello World' application
To follow the traditions and the **Brian Kernighan** steps (he was the author of 'hello world'), we will follow the next tutorial to run the hello world for spring boot:

- [Spring Boot Getting Started](https://spring.io/guides/gs/spring-boot/)

Complete the previous tutorial up to "Run the application" (included). Completing that tutorial you can invoke a service on the http://localhost:8080. Now we have an intro about Spring Boot, what about REST and spring?. Follow this tutorial:

- [Spring REST](https://spring.io/guides/gs/rest-service/)

Oh yeah.. now we have out first REST service.. but we are not easy going boys, we want a production level service!. Let's see actuator tutorial:

- [Spring Actuator](https://spring.io/guides/gs/actuator-service/) (let's skip the Test your application section)

To summarize, we create a spring boot application, then we create a spring rest application and finally we create a spring boot rest application using actuator. In all cases we skip the test section, we will dive on testing later, now lets focus on the service and API.

All good but what if we want to share our service with the world?, we have to share the way to use our service (the API documentation). Exists some tools to document a REST API, we will use swagger. Swagger is not a Hollywood actor, is a suit that enable us to write API specifications, you can fund it in this [link](https://swagger.io/). Do not install anything yet. As we said, swagger is used to document API specs, but how it looks like?, you can see it in action on this other [link](https://petstore.swagger.io). As you can see, swagger separates all the endpoints calls and the descriptions of its params as well as how the response looks like. Here you have the swagger tutorial:

- [Swagger Tutorial](https://swagger.io/tools/open-source/getting-started/) (just read the concepts)

And you can go directly with the online editor:

- [Swagger Online Editor](http://editor.swagger.io) (play with it)

Now let's the magic begin. In order to configure some automatic swagger 2 config we can follow this excellent article from baeldung:

- [Swagger 2 - Baeldung Article](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

### Coding Time!
![coder](./docs/img/coder.gif)

Let's set the objective first: "we want to create a REST API using actuator in order to manage shopping carts and products. DO NOT create a great shopping cart model, just make it simple. When you finish it, upload to some repo and share with us the repo. Important: we wont use any database to persist data, just add it to a in-memory repo.