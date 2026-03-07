# AWS S3 Module - Overview

## Purpose

Provides a simplified wrapper around AWS S3 SDK operations with consistent error handling.

## Design

- Wraps `software.amazon.awssdk.services.s3.S3Client`
- Translates all AWS SDK exceptions to `TechnicalException`
- Provides simple methods for common S3 operations

## Key Classes

| Class | Responsibility |
|-------|----------------|
| `S3ClientWrapper` | Wraps S3Client with simplified API |

## Dependencies

- `common-exception` - For TechnicalException
- `software.amazon.awssdk:s3` - AWS S3 SDK
