# Endorsement Service - API

## Endpoints

### List all endorsements
```
GET /api/endorsements -> 200 List<Endorsement>
```

### Get by ID
```
GET /api/endorsements/{id} -> 200 Endorsement | 404
```

### Get by endorsement number
```
GET /api/endorsements/number/{endorsementNumber} -> 200 Endorsement | 404
```

### Get by order
```
GET /api/endorsements/order/{orderId} -> 200 List<Endorsement>
```

### Get by user
```
GET /api/endorsements/user/{userId} -> 200 List<Endorsement>
```

### Create endorsement
```
POST /api/endorsements -> 201 Endorsement
Body: CreateEndorsementRequest
```

### Approve
```
POST /api/endorsements/{id}/approve -> 200 Endorsement | 404
```

### Reject
```
POST /api/endorsements/{id}/reject?reason={reason} -> 200 Endorsement | 404
```

### Apply
```
POST /api/endorsements/{id}/apply -> 200 Endorsement | 404
```

### Delete
```
DELETE /api/endorsements/{id} -> 204 | 404
```
