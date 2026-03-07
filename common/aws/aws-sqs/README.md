# AWS SQS Module

Wrapper for AWS SQS (Simple Queue Service) operations.

## Quick Start

```java
SqsClient sqsClient = SqsClient.create();
SqsClientWrapper wrapper = new SqsClientWrapper(sqsClient);

// Send message
String messageId = wrapper.sendMessage("http://queue-url", "message body");

// Send with delay
String messageId = wrapper.sendMessage("http://queue-url", "message body", 10);

// Receive messages
List<Message> messages = wrapper.receiveMessages("http://queue-url", 10);

// Delete message
wrapper.deleteMessage("http://queue-url", message.receiptHandle());
```

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>aws-sqs</artifactId>
</dependency>
```
