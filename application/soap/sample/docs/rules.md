# SOAP Sample Service - Rules

## Error Handling

- SOAP faults for business errors
- Standard SOAP fault structure for exceptions
- Log all errors with correlation ID

## Usage Rules

1. Use contract-first development (XSD first, then generate)
2. Validate requests against XSD schema
3. Use meaningful namespace URIs
4. Keep WSDL accessible for clients

## SOAP Conventions

- Namespace: `http://example.com/sample`
- Request suffix: `Request` (e.g., `GetSampleRequest`)
- Response suffix: `Response` (e.g., `GetSampleResponse`)
- WSDL path: `/ws/sample.wsdl`

## XSD Schema

- Located at: `classpath:xsd/sample.xsd`
- Defines request and response types
- Used for WSDL generation and validation

## Thread Safety

- Endpoints are stateless and thread-safe
- Spring manages concurrent request handling
- JAXB marshalling is thread-safe
