## Second week: This is all about Persistence! (cont)
In order to create our persistence layer is important to introduce a new concept: Java Persistence API (JPA). JPA is a java specification for mapping Java objects to the tables of relational databases. JPA is just a specification and it can't perform persistence or anything else by itself. JPA is only a set of interfaces and requires an implementation. Implementations of this specification don't only handle the code to interacts with the database, but also map the data to objects structures used by the application. You can go further following this link:

- [JPA API docs](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm)

Fortunately for most of developers Spring comes with several features to integrate persistence easily. In this case we are going to present Spring Data JPA

From spring.io website
>**Spring Data JPA** provides repository support for the Java Persistence API (JPA). It eases development of applications that need to access JPA data sources. 
>
>The goal of the **Spring Data** repository abstraction is to significantly reduce the amount of boilerplate code required to implement data access layers for various persistence stores.

So now that we have a few core concepts we can get our hands dirty..

- [Spring Data JPA Getting Started](https://spring.io/guides/gs/accessing-data-jpa/)

Completing that tutorial you will see how easily we can to persist our model. 

Another important point is to chose the Database engine that we are going to use. In the tutorial H2 is used (you can see the dependency added in the pom file).

- [H2 DB official web page](http://www.h2database.com/html/main.html)

We recommend on this bootcamp to use H2 or any other embedded database just for simplification. Maybe you want to take a look also to HSQLDB

- [HSQLDB official web page](http://hsqldb.org/)

### Coding Time!
![coder](./docs/img/happy-coding.gif)


Before adding persistence to our application we are going to extends a little bit our model. Until now we have Carts and Products. It would be great to have information about the users that are using our application. So we need to extends our shopping-cart application (rest API and data model) to be able to manage carts, products, and users. After that we need to add a persistence layer so all this information can be stored on a DB.