# REST Sample Service - Rules

## Error Handling

- Business errors return appropriate HTTP status codes (400, 404)
- Technical errors return 500 with error details
- All responses use consistent JSON structure

## Usage Rules

1. Health endpoint should return quickly (no database calls)
2. Use path variables for resource identifiers
3. Return appropriate HTTP status codes
4. Include Content-Type header in responses

## API Conventions

- Base path: `/api`
- Resource paths: `/api/{resource}`
- Use plural nouns for collections: `/api/samples`
- Use HTTP methods for operations (GET, POST, PUT, DELETE)

## Thread Safety

- Controllers are stateless and thread-safe
- Spring manages request handling concurrently
- No mutable shared state in controllers
