package com.example.service.order.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CreateOrderRequestTest {

  @Test
  void testDefaultConstructor() {
    CreateOrderRequest request = new CreateOrderRequest();
    assertNull(request.getUserId());
    assertNull(request.getItems());
  }

  @Test
  void testFullConstructor() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    CreateOrderRequest request = new CreateOrderRequest("user-123", items);

    assertEquals("user-123", request.getUserId());
    assertEquals(items, request.getItems());
    assertEquals(1, request.getItems().size());
  }

  @Test
  void testSetters() {
    CreateOrderRequest request = new CreateOrderRequest();
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-2", "Product 2", 2, new BigDecimal("50.00")));

    request.setUserId("user-456");
    request.setItems(items);

    assertEquals("user-456", request.getUserId());
    assertEquals(items, request.getItems());
  }
}
