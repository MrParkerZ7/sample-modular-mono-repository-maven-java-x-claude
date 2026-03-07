# AWS DynamoDB Module - Overview

## Purpose

Provides a simplified wrapper around AWS DynamoDB SDK operations with consistent error handling.

## Design

- Wraps `software.amazon.awssdk.services.dynamodb.DynamoDbClient`
- Translates all AWS SDK exceptions to `TechnicalException`
- Provides simple methods for common DynamoDB operations

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `DynamoDbClientWrapper` | Wraps DynamoDbClient with simplified API |

## Dependencies

- `common-exception` - For TechnicalException
- `software.amazon.awssdk:dynamodb` - AWS DynamoDB SDK
