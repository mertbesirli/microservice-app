# Microservice App
<a name="readme-top"></a>

[![MIT License][license-shield]][license-url]
[![Java Platform](https://img.shields.io/badge/platform-Java-blue.svg)](https://docs.oracle.com/en/java/)
[![REST Architecture](https://img.shields.io/badge/architecture-REST-5DADE2.svg)](http://www.vogella.com/tutorials/REST/article.html)
[![Spring Boot Framework](https://img.shields.io/badge/framework-Spring%20Boot-brightgreen.svg)](https://projects.spring.io/spring-boot/)

This is a proof-of-concept application, which demonstrates Microservice Architecture Pattern using Spring Boot, Spring Cloud and Docker.

## Requirements

- Java JDK 17
- Docker Desktop

### Before you start
- Install Docker and Docker Compose
- docker-compose -f ./docs/docker-compose.yml up -d

### Important Endpoints
- http://localhost:8080 - API Gateway
- http://localhost:8761 - Eureka Dashboard

## Functional Services
App was decomposed into three core microservices. All of them are independently deployable applications, organized around certain business domains.

![Screenshot_5](https://user-images.githubusercontent.com/43350594/213780623-60cf337f-b075-407e-8c61-b69ee933e2b9.png)

### Product Service
Contains product id, name, description and price.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
GET	| /api/product	| Get all product data
POST	| /api/product	| Create new product


The port number is set to 0, so it will be assigned randomly. The reason for this is to allow parallel operation. Accessible via API gateway with http://localhost:8080/api/product


### Order Service
Contains order number and orderlineitems list.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
POST	| /api/order	| Create new order


Accessible via API gateway with http://localhost:8080/api/order

### Inventory Service
Contains  skucode and quantity. Indicates the product type and how many of the products are in stock.

Method	| Path	| Description
------------- | ------------------------- | ------------- 
GET	| /api/inventory	| Get all stock product items

Accessible via API gateway with http://localhost:8080/api/inventory


### API Gateway

As you can see, there are three core services, which expose external API to client. In a real-world systems, this number can grow very quickly as well as whole system complexity. Actually, hundreds of services might be involved in rendering of one complex webpage.

In theory, a client could make requests to each of the microservices directly. But obviously, there are challenges and limitations with this option, like necessity to know all endpoints addresses, perform http request for each peace of information separately, merge the result on a client side. Another problem is non web-friendly protocols which might be used on the backend.

Usually a much better approach is to use API Gateway. It is a single entry point into the system, used to handle requests by routing them to the appropriate backend service or by invoking multiple backend services and aggregating the results.  Also, it can be used for authentication, insights, stress and canary testing, service migration, static response handling, active traffic management.


### Eureka Server

Another commonly known architecture pattern is Service discovery. It allows automatic detection of network locations for service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.

The key part of Service discovery is Registry. I use Netflix Eureka in this project. Eureka is a good example of the client-side discovery pattern, when client is responsible for determining locations of available service instances (using Registry server) and load balancing requests across them.

With Spring Boot, you can easily build Eureka Registry with spring-cloud-starter-eureka-server dependency, @EnableEurekaServer annotation and simple configuration properties.

Also, Eureka provides a simple interface, where you can track running services and a number of available instances: http://localhost:8761


### Circuit Breaker

Resilience4j is the implementation of Circuit Breaker pattern, which gives a control over latency and failure from dependencies accessed over the network. The main idea is to stop cascading failures in a distributed environment with a large number of microservices. That helps to fail fast and recover as soon as possible - important aspects of fault-tolerant systems that self-heal.

## Distributed Tracing

Analyzing problems in distributed systems can be difficult, for example, tracing requests that propagate from one microservice to another. It can be quite a challenge to try to find out how a request travels through the system, especially if you don't have any insight into the implementation of a microservice. Even when there is logging, it is hard to tell which action correlates to a single request.

Spring Cloud Sleuth solves this problem by providing support for distributed tracing. It adds two types of IDs to the logging: traceId and spanId. The spanId represents a basic unit of work, for example sending an HTTP request. The traceId contains a set of spans forming a tree-like structure. For example, with a distributed big-data store, a trace might be formed by a PUT request. Using traceId and spanId for each operation we know when and where our application is as it processes a request, making reading our logs much easier.

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.md` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


[license-shield]: https://img.shields.io/badge/license-MIT%20License-green.svg
[license-url]: https://github.com/mertbesirli/microservice-app/blob/main/LICENSE
