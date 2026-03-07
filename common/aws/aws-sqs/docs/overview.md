# AWS SQS Module - Overview

## Purpose

Provides a simplified wrapper around AWS SQS SDK operations with consistent error handling.

## Design

- Wraps `software.amazon.awssdk.services.sqs.SqsClient`
- Translates all AWS SDK exceptions to `TechnicalException`
- Provides simple methods for common SQS operations

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `SqsClientWrapper` | Wraps SqsClient with simplified API |

## Dependencies

- `common-exception` - For TechnicalException
- `software.amazon.awssdk:sqs` - AWS SQS SDK
