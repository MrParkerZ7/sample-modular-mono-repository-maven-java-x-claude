package com.example.service.endorsement.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EndorsementStatusTest {

  @Test
  void testAllValues() {
    EndorsementStatus[] values = EndorsementStatus.values();
    assertEquals(4, values.length);
    assertEquals(EndorsementStatus.PENDING, EndorsementStatus.valueOf("PENDING"));
    assertEquals(EndorsementStatus.APPROVED, EndorsementStatus.valueOf("APPROVED"));
    assertEquals(EndorsementStatus.REJECTED, EndorsementStatus.valueOf("REJECTED"));
    assertEquals(EndorsementStatus.APPLIED, EndorsementStatus.valueOf("APPLIED"));
  }
}
