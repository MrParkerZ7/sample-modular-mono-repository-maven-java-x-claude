# AWS DynamoDB Module

Wrapper for AWS DynamoDB operations.

## Quick Start

```java
DynamoDbClient dynamoDbClient = DynamoDbClient.create();
DynamoDbClientWrapper wrapper = new DynamoDbClientWrapper(dynamoDbClient);

// Put item
Map<String, AttributeValue> item = Map.of(
    "id", AttributeValue.builder().s("123").build(),
    "name", AttributeValue.builder().s("test").build()
);
wrapper.putItem("table-name", item);

// Get item
Map<String, AttributeValue> key = Map.of(
    "id", AttributeValue.builder().s("123").build()
);
Map<String, AttributeValue> result = wrapper.getItem("table-name", key);

// Check existence
boolean exists = wrapper.itemExists("table-name", key);

// Delete item
wrapper.deleteItem("table-name", key);
```

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-dynamodb</artifactId>
</dependency>
```
