# REST User Profile Service - Rules

## Validation Rules

### Email
- Required for user creation
- Must be unique across all users
- Must be valid email format

### Name Fields
- First name: Required, max 100 characters
- Last name: Required, max 100 characters

### Phone Number
- Optional
- Should include country code when provided

## Business Rules

1. New users are created with PENDING_VERIFICATION status by default
2. Email cannot be changed after creation
3. Deleted users are permanently removed
4. Status transitions are validated (e.g., cannot reactivate SUSPENDED users directly)

## Status Transitions

| From | To | Allowed |
|------|----|---------|
| PENDING_VERIFICATION | ACTIVE | Yes |
| PENDING_VERIFICATION | SUSPENDED | Yes |
| ACTIVE | INACTIVE | Yes |
| ACTIVE | SUSPENDED | Yes |
| INACTIVE | ACTIVE | Yes |
| SUSPENDED | INACTIVE | Yes |

## API Conventions

- Base path: `/api/users`
- Use UUIDs for user identifiers
- Timestamps in ISO 8601 format
- All responses in JSON format

## Thread Safety

- Service uses ConcurrentHashMap for thread-safe operations
- Controllers are stateless and thread-safe
