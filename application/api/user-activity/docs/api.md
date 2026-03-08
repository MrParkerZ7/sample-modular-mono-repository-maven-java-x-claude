# REST User Activity Service - API

## UserActivityController

User activity tracking endpoints.

### Endpoints

| Method | Path | Request Body | Response | Description |
|--------|------|--------------|----------|-------------|
| GET | `/api/activities` | - | List of activities | List all activities |
| GET | `/api/activities/{id}` | - | Activity JSON | Get activity by ID |
| GET | `/api/activities/user/{userId}` | - | List of activities | Get activities by user |
| POST | `/api/activities` | CreateActivityRequest | Activity JSON | Create new activity |
| DELETE | `/api/activities/{id}` | - | 204 No Content | Delete activity |

### Request/Response Formats

#### UserActivity Response

```json
{
  "id": "uuid-string",
  "userId": "user-123",
  "activityType": "PAGE_VIEW",
  "resourcePath": "/dashboard",
  "metadata": {"key": "value"},
  "timestamp": "2024-01-01T00:00:00Z",
  "ipAddress": "192.168.1.1",
  "userAgent": "Mozilla/5.0..."
}
```

#### CreateActivityRequest

```json
{
  "userId": "user-123",
  "activityType": "PAGE_VIEW",
  "resourcePath": "/dashboard",
  "metadata": {"key": "value"},
  "ipAddress": "192.168.1.1",
  "userAgent": "Mozilla/5.0..."
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String (UUID) | Activity identifier |
| `userId` | String | User identifier |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 201 | Created |
| 204 | Deleted |
| 400 | Invalid request |
| 404 | Activity not found |
| 500 | Internal server error |
