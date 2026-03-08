# REST Product Service - API

## ProductController

Product catalog management endpoints.

### Endpoints

| Method | Path | Request Body | Response | Description |
|--------|------|--------------|----------|-------------|
| GET | `/api/products` | - | List of products | List all products |
| GET | `/api/products/{id}` | - | Product JSON | Get product by ID |
| GET | `/api/products/code/{code}` | - | Product JSON | Get product by code |
| GET | `/api/products/type/{type}` | - | List of products | Get products by type |
| POST | `/api/products` | CreateProductRequest | Product JSON | Create new product |
| PUT | `/api/products/{id}` | UpdateProductRequest | Product JSON | Update product |
| DELETE | `/api/products/{id}` | - | 204 No Content | Delete product |

### Request/Response Formats

#### Product Response

```json
{
  "id": "uuid-string",
  "code": "LIFE-001",
  "name": "Basic Life Insurance",
  "description": "Entry-level life insurance product",
  "productType": "LIFE_INSURANCE",
  "status": "ACTIVE",
  "basePrice": 99.99,
  "currency": "USD",
  "termMonths": 12,
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

#### CreateProductRequest

```json
{
  "code": "LIFE-001",
  "name": "Basic Life Insurance",
  "description": "Entry-level life insurance product",
  "productType": "LIFE_INSURANCE",
  "basePrice": 99.99,
  "currency": "USD",
  "termMonths": 12
}
```

#### UpdateProductRequest

```json
{
  "name": "Updated Life Insurance",
  "description": "Updated description",
  "status": "INACTIVE",
  "basePrice": 149.99,
  "termMonths": 24
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String (UUID) | Product identifier |
| `code` | String | Product code |
| `type` | ProductType | Product type filter |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 201 | Created |
| 204 | Deleted |
| 400 | Invalid request |
| 404 | Product not found |
| 409 | Product code already exists |
| 500 | Internal server error |
