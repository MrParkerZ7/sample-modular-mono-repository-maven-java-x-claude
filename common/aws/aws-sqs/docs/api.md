# AWS SQS Module - API

## SqsClientWrapper

### Constructor

```java
public SqsClientWrapper(SqsClient sqsClient)
```

### Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `sendMessage` | queueUrl, messageBody | String (messageId) | Send message |
| `sendMessage` | queueUrl, messageBody, delaySeconds | String (messageId) | Send with delay |
| `receiveMessages` | queueUrl, maxMessages | List<Message> | Receive messages |
| `deleteMessage` | queueUrl, receiptHandle | void | Delete message |

### Error Codes

| Code | When |
|------|------|
| `SQS_SEND_ERROR` | Failed to send message |
| `SQS_RECEIVE_ERROR` | Failed to receive messages |
| `SQS_DELETE_ERROR` | Failed to delete message |
