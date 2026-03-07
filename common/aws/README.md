# AWS Modules

Modular AWS SDK wrappers. Each module provides a focused wrapper for a single AWS service.

## Modules

| Module | AWS Service | Dependency |
|--------|-------------|------------|
| `aws-s3` | S3 (Simple Storage Service) | `software.amazon.awssdk:s3` |
| `aws-sqs` | SQS (Simple Queue Service) | `software.amazon.awssdk:sqs` |
| `aws-dynamodb` | DynamoDB | `software.amazon.awssdk:dynamodb` |

## Usage

Only depend on the modules you need:

```xml
<!-- If you only need S3 -->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-s3</artifactId>
</dependency>

<!-- If you need S3 and SQS -->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-s3</artifactId>
</dependency>
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-sqs</artifactId>
</dependency>
```

## Structure

```
aws/
├── aws-s3/           # S3 operations
│   ├── src/
│   ├── tests/
│   └── docs/
├── aws-sqs/          # SQS operations
│   ├── src/
│   ├── tests/
│   └── docs/
└── aws-dynamodb/     # DynamoDB operations
    ├── src/
    ├── tests/
    └── docs/
```

## Benefits

- **Minimal dependencies**: Only pull in the AWS SDK modules you need
- **Smaller artifacts**: Each service wrapper is a separate JAR
- **Clear boundaries**: Each module has a single responsibility
