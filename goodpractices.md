## Good Practices

As we mention in the introduction good practices are the key to avoid insults and be blamed by coworkers when they have to modify or debug your code. When you write java applications without frameworks you need a ton of architectural and design patterns in order to follow good practices and to write flexible code.

But who is the enemy?.. the coupling. Coupling is a two headed beast, one head appears when you have to add features on an existing code (even worst when the code is on prod) and the other is on the development process side, I mean if you tie the code you will tie the features and stories, tasks, etc.

Spring as many frameworks help us to avoid common mistakes in terms of design and good practices. Lets dive a little bit on some of the advantages.

### Avoid Boiler Plate Code
Getters, setters, builders, constructors, equals, hash code are the things that convert a class of 6 lines into 40 lines. OOP establish the encapsulation as a code concepts but that force us to make private the attributes and generate getters and setters to get or modify the attribute value. We can use libraries like [lombok](https://projectlombok.org/) to avoid all the repetitive code between dtos (data transfer objects). Remember the KISS principle.. "keep it simple simon!". Having a lot of lines is an anti-pattern.

### Error Handling (on rest)
Errors are a condition of the execution of something. In this case we use REST so we can user the HTTP status error mapping.

