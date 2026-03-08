package com.example.service.payment.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentStatusTest {

  @Test
  void testEnumValues() {
    PaymentStatus[] values = PaymentStatus.values();
    assertEquals(6, values.length);
    assertEquals(PaymentStatus.PENDING, PaymentStatus.valueOf("PENDING"));
    assertEquals(PaymentStatus.PROCESSING, PaymentStatus.valueOf("PROCESSING"));
    assertEquals(PaymentStatus.COMPLETED, PaymentStatus.valueOf("COMPLETED"));
    assertEquals(PaymentStatus.FAILED, PaymentStatus.valueOf("FAILED"));
    assertEquals(PaymentStatus.REFUNDED, PaymentStatus.valueOf("REFUNDED"));
    assertEquals(PaymentStatus.CANCELLED, PaymentStatus.valueOf("CANCELLED"));
  }
}
