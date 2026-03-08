package com.example.service.product.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.product.model.ProductStatus;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class UpdateProductRequestTest {

  @Test
  void testDefaultConstructor() {
    UpdateProductRequest request = new UpdateProductRequest();
    assertNull(request.getName());
    assertNull(request.getDescription());
    assertNull(request.getStatus());
    assertNull(request.getBasePrice());
    assertNull(request.getTermMonths());
  }

  @Test
  void testFullConstructor() {
    UpdateProductRequest request =
        new UpdateProductRequest(
            "Updated Name",
            "Updated description",
            ProductStatus.ACTIVE,
            new BigDecimal("199.99"),
            24);

    assertEquals("Updated Name", request.getName());
    assertEquals("Updated description", request.getDescription());
    assertEquals(ProductStatus.ACTIVE, request.getStatus());
    assertEquals(new BigDecimal("199.99"), request.getBasePrice());
    assertEquals(24, request.getTermMonths());
  }

  @Test
  void testSetters() {
    UpdateProductRequest request = new UpdateProductRequest();

    request.setName("New Name");
    request.setDescription("New description");
    request.setStatus(ProductStatus.INACTIVE);
    request.setBasePrice(new BigDecimal("299.99"));
    request.setTermMonths(36);

    assertEquals("New Name", request.getName());
    assertEquals("New description", request.getDescription());
    assertEquals(ProductStatus.INACTIVE, request.getStatus());
    assertEquals(new BigDecimal("299.99"), request.getBasePrice());
    assertEquals(36, request.getTermMonths());
  }
}
