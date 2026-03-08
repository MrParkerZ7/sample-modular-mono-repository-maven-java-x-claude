package com.example.service.payment.model;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class PaymentTest {

  @Test
  void testDefaultConstructor() {
    Payment payment = new Payment();
    assertNull(payment.getId());
    assertNull(payment.getTransactionId());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    Payment payment =
        new Payment(
            "pay-123",
            "TXN-2024-00001",
            "order-456",
            "user-789",
            new BigDecimal("108.50"),
            "USD",
            PaymentMethod.CREDIT_CARD,
            PaymentStatus.COMPLETED,
            null,
            now,
            now);

    assertEquals("pay-123", payment.getId());
    assertEquals("TXN-2024-00001", payment.getTransactionId());
    assertEquals("order-456", payment.getOrderId());
    assertEquals("user-789", payment.getUserId());
    assertEquals(new BigDecimal("108.50"), payment.getAmount());
    assertEquals("USD", payment.getCurrency());
    assertEquals(PaymentMethod.CREDIT_CARD, payment.getPaymentMethod());
    assertEquals(PaymentStatus.COMPLETED, payment.getStatus());
    assertNull(payment.getFailureReason());
    assertEquals(now, payment.getCreatedAt());
    assertEquals(now, payment.getUpdatedAt());
  }

  @Test
  void testSetters() {
    Payment payment = new Payment();
    Instant now = Instant.now();

    payment.setId("pay-456");
    payment.setTransactionId("TXN-2024-00002");
    payment.setOrderId("order-123");
    payment.setUserId("user-456");
    payment.setAmount(new BigDecimal("200.00"));
    payment.setCurrency("EUR");
    payment.setPaymentMethod(PaymentMethod.PAYPAL);
    payment.setStatus(PaymentStatus.FAILED);
    payment.setFailureReason("Insufficient funds");
    payment.setCreatedAt(now);
    payment.setUpdatedAt(now);

    assertEquals("pay-456", payment.getId());
    assertEquals("TXN-2024-00002", payment.getTransactionId());
    assertEquals("order-123", payment.getOrderId());
    assertEquals("user-456", payment.getUserId());
    assertEquals(new BigDecimal("200.00"), payment.getAmount());
    assertEquals("EUR", payment.getCurrency());
    assertEquals(PaymentMethod.PAYPAL, payment.getPaymentMethod());
    assertEquals(PaymentStatus.FAILED, payment.getStatus());
    assertEquals("Insufficient funds", payment.getFailureReason());
    assertEquals(now, payment.getCreatedAt());
    assertEquals(now, payment.getUpdatedAt());
  }

  @Test
  void testEqualsAndHashCode() {
    Payment payment1 = new Payment();
    payment1.setId("same-id");

    Payment payment2 = new Payment();
    payment2.setId("same-id");

    Payment payment3 = new Payment();
    payment3.setId("different-id");

    assertEquals(payment1, payment2);
    assertEquals(payment1.hashCode(), payment2.hashCode());
    assertNotEquals(payment1, payment3);
    assertNotEquals(payment1, null);
    assertNotEquals(payment1, new Object());
    assertEquals(payment1, payment1);
  }
}
