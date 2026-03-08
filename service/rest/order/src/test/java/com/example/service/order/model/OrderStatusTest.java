package com.example.service.order.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderStatusTest {

  @Test
  void testEnumValues() {
    OrderStatus[] values = OrderStatus.values();
    assertEquals(6, values.length);
    assertEquals(OrderStatus.PENDING, OrderStatus.valueOf("PENDING"));
    assertEquals(OrderStatus.CONFIRMED, OrderStatus.valueOf("CONFIRMED"));
    assertEquals(OrderStatus.PROCESSING, OrderStatus.valueOf("PROCESSING"));
    assertEquals(OrderStatus.COMPLETED, OrderStatus.valueOf("COMPLETED"));
    assertEquals(OrderStatus.CANCELLED, OrderStatus.valueOf("CANCELLED"));
    assertEquals(OrderStatus.REFUNDED, OrderStatus.valueOf("REFUNDED"));
  }
}
