# AWS DynamoDB Module - Rules

## Error Handling

- All AWS SDK exceptions are caught and wrapped in `TechnicalException`
- `itemExists()` returns false for non-existent items (not an error)

## Usage Rules

1. Key must contain partition key (and sort key if defined)
2. `putItem` overwrites existing items with same key
3. `getItem` returns null/empty map if item doesn't exist

## Item Structure

```java
Map<String, AttributeValue> item = Map.of(
    "pk", AttributeValue.builder().s("partition-key").build(),
    "sk", AttributeValue.builder().s("sort-key").build(),
    "data", AttributeValue.builder().s("value").build()
);
```

## Thread Safety

- `DynamoDbClientWrapper` is thread-safe if the underlying `DynamoDbClient` is thread-safe
