# REST Payment Service - Rules

## Validation Rules

### Payment
- Order ID is required
- User ID is required
- Amount must be positive
- Currency must be valid ISO 4217 code
- Payment method is required

## Business Rules

1. New payments are created with PENDING status
2. Transaction ID is auto-generated
3. Only PENDING payments can be cancelled
4. Only COMPLETED payments can be refunded
5. Refunded payments cannot be refunded again

## Payment Methods

| Method | Description |
|--------|-------------|
| CREDIT_CARD | Credit card payment |
| DEBIT_CARD | Debit card payment |
| BANK_TRANSFER | Bank transfer |
| PAYPAL | PayPal payment |
| CRYPTO | Cryptocurrency payment |

## Payment Status Values

| Status | Description |
|--------|-------------|
| PENDING | Payment awaiting processing |
| PROCESSING | Payment being processed |
| COMPLETED | Payment successful |
| FAILED | Payment failed |
| REFUNDED | Payment refunded |
| CANCELLED | Payment cancelled |

## Status Transitions

| From | To | Allowed |
|------|----|---------|
| PENDING | PROCESSING | Yes |
| PENDING | CANCELLED | Yes |
| PROCESSING | COMPLETED | Yes |
| PROCESSING | FAILED | Yes |
| COMPLETED | REFUNDED | Yes |
| FAILED | - | No |
| REFUNDED | - | No |
| CANCELLED | - | No |

## API Conventions

- Base path: `/api/payments`
- Use UUIDs for payment identifiers
- Timestamps in ISO 8601 format
- Amounts in decimal format

## Thread Safety

- Service uses ConcurrentHashMap for thread-safe operations
- Controllers are stateless and thread-safe
