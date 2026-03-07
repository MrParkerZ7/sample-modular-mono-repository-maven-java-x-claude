# AWS S3 Module - Rules

## Error Handling

- All AWS SDK exceptions are caught and wrapped in `TechnicalException`
- `NoSuchKeyException` in `objectExists()` returns `false` (not an error)

## Usage Rules

1. Always close the `InputStream` returned by `getObject()`
2. Check `objectExists()` before `getObject()` if object might not exist
3. Use byte[] overload for binary content, String for text

## Thread Safety

- `S3ClientWrapper` is thread-safe if the underlying `S3Client` is thread-safe
- AWS SDK clients are typically thread-safe
