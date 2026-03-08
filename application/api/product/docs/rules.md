# REST Product Service - Rules

## Validation Rules

### Product Code
- Required for product creation
- Must be unique across all products
- Format: alphanumeric with hyphens (e.g., LIFE-001)

### Name
- Required, max 200 characters

### Price
- Base price must be positive
- Currency must be valid ISO 4217 code

### Term
- Term in months must be positive integer

## Business Rules

1. New products are created with COMING_SOON status by default
2. Product code cannot be changed after creation
3. Deleted products are permanently removed
4. Only ACTIVE products should be offered to customers

## Product Types

| Type | Description |
|------|-------------|
| LIFE_INSURANCE | Life coverage products |
| HEALTH_INSURANCE | Health and medical coverage |
| AUTO_INSURANCE | Vehicle insurance |
| HOME_INSURANCE | Property and home coverage |
| TRAVEL_INSURANCE | Travel protection plans |

## Status Values

| Status | Description |
|--------|-------------|
| ACTIVE | Available for purchase |
| INACTIVE | Temporarily unavailable |
| DISCONTINUED | No longer offered |
| COMING_SOON | Future product |

## API Conventions

- Base path: `/api/products`
- Use UUIDs for product identifiers
- Timestamps in ISO 8601 format
- Prices in decimal format

## Thread Safety

- Service uses ConcurrentHashMap for thread-safe operations
- Controllers are stateless and thread-safe
