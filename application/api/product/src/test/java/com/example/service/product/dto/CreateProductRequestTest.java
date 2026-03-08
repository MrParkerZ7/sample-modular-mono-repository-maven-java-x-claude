package com.example.service.product.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.product.model.ProductType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CreateProductRequestTest {

  @Test
  void testDefaultConstructor() {
    CreateProductRequest request = new CreateProductRequest();
    assertNull(request.getCode());
    assertNull(request.getName());
    assertNull(request.getDescription());
    assertNull(request.getProductType());
    assertNull(request.getBasePrice());
    assertNull(request.getCurrency());
    assertNull(request.getTermMonths());
  }

  @Test
  void testFullConstructor() {
    CreateProductRequest request =
        new CreateProductRequest(
            "LIFE-001",
            "Basic Life Insurance",
            "Entry-level product",
            ProductType.LIFE_INSURANCE,
            new BigDecimal("99.99"),
            "USD",
            12);

    assertEquals("LIFE-001", request.getCode());
    assertEquals("Basic Life Insurance", request.getName());
    assertEquals("Entry-level product", request.getDescription());
    assertEquals(ProductType.LIFE_INSURANCE, request.getProductType());
    assertEquals(new BigDecimal("99.99"), request.getBasePrice());
    assertEquals("USD", request.getCurrency());
    assertEquals(12, request.getTermMonths());
  }

  @Test
  void testSetters() {
    CreateProductRequest request = new CreateProductRequest();

    request.setCode("HEALTH-001");
    request.setName("Health Plan");
    request.setDescription("Health coverage");
    request.setProductType(ProductType.HEALTH_INSURANCE);
    request.setBasePrice(new BigDecimal("149.99"));
    request.setCurrency("EUR");
    request.setTermMonths(24);

    assertEquals("HEALTH-001", request.getCode());
    assertEquals("Health Plan", request.getName());
    assertEquals("Health coverage", request.getDescription());
    assertEquals(ProductType.HEALTH_INSURANCE, request.getProductType());
    assertEquals(new BigDecimal("149.99"), request.getBasePrice());
    assertEquals("EUR", request.getCurrency());
    assertEquals(24, request.getTermMonths());
  }
}
