package com.example.service.order.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class OrderItemTest {

  @Test
  void testDefaultConstructor() {
    OrderItem item = new OrderItem();
    assertNull(item.getId());
    assertNull(item.getProductId());
  }

  @Test
  void testFullConstructor() {
    OrderItem item =
        new OrderItem(
            "item-123",
            "prod-456",
            "Test Product",
            2,
            new BigDecimal("99.99"),
            new BigDecimal("199.98"));

    assertEquals("item-123", item.getId());
    assertEquals("prod-456", item.getProductId());
    assertEquals("Test Product", item.getProductName());
    assertEquals(2, item.getQuantity());
    assertEquals(new BigDecimal("99.99"), item.getUnitPrice());
    assertEquals(new BigDecimal("199.98"), item.getTotalPrice());
  }

  @Test
  void testSetters() {
    OrderItem item = new OrderItem();

    item.setId("item-789");
    item.setProductId("prod-123");
    item.setProductName("Another Product");
    item.setQuantity(3);
    item.setUnitPrice(new BigDecimal("49.99"));
    item.setTotalPrice(new BigDecimal("149.97"));

    assertEquals("item-789", item.getId());
    assertEquals("prod-123", item.getProductId());
    assertEquals("Another Product", item.getProductName());
    assertEquals(3, item.getQuantity());
    assertEquals(new BigDecimal("49.99"), item.getUnitPrice());
    assertEquals(new BigDecimal("149.97"), item.getTotalPrice());
  }

  @Test
  void testEqualsAndHashCode() {
    OrderItem item1 = new OrderItem();
    item1.setId("same-id");

    OrderItem item2 = new OrderItem();
    item2.setId("same-id");

    OrderItem item3 = new OrderItem();
    item3.setId("different-id");

    assertEquals(item1, item2);
    assertEquals(item1.hashCode(), item2.hashCode());
    assertNotEquals(item1, item3);
    assertNotEquals(item1, null);
    assertNotEquals(item1, new Object());
    assertEquals(item1, item1);
  }
}
