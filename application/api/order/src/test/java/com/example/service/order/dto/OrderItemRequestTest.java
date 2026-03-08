package com.example.service.order.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderItemRequestTest {

  @Test
  void testDefaultConstructor() {
    OrderItemRequest request = new OrderItemRequest();
    assertNull(request.getProductId());
    assertNull(request.getProductName());
    assertNull(request.getQuantity());
    assertNull(request.getUnitPrice());
  }

  @Test
  void testFullConstructor() {
    OrderItemRequest request =
        new OrderItemRequest("prod-123", "Test Product", 2, new BigDecimal("99.99"));

    assertEquals("prod-123", request.getProductId());
    assertEquals("Test Product", request.getProductName());
    assertEquals(2, request.getQuantity());
    assertEquals(new BigDecimal("99.99"), request.getUnitPrice());
  }

  @Test
  void testSetters() {
    OrderItemRequest request = new OrderItemRequest();

    request.setProductId("prod-456");
    request.setProductName("Another Product");
    request.setQuantity(3);
    request.setUnitPrice(new BigDecimal("49.99"));

    assertEquals("prod-456", request.getProductId());
    assertEquals("Another Product", request.getProductName());
    assertEquals(3, request.getQuantity());
    assertEquals(new BigDecimal("49.99"), request.getUnitPrice());
  }
}
