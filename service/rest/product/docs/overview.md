# REST Product Service - Overview

## Purpose

Provides insurance product catalog management including creation, retrieval, update, and deletion of insurance products.

## Design

- Spring Boot Web application with `@RestController` endpoints
- In-memory storage using ConcurrentHashMap for thread-safe operations
- RESTful API following standard HTTP conventions
- Support for multiple insurance product types

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `Product` | Domain model representing an insurance product |
| `ProductType` | Enum for insurance product categories |
| `ProductStatus` | Enum for product availability status |
| `ProductService` | Business logic for product operations |
| `ProductController` | REST API endpoints |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
