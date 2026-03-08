# SOAP Sample Service - API

## WebServiceConfig

Configuration for SOAP web service infrastructure.

### Beans

| Bean | Type | Description |
|------|------|-------------|
| `messageDispatcherServlet` | ServletRegistrationBean | WS servlet at `/ws/*` |
| `defaultWsdl11Definition` | DefaultWsdl11Definition | WSDL generator |
| `sampleSchema` | XsdSchema | XSD schema loader |

### WSDL Configuration

- Port Type: `SamplePort`
- Target Namespace: `http://example.com/sample`
- Location URI: `/ws`

## SampleEndpoint

SOAP endpoint handling sample operations.

### Namespace

```java
public static final String NAMESPACE_URI = "http://example.com/sample"
```

### Operations

| Operation | Request | Response | Description |
|-----------|---------|----------|-------------|
| `getSample` | GetSampleRequest | GetSampleResponse | Retrieves sample by ID |

### Payload Mapping

```java
@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSampleRequest")
@ResponsePayload
public GetSampleResponse getSample(@RequestPayload GetSampleRequest request)
```

## GetSampleRequest

JAXB-annotated request model.

### Fields

| Field | Type | Description |
|-------|------|-------------|
| `id` | String | Sample identifier |

## GetSampleResponse

JAXB-annotated response model.

### Fields

| Field | Type | Description |
|-------|------|-------------|
| `id` | String | Sample identifier |
| `name` | String | Sample name |
| `active` | boolean | Active status |
