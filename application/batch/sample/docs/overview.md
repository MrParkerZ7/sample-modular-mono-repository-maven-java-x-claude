# Batch Sample Service - Overview

## Purpose

Demonstrates Spring Batch job structure with a simple tasklet-based job for batch processing operations.

## Design

- Spring Boot application with Spring Batch integration
- Job configuration using Spring's fluent builder API
- Tasklet-based step for simple batch operations
- HSQLDB for job repository (in-memory)

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `BatchApplication` | Spring Boot entry point |
| `SampleJobConfig` | Job and step configuration |
| `SampleTasklet` | Batch processing logic |

## Dependencies

- `spring-boot-starter-batch` - Spring Batch framework
- `hsqldb` - In-memory job repository
- `exception` - Exception handling
- `utils` - Utility functions
- `env` - Configuration management
