# REST Sample Service - Overview

## Purpose

Demonstrates a Spring Boot REST API structure with health check and sample CRUD endpoint patterns.

## Design

- Spring Boot Web application with `@RestController` endpoints
- Standard REST conventions with JSON responses
- Health endpoint for monitoring and load balancer checks
- Sample controller demonstrating path variables and response structure

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `Application` | Spring Boot entry point |
| `HealthController` | Health check endpoint at `/api/health` |
| `SampleController` | Sample CRUD endpoint at `/api/samples` |

## Dependencies

- `spring-boot-starter-web` - Spring Web MVC
- `spring-boot-starter-actuator` - Monitoring endpoints
- `exception` - Exception handling
- `env` - Configuration management
