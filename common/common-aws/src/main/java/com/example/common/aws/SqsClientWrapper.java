package com.example.common.aws;

import com.example.common.exception.TechnicalException;
import java.util.List;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

/** Wrapper for AWS SQS operations. */
public class SqsClientWrapper {

  private final SqsClient sqsClient;

  public SqsClientWrapper(SqsClient sqsClient) {
    this.sqsClient = sqsClient;
  }

  public String sendMessage(String queueUrl, String messageBody) {
    try {
      SendMessageRequest request =
          SendMessageRequest.builder().queueUrl(queueUrl).messageBody(messageBody).build();
      SendMessageResponse response = sqsClient.sendMessage(request);
      return response.messageId();
    } catch (Exception e) {
      throw new TechnicalException("SQS_SEND_ERROR", "Failed to send message to SQS", e);
    }
  }

  public String sendMessage(String queueUrl, String messageBody, int delaySeconds) {
    try {
      SendMessageRequest request =
          SendMessageRequest.builder()
              .queueUrl(queueUrl)
              .messageBody(messageBody)
              .delaySeconds(delaySeconds)
              .build();
      SendMessageResponse response = sqsClient.sendMessage(request);
      return response.messageId();
    } catch (Exception e) {
      throw new TechnicalException("SQS_SEND_ERROR", "Failed to send message to SQS", e);
    }
  }

  public List<Message> receiveMessages(String queueUrl, int maxMessages) {
    try {
      ReceiveMessageRequest request =
          ReceiveMessageRequest.builder()
              .queueUrl(queueUrl)
              .maxNumberOfMessages(maxMessages)
              .build();
      return sqsClient.receiveMessage(request).messages();
    } catch (Exception e) {
      throw new TechnicalException("SQS_RECEIVE_ERROR", "Failed to receive messages from SQS", e);
    }
  }

  public void deleteMessage(String queueUrl, String receiptHandle) {
    try {
      DeleteMessageRequest request =
          DeleteMessageRequest.builder().queueUrl(queueUrl).receiptHandle(receiptHandle).build();
      sqsClient.deleteMessage(request);
    } catch (Exception e) {
      throw new TechnicalException("SQS_DELETE_ERROR", "Failed to delete message from SQS", e);
    }
  }
}
