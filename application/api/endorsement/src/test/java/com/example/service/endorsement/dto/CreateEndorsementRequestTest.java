package com.example.service.endorsement.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.endorsement.model.EndorsementType;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class CreateEndorsementRequestTest {

  @Test
  void testDefaultConstructor() {
    CreateEndorsementRequest request = new CreateEndorsementRequest();
    assertNull(request.getOrderId());
    assertNull(request.getUserId());
    assertNull(request.getProductId());
    assertNull(request.getEndorsementType());
    assertNull(request.getDescription());
    assertNull(request.getEffectiveDate());
  }

  @Test
  void testFullConstructor() {
    Instant effective = Instant.parse("2024-07-01T00:00:00Z");
    CreateEndorsementRequest request =
        new CreateEndorsementRequest(
            "order-123",
            "user-456",
            "prod-789",
            EndorsementType.ADD_COVERAGE,
            "Add dental coverage",
            effective);

    assertEquals("order-123", request.getOrderId());
    assertEquals("user-456", request.getUserId());
    assertEquals("prod-789", request.getProductId());
    assertEquals(EndorsementType.ADD_COVERAGE, request.getEndorsementType());
    assertEquals("Add dental coverage", request.getDescription());
    assertEquals(effective, request.getEffectiveDate());
  }

  @Test
  void testSetters() {
    CreateEndorsementRequest request = new CreateEndorsementRequest();
    Instant effective = Instant.parse("2024-08-01T00:00:00Z");

    request.setOrderId("order-789");
    request.setUserId("user-123");
    request.setProductId("prod-456");
    request.setEndorsementType(EndorsementType.CHANGE_BENEFICIARY);
    request.setDescription("Change primary beneficiary");
    request.setEffectiveDate(effective);

    assertEquals("order-789", request.getOrderId());
    assertEquals("user-123", request.getUserId());
    assertEquals("prod-456", request.getProductId());
    assertEquals(EndorsementType.CHANGE_BENEFICIARY, request.getEndorsementType());
    assertEquals("Change primary beneficiary", request.getDescription());
    assertEquals(effective, request.getEffectiveDate());
  }
}
