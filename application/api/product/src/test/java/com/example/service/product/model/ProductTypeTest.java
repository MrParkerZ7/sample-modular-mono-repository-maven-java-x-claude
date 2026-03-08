package com.example.service.product.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTypeTest {

  @Test
  void testEnumValues() {
    ProductType[] values = ProductType.values();
    assertEquals(5, values.length);
    assertEquals(ProductType.LIFE_INSURANCE, ProductType.valueOf("LIFE_INSURANCE"));
    assertEquals(ProductType.HEALTH_INSURANCE, ProductType.valueOf("HEALTH_INSURANCE"));
    assertEquals(ProductType.AUTO_INSURANCE, ProductType.valueOf("AUTO_INSURANCE"));
    assertEquals(ProductType.HOME_INSURANCE, ProductType.valueOf("HOME_INSURANCE"));
    assertEquals(ProductType.TRAVEL_INSURANCE, ProductType.valueOf("TRAVEL_INSURANCE"));
  }
}
