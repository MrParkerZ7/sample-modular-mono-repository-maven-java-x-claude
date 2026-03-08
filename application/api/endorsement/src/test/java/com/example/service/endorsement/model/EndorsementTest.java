package com.example.service.endorsement.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class EndorsementTest {

  @Test
  void testDefaultConstructor() {
    Endorsement endorsement = new Endorsement();
    assertNull(endorsement.getId());
    assertNull(endorsement.getEndorsementNumber());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    Instant effective = Instant.parse("2024-07-01T00:00:00Z");
    Endorsement endorsement =
        new Endorsement(
            "end-123",
            "END-2024-00001",
            "order-456",
            "user-789",
            "prod-101",
            EndorsementType.ADD_COVERAGE,
            EndorsementStatus.PENDING,
            "Add dental coverage",
            null,
            effective,
            now,
            now);

    assertEquals("end-123", endorsement.getId());
    assertEquals("END-2024-00001", endorsement.getEndorsementNumber());
    assertEquals("order-456", endorsement.getOrderId());
    assertEquals("user-789", endorsement.getUserId());
    assertEquals("prod-101", endorsement.getProductId());
    assertEquals(EndorsementType.ADD_COVERAGE, endorsement.getEndorsementType());
    assertEquals(EndorsementStatus.PENDING, endorsement.getStatus());
    assertEquals("Add dental coverage", endorsement.getDescription());
    assertNull(endorsement.getRejectionReason());
    assertEquals(effective, endorsement.getEffectiveDate());
    assertEquals(now, endorsement.getCreatedAt());
    assertEquals(now, endorsement.getUpdatedAt());
  }

  @Test
  void testSetters() {
    Endorsement endorsement = new Endorsement();
    Instant now = Instant.now();
    Instant effective = Instant.parse("2024-08-01T00:00:00Z");

    endorsement.setId("end-456");
    endorsement.setEndorsementNumber("END-2024-00002");
    endorsement.setOrderId("order-123");
    endorsement.setUserId("user-456");
    endorsement.setProductId("prod-202");
    endorsement.setEndorsementType(EndorsementType.CHANGE_BENEFICIARY);
    endorsement.setStatus(EndorsementStatus.APPROVED);
    endorsement.setDescription("Change primary beneficiary");
    endorsement.setRejectionReason("Policy expired");
    endorsement.setEffectiveDate(effective);
    endorsement.setCreatedAt(now);
    endorsement.setUpdatedAt(now);

    assertEquals("end-456", endorsement.getId());
    assertEquals("END-2024-00002", endorsement.getEndorsementNumber());
    assertEquals("order-123", endorsement.getOrderId());
    assertEquals("user-456", endorsement.getUserId());
    assertEquals("prod-202", endorsement.getProductId());
    assertEquals(EndorsementType.CHANGE_BENEFICIARY, endorsement.getEndorsementType());
    assertEquals(EndorsementStatus.APPROVED, endorsement.getStatus());
    assertEquals("Change primary beneficiary", endorsement.getDescription());
    assertEquals("Policy expired", endorsement.getRejectionReason());
    assertEquals(effective, endorsement.getEffectiveDate());
    assertEquals(now, endorsement.getCreatedAt());
    assertEquals(now, endorsement.getUpdatedAt());
  }

  @Test
  void testEqualsAndHashCode() {
    Endorsement endorsement1 = new Endorsement();
    endorsement1.setId("same-id");

    Endorsement endorsement2 = new Endorsement();
    endorsement2.setId("same-id");

    Endorsement endorsement3 = new Endorsement();
    endorsement3.setId("different-id");

    assertEquals(endorsement1, endorsement2);
    assertEquals(endorsement1.hashCode(), endorsement2.hashCode());
    assertNotEquals(endorsement1, endorsement3);
    assertNotEquals(endorsement1, null);
    assertNotEquals(endorsement1, new Object());
    assertEquals(endorsement1, endorsement1);
  }
}
