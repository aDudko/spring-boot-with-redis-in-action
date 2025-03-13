# Spring Boot with Redis in Action

**Example of object caching in Redis for Spring Boot applications**

## Overview

The application is designed as an internal microservice with no public access to it and no user interface.

Data is passed to the service via a POST request. The data can then be read via a GET request. In the current version,
the service does not provide a method to remove data from Redis - this is the intention.

It is also expected that the data must be signed. In our simplified case, the signature is a string in the format “key:
value”, for example, you can use “login:password” or anything you choose.

## Technologies

- `Java` - version `21`
- `Maven` - for building the application
- `Jackson` - for working with JSON
- `Spring Boot` - version `3.4.3`
- `Spring Cloud` - version `2023.0.3`
- `Spring Boot Actuator` - it's for real-world applications
- `Docker` - containerization
- `Docker-Compose` - infrastructure

## Structure of the project

```
spring-boot-with-redis-in-action/
├── compose.yml                     # docker-compose file
├── src/main/
|   ├── java/com/dudko/example
|   |   ├── config/
|   |   ├── controller/             # domain level of requests and controllers
|   |   ├── domain/                 # persistent domain level and repositories
|   |   ├── model/                  # service level of the domain, used in business logic
|   |   ├── service/                # business logic
|   ├── resources/                  # configs
├── pom.xml                         # artifact of Maven
├── postman_collection.json         # collection of requests for Postman
```

## How to try this project?

```sh
docker-compose -f compose.yml up
```

### Author:
Anatoly Dudko