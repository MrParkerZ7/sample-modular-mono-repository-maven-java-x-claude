package com.example.common.aws.dynamodb;

import com.example.common.exception.TechnicalException;
import java.util.Map;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

/** Wrapper for AWS DynamoDB operations. */
public class DynamoDbClientWrapper {

  private final DynamoDbClient dynamoDbClient;

  public DynamoDbClientWrapper(DynamoDbClient dynamoDbClient) {
    this.dynamoDbClient = dynamoDbClient;
  }

  public void putItem(String tableName, Map<String, AttributeValue> item) {
    try {
      PutItemRequest request = PutItemRequest.builder().tableName(tableName).item(item).build();
      dynamoDbClient.putItem(request);
    } catch (Exception e) {
      throw new TechnicalException("DYNAMODB_PUT_ERROR", "Failed to put item to DynamoDB", e);
    }
  }

  public Map<String, AttributeValue> getItem(String tableName, Map<String, AttributeValue> key) {
    try {
      GetItemRequest request = GetItemRequest.builder().tableName(tableName).key(key).build();
      GetItemResponse response = dynamoDbClient.getItem(request);
      return response.item();
    } catch (Exception e) {
      throw new TechnicalException("DYNAMODB_GET_ERROR", "Failed to get item from DynamoDB", e);
    }
  }

  public void deleteItem(String tableName, Map<String, AttributeValue> key) {
    try {
      DeleteItemRequest request = DeleteItemRequest.builder().tableName(tableName).key(key).build();
      dynamoDbClient.deleteItem(request);
    } catch (Exception e) {
      throw new TechnicalException(
          "DYNAMODB_DELETE_ERROR", "Failed to delete item from DynamoDB", e);
    }
  }

  public boolean itemExists(String tableName, Map<String, AttributeValue> key) {
    Map<String, AttributeValue> item = getItem(tableName, key);
    return item != null && !item.isEmpty();
  }
}
