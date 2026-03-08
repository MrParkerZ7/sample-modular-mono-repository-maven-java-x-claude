# REST Order Service - API

## OrderController

Order processing endpoints.

### Endpoints

| Method | Path | Request Body | Response | Description |
|--------|------|--------------|----------|-------------|
| GET | `/api/orders` | - | List of orders | List all orders |
| GET | `/api/orders/{id}` | - | Order JSON | Get order by ID |
| GET | `/api/orders/number/{orderNumber}` | - | Order JSON | Get order by number |
| GET | `/api/orders/user/{userId}` | - | List of orders | Get orders by user |
| POST | `/api/orders` | CreateOrderRequest | Order JSON | Create new order |
| PUT | `/api/orders/{id}` | UpdateOrderRequest | Order JSON | Update order |
| PUT | `/api/orders/{id}/status/{status}` | - | Order JSON | Update status |
| DELETE | `/api/orders/{id}` | - | 204 No Content | Cancel order |

### Request/Response Formats

#### Order Response

```json
{
  "id": "uuid-string",
  "orderNumber": "ORD-2024-001",
  "userId": "user-123",
  "items": [
    {
      "id": "item-uuid",
      "productId": "prod-123",
      "productName": "Basic Life Insurance",
      "quantity": 1,
      "unitPrice": 99.99,
      "totalPrice": 99.99
    }
  ],
  "subtotal": 99.99,
  "tax": 8.50,
  "total": 108.49,
  "status": "PENDING",
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

#### CreateOrderRequest

```json
{
  "userId": "user-123",
  "items": [
    {
      "productId": "prod-123",
      "productName": "Basic Life Insurance",
      "quantity": 1,
      "unitPrice": 99.99
    }
  ]
}
```

#### UpdateOrderRequest

```json
{
  "items": [
    {
      "productId": "prod-123",
      "productName": "Updated Product",
      "quantity": 2,
      "unitPrice": 99.99
    }
  ]
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String (UUID) | Order identifier |
| `orderNumber` | String | Order number |
| `userId` | String | User identifier |
| `status` | OrderStatus | New status |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 201 | Created |
| 204 | Deleted |
| 400 | Invalid request |
| 404 | Order not found |
| 500 | Internal server error |
