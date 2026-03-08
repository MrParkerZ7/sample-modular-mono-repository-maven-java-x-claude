# REST Payment Service - Overview

## Purpose

Provides payment processing capabilities including processing payments, refunds, and payment status management.

## Design

- Spring Boot Web application with `@RestController` endpoints
- In-memory storage using ConcurrentHashMap for thread-safe operations
- RESTful API following standard HTTP conventions
- Support for multiple payment methods and statuses

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `Payment` | Domain model representing a payment |
| `PaymentMethod` | Enum for payment methods |
| `PaymentStatus` | Enum for payment status |
| `PaymentService` | Business logic for payment operations |
| `PaymentController` | REST API endpoints |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
