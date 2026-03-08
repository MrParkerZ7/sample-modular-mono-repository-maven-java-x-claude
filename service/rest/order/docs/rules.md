# REST Order Service - Rules

## Validation Rules

### Order
- User ID is required
- At least one order item is required
- Order number is auto-generated

### Order Items
- Product ID is required
- Product name is required
- Quantity must be positive
- Unit price must be non-negative

## Business Rules

1. New orders are created with PENDING status
2. Order number is auto-generated with format ORD-YYYY-XXXXX
3. Subtotal is calculated from item totals
4. Tax is calculated at a fixed rate (8.5%)
5. Cancelled orders cannot be modified
6. Completed orders cannot be cancelled

## Status Transitions

| From | To | Allowed |
|------|----|---------|
| PENDING | CONFIRMED | Yes |
| PENDING | CANCELLED | Yes |
| CONFIRMED | PROCESSING | Yes |
| CONFIRMED | CANCELLED | Yes |
| PROCESSING | COMPLETED | Yes |
| PROCESSING | CANCELLED | Yes |
| COMPLETED | REFUNDED | Yes |
| CANCELLED | - | No |
| REFUNDED | - | No |

## Order Status Values

| Status | Description |
|--------|-------------|
| PENDING | Order awaiting confirmation |
| CONFIRMED | Order confirmed |
| PROCESSING | Order being processed |
| COMPLETED | Order fulfilled |
| CANCELLED | Order cancelled |
| REFUNDED | Order refunded |

## API Conventions

- Base path: `/api/orders`
- Use UUIDs for order identifiers
- Timestamps in ISO 8601 format
- Prices in decimal format

## Thread Safety

- Service uses ConcurrentHashMap for thread-safe operations
- Controllers are stateless and thread-safe
