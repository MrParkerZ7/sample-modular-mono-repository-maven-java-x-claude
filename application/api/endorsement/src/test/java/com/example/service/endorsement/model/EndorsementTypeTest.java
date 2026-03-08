package com.example.service.endorsement.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EndorsementTypeTest {

  @Test
  void testAllValues() {
    EndorsementType[] values = EndorsementType.values();
    assertEquals(6, values.length);
    assertEquals(EndorsementType.ADD_COVERAGE, EndorsementType.valueOf("ADD_COVERAGE"));
    assertEquals(EndorsementType.REMOVE_COVERAGE, EndorsementType.valueOf("REMOVE_COVERAGE"));
    assertEquals(EndorsementType.CHANGE_BENEFICIARY, EndorsementType.valueOf("CHANGE_BENEFICIARY"));
    assertEquals(EndorsementType.CHANGE_SUM_INSURED, EndorsementType.valueOf("CHANGE_SUM_INSURED"));
    assertEquals(EndorsementType.EXTEND_TERM, EndorsementType.valueOf("EXTEND_TERM"));
    assertEquals(EndorsementType.CANCEL_POLICY, EndorsementType.valueOf("CANCEL_POLICY"));
  }
}
