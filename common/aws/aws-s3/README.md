# AWS S3 Module

Wrapper for AWS S3 (Simple Storage Service) operations.

## Quick Start

```java
S3Client s3Client = S3Client.create();
S3ClientWrapper wrapper = new S3ClientWrapper(s3Client);

// Upload
wrapper.putObject("bucket", "key", "content");

// Download
InputStream stream = wrapper.getObject("bucket", "key");

// Check existence
boolean exists = wrapper.objectExists("bucket", "key");

// Delete
wrapper.deleteObject("bucket", "key");
```

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-s3</artifactId>
</dependency>
```
