package com.example.service.product.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class ProductTest {

  @Test
  void testDefaultConstructor() {
    Product product = new Product();
    assertNull(product.getId());
    assertNull(product.getCode());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    Product product =
        new Product(
            "id-123",
            "LIFE-001",
            "Basic Life Insurance",
            "Entry-level life insurance",
            ProductType.LIFE_INSURANCE,
            ProductStatus.ACTIVE,
            new BigDecimal("99.99"),
            "USD",
            12,
            now,
            now);

    assertEquals("id-123", product.getId());
    assertEquals("LIFE-001", product.getCode());
    assertEquals("Basic Life Insurance", product.getName());
    assertEquals("Entry-level life insurance", product.getDescription());
    assertEquals(ProductType.LIFE_INSURANCE, product.getProductType());
    assertEquals(ProductStatus.ACTIVE, product.getStatus());
    assertEquals(new BigDecimal("99.99"), product.getBasePrice());
    assertEquals("USD", product.getCurrency());
    assertEquals(12, product.getTermMonths());
    assertEquals(now, product.getCreatedAt());
    assertEquals(now, product.getUpdatedAt());
  }

  @Test
  void testSetters() {
    Product product = new Product();
    Instant now = Instant.now();

    product.setId("id-456");
    product.setCode("HEALTH-001");
    product.setName("Health Plan");
    product.setDescription("Health coverage");
    product.setProductType(ProductType.HEALTH_INSURANCE);
    product.setStatus(ProductStatus.INACTIVE);
    product.setBasePrice(new BigDecimal("149.99"));
    product.setCurrency("EUR");
    product.setTermMonths(24);
    product.setCreatedAt(now);
    product.setUpdatedAt(now);

    assertEquals("id-456", product.getId());
    assertEquals("HEALTH-001", product.getCode());
    assertEquals("Health Plan", product.getName());
    assertEquals("Health coverage", product.getDescription());
    assertEquals(ProductType.HEALTH_INSURANCE, product.getProductType());
    assertEquals(ProductStatus.INACTIVE, product.getStatus());
    assertEquals(new BigDecimal("149.99"), product.getBasePrice());
    assertEquals("EUR", product.getCurrency());
    assertEquals(24, product.getTermMonths());
    assertEquals(now, product.getCreatedAt());
    assertEquals(now, product.getUpdatedAt());
  }

  @Test
  void testEqualsAndHashCode() {
    Product product1 = new Product();
    product1.setId("same-id");

    Product product2 = new Product();
    product2.setId("same-id");

    Product product3 = new Product();
    product3.setId("different-id");

    assertEquals(product1, product2);
    assertEquals(product1.hashCode(), product2.hashCode());
    assertNotEquals(product1, product3);
    assertNotEquals(product1, null);
    assertNotEquals(product1, new Object());
    assertEquals(product1, product1);
  }
}
