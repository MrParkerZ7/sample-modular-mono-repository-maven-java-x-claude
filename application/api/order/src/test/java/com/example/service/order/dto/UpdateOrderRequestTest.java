package com.example.service.order.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class UpdateOrderRequestTest {

  @Test
  void testDefaultConstructor() {
    UpdateOrderRequest request = new UpdateOrderRequest();
    assertNull(request.getItems());
  }

  @Test
  void testFullConstructor() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    UpdateOrderRequest request = new UpdateOrderRequest(items);

    assertEquals(items, request.getItems());
    assertEquals(1, request.getItems().size());
  }

  @Test
  void testSetters() {
    UpdateOrderRequest request = new UpdateOrderRequest();
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-2", "Product 2", 2, new BigDecimal("50.00")));

    request.setItems(items);

    assertEquals(items, request.getItems());
  }
}
