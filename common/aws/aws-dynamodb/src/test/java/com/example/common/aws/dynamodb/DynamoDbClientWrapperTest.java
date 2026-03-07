package com.example.common.aws.dynamodb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.common.exception.TechnicalException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@ExtendWith(MockitoExtension.class)
class DynamoDbClientWrapperTest {

  @Mock private DynamoDbClient dynamoDbClient;

  private DynamoDbClientWrapper wrapper;

  @BeforeEach
  void setUp() {
    wrapper = new DynamoDbClientWrapper(dynamoDbClient);
  }

  @Test
  void testPutItem() {
    Map<String, AttributeValue> item = new HashMap<>();
    item.put("id", AttributeValue.builder().s("123").build());

    wrapper.putItem("table", item);

    verify(dynamoDbClient).putItem(any(PutItemRequest.class));
  }

  @Test
  void testPutItemThrowsException() {
    when(dynamoDbClient.putItem(any(PutItemRequest.class)))
        .thenThrow(new RuntimeException("DynamoDB error"));

    Map<String, AttributeValue> item = new HashMap<>();
    item.put("id", AttributeValue.builder().s("123").build());

    assertThrows(TechnicalException.class, () -> wrapper.putItem("table", item));
  }

  @Test
  void testGetItem() {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    Map<String, AttributeValue> responseItem = new HashMap<>();
    responseItem.put("id", AttributeValue.builder().s("123").build());
    responseItem.put("name", AttributeValue.builder().s("test").build());

    when(dynamoDbClient.getItem(any(GetItemRequest.class)))
        .thenReturn(GetItemResponse.builder().item(responseItem).build());

    Map<String, AttributeValue> result = wrapper.getItem("table", key);

    assertEquals("123", result.get("id").s());
    assertEquals("test", result.get("name").s());
    verify(dynamoDbClient).getItem(any(GetItemRequest.class));
  }

  @Test
  void testGetItemThrowsException() {
    when(dynamoDbClient.getItem(any(GetItemRequest.class)))
        .thenThrow(new RuntimeException("DynamoDB error"));

    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    assertThrows(TechnicalException.class, () -> wrapper.getItem("table", key));
  }

  @Test
  void testDeleteItem() {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    wrapper.deleteItem("table", key);

    verify(dynamoDbClient).deleteItem(any(DeleteItemRequest.class));
  }

  @Test
  void testDeleteItemThrowsException() {
    doThrow(new RuntimeException("DynamoDB error"))
        .when(dynamoDbClient)
        .deleteItem(any(DeleteItemRequest.class));

    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    assertThrows(TechnicalException.class, () -> wrapper.deleteItem("table", key));
  }

  @Test
  void testItemExistsTrue() {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    Map<String, AttributeValue> responseItem = new HashMap<>();
    responseItem.put("id", AttributeValue.builder().s("123").build());

    when(dynamoDbClient.getItem(any(GetItemRequest.class)))
        .thenReturn(GetItemResponse.builder().item(responseItem).build());

    assertTrue(wrapper.itemExists("table", key));
  }

  @Test
  void testItemExistsFalseEmptyItem() {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    when(dynamoDbClient.getItem(any(GetItemRequest.class)))
        .thenReturn(GetItemResponse.builder().item(new HashMap<>()).build());

    assertFalse(wrapper.itemExists("table", key));
  }

  @Test
  void testItemExistsFalseNullItem() {
    Map<String, AttributeValue> key = new HashMap<>();
    key.put("id", AttributeValue.builder().s("123").build());

    when(dynamoDbClient.getItem(any(GetItemRequest.class)))
        .thenReturn(GetItemResponse.builder().build());

    assertFalse(wrapper.itemExists("table", key));
  }
}
