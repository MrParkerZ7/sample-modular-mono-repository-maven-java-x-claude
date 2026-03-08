# REST User Activity Service - Overview

## Purpose

Provides user activity tracking capabilities for logging and analyzing user actions within the system.

## Design

- Spring Boot Web application with `@RestController` endpoints
- In-memory storage using ConcurrentHashMap for thread-safe operations
- RESTful API following standard HTTP conventions
- Support for various activity types and metadata

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `UserActivity` | Domain model representing a user activity |
| `ActivityType` | Enum for activity categories |
| `UserActivityService` | Business logic for activity operations |
| `UserActivityController` | REST API endpoints |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
