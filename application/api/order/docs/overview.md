# REST Order Service - Overview

## Purpose

Provides order processing capabilities including creation, retrieval, update, and cancellation of orders.

## Design

- Spring Boot Web application with `@RestController` endpoints
- In-memory storage using ConcurrentHashMap for thread-safe operations
- RESTful API following standard HTTP conventions
- Support for order items and status management

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `Order` | Domain model representing an order |
| `OrderItem` | Domain model representing an order line item |
| `OrderStatus` | Enum for order status |
| `OrderService` | Business logic for order operations |
| `OrderController` | REST API endpoints |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
