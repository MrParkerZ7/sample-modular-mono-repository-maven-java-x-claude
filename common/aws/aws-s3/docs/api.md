# AWS S3 Module - API

## S3ClientWrapper

### Constructor

```java
public S3ClientWrapper(S3Client s3Client)
```

### Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `putObject` | bucket, key, content (String) | void | Upload string content |
| `putObject` | bucket, key, content (byte[]) | void | Upload binary content |
| `getObject` | bucket, key | InputStream | Download object |
| `deleteObject` | bucket, key | void | Delete object |
| `objectExists` | bucket, key | boolean | Check if object exists |

### Error Codes

| Code | When |
|------|------|
| `S3_PUT_ERROR` | Failed to upload object |
| `S3_GET_ERROR` | Failed to download object |
| `S3_DELETE_ERROR` | Failed to delete object |
| `S3_HEAD_ERROR` | Failed to check object existence |
