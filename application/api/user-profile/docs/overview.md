# REST User Profile Service - Overview

## Purpose

Provides user profile management capabilities including creation, retrieval, update, and deletion of user profiles.

## Design

- Spring Boot Web application with `@RestController` endpoints
- In-memory storage using ConcurrentHashMap for thread-safe operations
- RESTful API following standard HTTP conventions
- DTOs for request/response separation from domain model

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `UserProfile` | Domain model representing a user profile |
| `UserStatus` | Enum for user account status |
| `UserProfileService` | Business logic for user operations |
| `UserProfileController` | REST API endpoints |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
