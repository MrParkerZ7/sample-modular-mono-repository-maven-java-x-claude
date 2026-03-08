package com.example.service.order.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  void testDefaultConstructor() {
    Order order = new Order();
    assertNull(order.getId());
    assertNull(order.getOrderNumber());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    List<OrderItem> items = new ArrayList<>();
    items.add(
        new OrderItem(
            "item-1",
            "prod-1",
            "Product 1",
            1,
            new BigDecimal("100.00"),
            new BigDecimal("100.00")));

    Order order =
        new Order(
            "order-123",
            "ORD-2024-00001",
            "user-456",
            items,
            new BigDecimal("100.00"),
            new BigDecimal("8.50"),
            new BigDecimal("108.50"),
            OrderStatus.PENDING,
            now,
            now);

    assertEquals("order-123", order.getId());
    assertEquals("ORD-2024-00001", order.getOrderNumber());
    assertEquals("user-456", order.getUserId());
    assertEquals(items, order.getItems());
    assertEquals(new BigDecimal("100.00"), order.getSubtotal());
    assertEquals(new BigDecimal("8.50"), order.getTax());
    assertEquals(new BigDecimal("108.50"), order.getTotal());
    assertEquals(OrderStatus.PENDING, order.getStatus());
    assertEquals(now, order.getCreatedAt());
    assertEquals(now, order.getUpdatedAt());
  }

  @Test
  void testSetters() {
    Order order = new Order();
    Instant now = Instant.now();
    List<OrderItem> items = new ArrayList<>();

    order.setId("order-789");
    order.setOrderNumber("ORD-2024-00002");
    order.setUserId("user-123");
    order.setItems(items);
    order.setSubtotal(new BigDecimal("200.00"));
    order.setTax(new BigDecimal("17.00"));
    order.setTotal(new BigDecimal("217.00"));
    order.setStatus(OrderStatus.CONFIRMED);
    order.setCreatedAt(now);
    order.setUpdatedAt(now);

    assertEquals("order-789", order.getId());
    assertEquals("ORD-2024-00002", order.getOrderNumber());
    assertEquals("user-123", order.getUserId());
    assertEquals(items, order.getItems());
    assertEquals(new BigDecimal("200.00"), order.getSubtotal());
    assertEquals(new BigDecimal("17.00"), order.getTax());
    assertEquals(new BigDecimal("217.00"), order.getTotal());
    assertEquals(OrderStatus.CONFIRMED, order.getStatus());
    assertEquals(now, order.getCreatedAt());
    assertEquals(now, order.getUpdatedAt());
  }

  @Test
  void testEqualsAndHashCode() {
    Order order1 = new Order();
    order1.setId("same-id");

    Order order2 = new Order();
    order2.setId("same-id");

    Order order3 = new Order();
    order3.setId("different-id");

    assertEquals(order1, order2);
    assertEquals(order1.hashCode(), order2.hashCode());
    assertNotEquals(order1, order3);
    assertNotEquals(order1, null);
    assertNotEquals(order1, new Object());
    assertEquals(order1, order1);
  }
}
