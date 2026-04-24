# Student Result Management System (Spring Boot Microservices)

A secure and scalable Student Result Management System built using Spring Boot and microservices architecture. This system allows administrators to upload student results via Excel files, processes them using Apache POI, and stores the data in PostgreSQL with secure access using JWT authentication.

## Features

* Result upload using Excel files
* Excel processing with Apache POI
* Student result management (CRUD operations)
* JWT-based authentication and role-based authorization
* API Gateway for centralized routing
* Eureka Service Discovery for dynamic service registration
* Spring Cloud Config Server for centralized configuration
* Secure REST APIs using Spring Security

## Tech Stack

* Java
* Spring Boot
* Spring Cloud (Eureka, Config Server, API Gateway)
* Spring Security with JWT
* Apache POI
* PostgreSQL
* Maven

## Project Structure

StudentResultSystem/
├── auth-service
├── user-admin-service
├── api-gateway
├── config-server
├── eureka-server
├── README.md
└── .gitignore

## Architecture Overview

* API Gateway acts as the single entry point for all client requests
* Eureka Server handles service registration and discovery
* Config Server manages centralized configuration for all services
* Auth Service handles authentication and JWT generation
* Result Service manages student data and Excel processing
* Apache POI processes uploaded Excel files and stores data in PostgreSQL

## Getting Started

### Prerequisites

* Java 17 or higher
* Maven (or use Maven Wrapper)
* PostgreSQL
* Git

### Clone the Repository

git clone https://github.com/Doctor22d/StudentResultSystem.git
cd StudentResultSystem

### Run Services (order matters)

1. Start Eureka Server
2. Start Config Server
3. Start API Gateway
4. Start Auth Service
5. Start User/Admin Service

### Run using Maven Wrapper

./mvnw spring-boot:run

(On Windows)
mvnw.cmd spring-boot:run

## API Overview

* Authentication APIs (login/register with JWT)
* Admin APIs for uploading Excel results
* User APIs for viewing results

## Security

* JWT-based authentication
* Role-based access control (Admin/User)
* Secured endpoints using Spring Security

## Notes

* Do not commit sensitive data such as database credentials or secret keys
* Use environment variables or external configuration for secrets

## Future Improvements

* Add frontend (React/Angular)
* Add Docker support
* Improve validation and error handling
* Add reporting and analytics
