# AWS SQS Module - Rules

## Error Handling

- All AWS SDK exceptions are caught and wrapped in `TechnicalException`

## Usage Rules

1. Always delete messages after successful processing
2. Use delay parameter for scheduled/deferred processing
3. `maxMessages` is capped at 10 by AWS SQS

## Message Processing Pattern

```java
List<Message> messages = wrapper.receiveMessages(queueUrl, 10);
for (Message message : messages) {
    try {
        process(message.body());
        wrapper.deleteMessage(queueUrl, message.receiptHandle());
    } catch (Exception e) {
        // Message will become visible again after visibility timeout
    }
}
```

## Thread Safety

- `SqsClientWrapper` is thread-safe if the underlying `SqsClient` is thread-safe
