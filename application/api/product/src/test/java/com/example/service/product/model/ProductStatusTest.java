package com.example.service.product.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductStatusTest {

  @Test
  void testEnumValues() {
    ProductStatus[] values = ProductStatus.values();
    assertEquals(4, values.length);
    assertEquals(ProductStatus.ACTIVE, ProductStatus.valueOf("ACTIVE"));
    assertEquals(ProductStatus.INACTIVE, ProductStatus.valueOf("INACTIVE"));
    assertEquals(ProductStatus.DISCONTINUED, ProductStatus.valueOf("DISCONTINUED"));
    assertEquals(ProductStatus.COMING_SOON, ProductStatus.valueOf("COMING_SOON"));
  }
}
