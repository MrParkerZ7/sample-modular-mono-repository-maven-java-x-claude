# REST Sample Service - API

## HealthController

Health check endpoint for monitoring.

### Endpoints

| Method | Path | Response | Description |
|--------|------|----------|-------------|
| GET | `/api/health` | `{"status": "UP"}` | Health status |

### Response Format

```json
{
  "status": "UP"
}
```

## SampleController

Sample resource endpoint demonstrating REST patterns.

### Endpoints

| Method | Path | Response | Description |
|--------|------|----------|-------------|
| GET | `/api/samples/{id}` | Sample JSON | Get sample by ID |

### Response Format

```json
{
  "id": "123",
  "name": "Sample 123",
  "active": true
}
```

### Path Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| `id` | String | Sample identifier |

## Error Responses

| Status | When |
|--------|------|
| 200 | Success |
| 404 | Resource not found |
| 500 | Internal server error |
