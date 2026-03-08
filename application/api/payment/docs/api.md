# REST Payment Service - API

## PaymentController

Payment processing endpoints.

### Endpoints

| Method | Path | Request Body | Response | Description |
|--------|------|--------------|----------|-------------|
| GET | `/api/payments` | - | List of payments | List all payments |
| GET | `/api/payments/{id}` | - | Payment JSON | Get payment by ID |
| GET | `/api/payments/transaction/{transactionId}` | - | Payment JSON | Get by transaction ID |
| GET | `/api/payments/order/{orderId}` | - | List of payments | Get payments by order |
| GET | `/api/payments/user/{userId}` | - | List of payments | Get payments by user |
| POST | `/api/payments` | CreatePaymentRequest | Payment JSON | Process payment |
| POST | `/api/payments/{id}/refund` | - | Payment JSON | Refund payment |
| DELETE | `/api/payments/{id}` | - | 204 No Content | Cancel payment |

### Request/Response Formats

#### Payment Response

```json
{
  "id": "uuid-string",
  "transactionId": "TXN-2024-00001",
  "orderId": "order-123",
  "userId": "user-456",
  "amount": 108.50,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD",
  "status": "COMPLETED",
  "failureReason": null,
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

#### CreatePaymentRequest

```json
{
  "orderId": "order-123",
  "userId": "user-456",
  "amount": 108.50,
  "currency": "USD",
  "paymentMethod": "CREDIT_CARD"
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String (UUID) | Payment identifier |
| `transactionId` | String | Transaction identifier |
| `orderId` | String | Order identifier |
| `userId` | String | User identifier |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 201 | Created |
| 204 | Deleted |
| 400 | Invalid request |
| 404 | Payment not found |
| 500 | Internal server error |
