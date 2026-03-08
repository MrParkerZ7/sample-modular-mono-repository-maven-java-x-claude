# SOAP Sample Service - Overview

## Purpose

Demonstrates a Spring Web Services (Spring-WS) SOAP endpoint with contract-first development using XSD schema.

## Design

- Contract-first approach with XSD schema definition
- Spring-WS `@Endpoint` annotation for SOAP handling
- JAXB for XML binding
- Auto-generated WSDL from XSD schema

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `SoapApplication` | Spring Boot entry point |
| `WebServiceConfig` | WSDL and servlet configuration |
| `SampleEndpoint` | SOAP request handler |
| `GetSampleRequest` | JAXB request model |
| `GetSampleResponse` | JAXB response model |

## Dependencies

- `spring-boot-starter-web-services` - Spring WS framework
- `wsdl4j` - WSDL generation
- `exception` - Exception handling
- `utils` - Utility functions
- `env` - Configuration management
