package com.example.service.endorsement.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.endorsement.dto.CreateEndorsementRequest;
import com.example.service.endorsement.model.Endorsement;
import com.example.service.endorsement.model.EndorsementStatus;
import com.example.service.endorsement.model.EndorsementType;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndorsementServiceImplTest {

  private EndorsementServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new EndorsementServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<Endorsement> endorsements = service.findAll();
    assertTrue(endorsements.isEmpty());
  }

  @Test
  void testCreateAndFindById() {
    Instant effective = Instant.parse("2024-07-01T00:00:00Z");
    CreateEndorsementRequest request =
        new CreateEndorsementRequest(
            "order-123",
            "user-456",
            "prod-789",
            EndorsementType.ADD_COVERAGE,
            "Add dental coverage",
            effective);

    Endorsement created = service.create(request);

    assertNotNull(created.getId());
    assertNotNull(created.getEndorsementNumber());
    assertTrue(created.getEndorsementNumber().startsWith("END-"));
    assertEquals("order-123", created.getOrderId());
    assertEquals("user-456", created.getUserId());
    assertEquals("prod-789", created.getProductId());
    assertEquals(EndorsementType.ADD_COVERAGE, created.getEndorsementType());
    assertEquals(EndorsementStatus.PENDING, created.getStatus());
    assertEquals("Add dental coverage", created.getDescription());
    assertNull(created.getRejectionReason());
    assertEquals(effective, created.getEffectiveDate());
    assertNotNull(created.getCreatedAt());
    assertNotNull(created.getUpdatedAt());

    Optional<Endorsement> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Endorsement> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByEndorsementNumber() {
    CreateEndorsementRequest request =
        new CreateEndorsementRequest(
            "order-123",
            "user-456",
            "prod-789",
            EndorsementType.CHANGE_BENEFICIARY,
            "Change beneficiary",
            null);
    Endorsement created = service.create(request);

    Optional<Endorsement> found = service.findByEndorsementNumber(created.getEndorsementNumber());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByEndorsementNumberNotFound() {
    Optional<Endorsement> found = service.findByEndorsementNumber("END-9999-99999");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByOrderId() {
    service.create(
        new CreateEndorsementRequest(
            "order-123", "user-1", "prod-1", EndorsementType.ADD_COVERAGE, "Add coverage", null));
    service.create(
        new CreateEndorsementRequest(
            "order-123", "user-2", "prod-2", EndorsementType.EXTEND_TERM, "Extend term", null));
    service.create(
        new CreateEndorsementRequest(
            "order-456", "user-1", "prod-1", EndorsementType.CANCEL_POLICY, "Cancel", null));

    List<Endorsement> order123 = service.findByOrderId("order-123");
    assertEquals(2, order123.size());

    List<Endorsement> order456 = service.findByOrderId("order-456");
    assertEquals(1, order456.size());

    List<Endorsement> nonExistent = service.findByOrderId("order-999");
    assertTrue(nonExistent.isEmpty());
  }

  @Test
  void testFindByUserId() {
    service.create(
        new CreateEndorsementRequest(
            "order-1", "user-123", "prod-1", EndorsementType.ADD_COVERAGE, "Add coverage", null));
    service.create(
        new CreateEndorsementRequest(
            "order-2",
            "user-123",
            "prod-2",
            EndorsementType.REMOVE_COVERAGE,
            "Remove coverage",
            null));
    service.create(
        new CreateEndorsementRequest(
            "order-3",
            "user-456",
            "prod-3",
            EndorsementType.CHANGE_SUM_INSURED,
            "Change sum",
            null));

    List<Endorsement> user123 = service.findByUserId("user-123");
    assertEquals(2, user123.size());

    List<Endorsement> user456 = service.findByUserId("user-456");
    assertEquals(1, user456.size());

    List<Endorsement> nonExistent = service.findByUserId("user-999");
    assertTrue(nonExistent.isEmpty());
  }

  @Test
  void testFindAll() {
    service.create(
        new CreateEndorsementRequest(
            "order-1", "user-1", "prod-1", EndorsementType.ADD_COVERAGE, "Add", null));
    service.create(
        new CreateEndorsementRequest(
            "order-2", "user-2", "prod-2", EndorsementType.EXTEND_TERM, "Extend", null));

    List<Endorsement> endorsements = service.findAll();
    assertEquals(2, endorsements.size());
  }

  @Test
  void testApprove() {
    Endorsement created =
        service.create(
            new CreateEndorsementRequest(
                "order-123",
                "user-456",
                "prod-789",
                EndorsementType.ADD_COVERAGE,
                "Add coverage",
                null));

    Optional<Endorsement> approved = service.approve(created.getId());

    assertTrue(approved.isPresent());
    assertEquals(EndorsementStatus.APPROVED, approved.get().getStatus());
  }

  @Test
  void testApproveNotFound() {
    Optional<Endorsement> approved = service.approve("non-existent-id");
    assertTrue(approved.isEmpty());
  }

  @Test
  void testReject() {
    Endorsement created =
        service.create(
            new CreateEndorsementRequest(
                "order-123",
                "user-456",
                "prod-789",
                EndorsementType.CANCEL_POLICY,
                "Cancel policy",
                null));

    Optional<Endorsement> rejected = service.reject(created.getId(), "Policy still active");

    assertTrue(rejected.isPresent());
    assertEquals(EndorsementStatus.REJECTED, rejected.get().getStatus());
    assertEquals("Policy still active", rejected.get().getRejectionReason());
  }

  @Test
  void testRejectNotFound() {
    Optional<Endorsement> rejected = service.reject("non-existent-id", "reason");
    assertTrue(rejected.isEmpty());
  }

  @Test
  void testApply() {
    Endorsement created =
        service.create(
            new CreateEndorsementRequest(
                "order-123",
                "user-456",
                "prod-789",
                EndorsementType.CHANGE_BENEFICIARY,
                "Change beneficiary",
                null));

    Optional<Endorsement> applied = service.apply(created.getId());

    assertTrue(applied.isPresent());
    assertEquals(EndorsementStatus.APPLIED, applied.get().getStatus());
  }

  @Test
  void testApplyNotFound() {
    Optional<Endorsement> applied = service.apply("non-existent-id");
    assertTrue(applied.isEmpty());
  }

  @Test
  void testDelete() {
    Endorsement created =
        service.create(
            new CreateEndorsementRequest(
                "order-123",
                "user-456",
                "prod-789",
                EndorsementType.EXTEND_TERM,
                "Extend term",
                null));

    assertTrue(service.delete(created.getId()));

    Optional<Endorsement> found = service.findById(created.getId());
    assertTrue(found.isEmpty());

    Optional<Endorsement> byNumber =
        service.findByEndorsementNumber(created.getEndorsementNumber());
    assertTrue(byNumber.isEmpty());
  }

  @Test
  void testDeleteNotFound() {
    assertFalse(service.delete("non-existent-id"));
  }

  @Test
  void testAllEndorsementTypes() {
    for (EndorsementType type : EndorsementType.values()) {
      Endorsement created =
          service.create(
              new CreateEndorsementRequest(
                  "order-" + type.name(),
                  "user-test",
                  "prod-test",
                  type,
                  "Test " + type.name(),
                  null));

      assertEquals(type, created.getEndorsementType());
    }

    assertEquals(6, service.findAll().size());
  }
}
