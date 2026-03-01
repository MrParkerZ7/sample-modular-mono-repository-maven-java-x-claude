package com.example.common.aws;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.common.exception.TechnicalException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@ExtendWith(MockitoExtension.class)
class SqsClientWrapperTest {

  @Mock private SqsClient sqsClient;

  private SqsClientWrapper wrapper;

  @BeforeEach
  void setUp() {
    wrapper = new SqsClientWrapper(sqsClient);
  }

  @Test
  void testSendMessage() {
    when(sqsClient.sendMessage(any(SendMessageRequest.class)))
        .thenReturn(SendMessageResponse.builder().messageId("msg-123").build());

    String messageId = wrapper.sendMessage("http://queue-url", "message body");

    assertEquals("msg-123", messageId);
    verify(sqsClient).sendMessage(any(SendMessageRequest.class));
  }

  @Test
  void testSendMessageWithDelay() {
    when(sqsClient.sendMessage(any(SendMessageRequest.class)))
        .thenReturn(SendMessageResponse.builder().messageId("msg-456").build());

    String messageId = wrapper.sendMessage("http://queue-url", "message body", 10);

    assertEquals("msg-456", messageId);
    verify(sqsClient).sendMessage(any(SendMessageRequest.class));
  }

  @Test
  void testSendMessageThrowsException() {
    when(sqsClient.sendMessage(any(SendMessageRequest.class)))
        .thenThrow(new RuntimeException("SQS error"));

    assertThrows(
        TechnicalException.class, () -> wrapper.sendMessage("http://queue-url", "message body"));
  }

  @Test
  void testSendMessageWithDelayThrowsException() {
    when(sqsClient.sendMessage(any(SendMessageRequest.class)))
        .thenThrow(new RuntimeException("SQS error"));

    assertThrows(
        TechnicalException.class,
        () -> wrapper.sendMessage("http://queue-url", "message body", 10));
  }

  @Test
  void testReceiveMessages() {
    Message message = Message.builder().messageId("msg-789").body("test body").build();
    when(sqsClient.receiveMessage(any(ReceiveMessageRequest.class)))
        .thenReturn(ReceiveMessageResponse.builder().messages(message).build());

    List<Message> messages = wrapper.receiveMessages("http://queue-url", 10);

    assertEquals(1, messages.size());
    assertEquals("msg-789", messages.get(0).messageId());
    verify(sqsClient).receiveMessage(any(ReceiveMessageRequest.class));
  }

  @Test
  void testReceiveMessagesThrowsException() {
    when(sqsClient.receiveMessage(any(ReceiveMessageRequest.class)))
        .thenThrow(new RuntimeException("SQS error"));

    assertThrows(TechnicalException.class, () -> wrapper.receiveMessages("http://queue-url", 10));
  }

  @Test
  void testDeleteMessage() {
    wrapper.deleteMessage("http://queue-url", "receipt-handle");

    verify(sqsClient).deleteMessage(any(DeleteMessageRequest.class));
  }

  @Test
  void testDeleteMessageThrowsException() {
    doThrow(new RuntimeException("SQS error"))
        .when(sqsClient)
        .deleteMessage(any(DeleteMessageRequest.class));

    assertThrows(
        TechnicalException.class,
        () -> wrapper.deleteMessage("http://queue-url", "receipt-handle"));
  }
}
