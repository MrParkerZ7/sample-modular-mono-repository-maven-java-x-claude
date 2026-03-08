# REST User Activity Service - Rules

## Validation Rules

### User ID
- Required for activity creation
- Must reference a valid user

### Activity Type
- Required, must be a valid ActivityType enum value

### Resource Path
- Optional, max 500 characters
- Should be a valid URL path

### Metadata
- Optional key-value pairs
- Used for additional context

## Business Rules

1. Activities are immutable after creation (no updates)
2. Timestamp is automatically set on creation
3. Activities can be deleted for compliance reasons
4. User ID is required but not validated against user service

## Activity Types

| Type | Description |
|------|-------------|
| LOGIN | User authentication |
| LOGOUT | User sign out |
| PAGE_VIEW | Page navigation |
| CLICK | UI interaction |
| FORM_SUBMIT | Form submission |
| DOWNLOAD | File download |

## API Conventions

- Base path: `/api/activities`
- Use UUIDs for activity identifiers
- Timestamps in ISO 8601 format
- All responses in JSON format

## Thread Safety

- Service uses ConcurrentHashMap for thread-safe operations
- Controllers are stateless and thread-safe
