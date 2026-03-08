# OAuth Server - Overview

## Purpose

Central authentication and authorization server for all services. Issues JWT tokens that resource servers validate.

## Design

- Spring Authorization Server with in-memory client and user stores
- RSA-signed JWT tokens
- OIDC discovery enabled
- Supports client_credentials (service-to-service) and authorization_code (user-facing) grant types

## Architecture

```
Client -> OAuth Server (9000) -> JWT Token
Client -> REST Service (8080+) -> Validates JWT via JWKS endpoint
```
