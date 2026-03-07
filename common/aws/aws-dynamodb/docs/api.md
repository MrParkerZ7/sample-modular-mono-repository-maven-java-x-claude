# AWS DynamoDB Module - API

## DynamoDbClientWrapper

### Constructor

```java
public DynamoDbClientWrapper(DynamoDbClient dynamoDbClient)
```

### Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `putItem` | tableName, item | void | Insert/update item |
| `getItem` | tableName, key | Map<String, AttributeValue> | Get item by key |
| `deleteItem` | tableName, key | void | Delete item |
| `itemExists` | tableName, key | boolean | Check if item exists |

### Error Codes

| Code | When |
|------|------|
| `DYNAMODB_PUT_ERROR` | Failed to put item |
| `DYNAMODB_GET_ERROR` | Failed to get item |
| `DYNAMODB_DELETE_ERROR` | Failed to delete item |
