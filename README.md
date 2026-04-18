# E-Commerce Backend System

## Overview
This is the backend repository for the E-commerce Web Application developed by Nhom 5/6 - TMDT. The system is built using modern Java technologies, providing a scalable and robust API for managing e-commerce operations. It handles everything from user authentication to product schemas, order processing, and shopping cart management.

This project uses a microservices-inspired modular architecture, separating concerns like authentication, cart computation, order processing, and integrating via Redis and MySQL. It also prepares to connect to external microservices such as Python-based recommendation engines via Docker Compose.

## Technology Stack
- Language: Java 21
- Framework: Spring Boot 3.2.5
- Database: MySQL (Data persistence via Spring Data JPA / Hibernate)
- Caching/Session: Redis (Spring Data Redis)
- Security: Spring Security, OAuth2 Client / Resource Server, JWT Handling
- Real-time Communication: Spring Boot WebSocket
- Email Service: Spring Boot Mail
- Data Mapping: MapStruct
- Utilities: Lombok, Validation, Jackson Databind

## Core Modules & Features

### Authentication and Authorization
- Full implementation of JWT-based authentication.
- OAuth2 social login integration.
- Custom security filters, request interceptors, and robust role-based access control (RBAC).

### Product and Inventory Management
- Product catalog schema mapping.
- Inventory tracking and validation.

### Cart and Order Management
- Shopping cart functionality.
- Order processing system from checkout to invoice generation.
- Order status tracking.

### Real-time Features
- WebSocket integration for real-time notifications and chat functionalities.

### Notification Service
- Automated email sending module for order confirmations and security alerts.

## Getting Started

### Prerequisites
- JDK21
- Maven
- MySQL Server
- Redis Server
- Docker & Docker Compose (for full environment deployment)

### Local Development Setup
1. Clone the repository.
2. Update the `application.properties` or `.env` file with your local MySQL and Redis credentials, along with OAuth2 API keys.
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   Or via the generated jar file:
   ```bash
   java -jar target/EcommerceProjectBackend-0.0.1-SNAPSHOT.jar
   ```

### Deployment
This application is container-ready. Use the provided Docker and Docker Compose files to spin up the entire cluster (Java backend, MySQL, Redis, Elasticsearch, and the Recommendation Microservice) in a production or staging environment.

## Authors
- taivstech / Nhom 5-6 TMDT
