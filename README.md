# Project Cosmos Backend API

## Setup

- Ensure latest Java SDK is installed (along with a valid JRE of course).
- Install postgres, and set up an appropriate database. Along with a password, and proper port (I use pgadmin4 for database management)
- Once all that is prepared, navigate to src\main\resources and create a file named `application.properties`.
- Fill it with the following, replace all <> with your own values

```properties
spring.application.name=api

spring.datasource.url=jdbc:postgresql://localhost:<port>/<database-name>
spring.datasource.username=<username> (default: postgres)
spring.datasource.password=<password>

# This lets Spring Boot create/update tables automatically from entities
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Put all REST endpoints under /api
spring.data.rest.base-path=/api

server.port=<preferred port to work on>
```

- Run it your preferred method, I use visual studio code's built in spring boot runner which handles it for me. You should navigate to localhost:<server.port>/api and should see some json. Play around with the endpoints if you'd like to.

## What is Project Cosmos
In short, it's a space idle game built for the browser (and maybe an app one day) that mimics certain games like Eve Online. Allowing you the ability to purchase ships, sell resources, and build a corporation with many fleets. 

The main difference between Eve and this game, is that you'll be able to do all content by yourself, because you will be able to command many fleets, and large ones at that, by yourself.

This is the backend, which will handle all server side requests.