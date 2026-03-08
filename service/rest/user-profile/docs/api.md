# REST User Profile Service - API

## UserProfileController

User profile management endpoints.

### Endpoints

| Method | Path | Request Body | Response | Description |
|--------|------|--------------|----------|-------------|
| GET | `/api/users` | - | List of users | List all users |
| GET | `/api/users/{id}` | - | User JSON | Get user by ID |
| GET | `/api/users/email/{email}` | - | User JSON | Get user by email |
| POST | `/api/users` | CreateUserRequest | User JSON | Create new user |
| PUT | `/api/users/{id}` | UpdateUserRequest | User JSON | Update user |
| DELETE | `/api/users/{id}` | - | 204 No Content | Delete user |

### Request/Response Formats

#### UserProfile Response

```json
{
  "id": "uuid-string",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "status": "ACTIVE",
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

#### CreateUserRequest

```json
{
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890"
}
```

#### UpdateUserRequest

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "status": "ACTIVE"
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String (UUID) | User identifier |
| `email` | String | User email address |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 201 | Created |
| 204 | Deleted |
| 400 | Invalid request |
| 404 | User not found |
| 409 | Email already exists |
| 500 | Internal server error |
