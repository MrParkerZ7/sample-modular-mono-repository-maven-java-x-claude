package com.example.service.payment.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.payment.dto.CreatePaymentRequest;
import com.example.service.payment.model.Payment;
import com.example.service.payment.model.PaymentMethod;
import com.example.service.payment.model.PaymentStatus;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceImplTest {

  private PaymentServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new PaymentServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<Payment> payments = service.findAll();
    assertTrue(payments.isEmpty());
  }

  @Test
  void testProcessAndFindById() {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-123", "user-456", new BigDecimal("108.50"), "USD", PaymentMethod.CREDIT_CARD);

    Payment processed = service.process(request);

    assertNotNull(processed.getId());
    assertNotNull(processed.getTransactionId());
    assertTrue(processed.getTransactionId().startsWith("TXN-"));
    assertEquals("order-123", processed.getOrderId());
    assertEquals("user-456", processed.getUserId());
    assertEquals(new BigDecimal("108.50"), processed.getAmount());
    assertEquals("USD", processed.getCurrency());
    assertEquals(PaymentMethod.CREDIT_CARD, processed.getPaymentMethod());
    assertEquals(PaymentStatus.COMPLETED, processed.getStatus());
    assertNull(processed.getFailureReason());
    assertNotNull(processed.getCreatedAt());
    assertNotNull(processed.getUpdatedAt());

    Optional<Payment> found = service.findById(processed.getId());
    assertTrue(found.isPresent());
    assertEquals(processed.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Payment> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByTransactionId() {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-123", "user-456", new BigDecimal("100.00"), "USD", PaymentMethod.DEBIT_CARD);
    Payment processed = service.process(request);

    Optional<Payment> found = service.findByTransactionId(processed.getTransactionId());
    assertTrue(found.isPresent());
    assertEquals(processed.getId(), found.get().getId());
  }

  @Test
  void testFindByTransactionIdNotFound() {
    Optional<Payment> found = service.findByTransactionId("TXN-9999-99999");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByOrderId() {
    service.process(
        new CreatePaymentRequest(
            "order-123", "user-1", new BigDecimal("50.00"), "USD", PaymentMethod.CREDIT_CARD));
    service.process(
        new CreatePaymentRequest(
            "order-123", "user-2", new BigDecimal("75.00"), "USD", PaymentMethod.PAYPAL));
    service.process(
        new CreatePaymentRequest(
            "order-456", "user-1", new BigDecimal("100.00"), "USD", PaymentMethod.BANK_TRANSFER));

    List<Payment> order123Payments = service.findByOrderId("order-123");
    assertEquals(2, order123Payments.size());

    List<Payment> order456Payments = service.findByOrderId("order-456");
    assertEquals(1, order456Payments.size());

    List<Payment> nonExistentPayments = service.findByOrderId("order-999");
    assertTrue(nonExistentPayments.isEmpty());
  }

  @Test
  void testFindByUserId() {
    service.process(
        new CreatePaymentRequest(
            "order-1", "user-123", new BigDecimal("50.00"), "USD", PaymentMethod.CREDIT_CARD));
    service.process(
        new CreatePaymentRequest(
            "order-2", "user-123", new BigDecimal("75.00"), "USD", PaymentMethod.CRYPTO));
    service.process(
        new CreatePaymentRequest(
            "order-3", "user-456", new BigDecimal("100.00"), "EUR", PaymentMethod.PAYPAL));

    List<Payment> user123Payments = service.findByUserId("user-123");
    assertEquals(2, user123Payments.size());

    List<Payment> user456Payments = service.findByUserId("user-456");
    assertEquals(1, user456Payments.size());

    List<Payment> nonExistentPayments = service.findByUserId("user-999");
    assertTrue(nonExistentPayments.isEmpty());
  }

  @Test
  void testFindAll() {
    service.process(
        new CreatePaymentRequest(
            "order-1", "user-1", new BigDecimal("50.00"), "USD", PaymentMethod.CREDIT_CARD));
    service.process(
        new CreatePaymentRequest(
            "order-2", "user-2", new BigDecimal("100.00"), "EUR", PaymentMethod.PAYPAL));

    List<Payment> payments = service.findAll();
    assertEquals(2, payments.size());
  }

  @Test
  void testRefund() {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-123", "user-456", new BigDecimal("108.50"), "USD", PaymentMethod.CREDIT_CARD);
    Payment processed = service.process(request);

    Optional<Payment> refunded = service.refund(processed.getId());

    assertTrue(refunded.isPresent());
    assertEquals(PaymentStatus.REFUNDED, refunded.get().getStatus());
  }

  @Test
  void testRefundNotFound() {
    Optional<Payment> refunded = service.refund("non-existent-id");
    assertTrue(refunded.isEmpty());
  }

  @Test
  void testCancel() {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-123", "user-456", new BigDecimal("108.50"), "USD", PaymentMethod.BANK_TRANSFER);
    Payment processed = service.process(request);

    assertTrue(service.cancel(processed.getId()));

    Optional<Payment> found = service.findById(processed.getId());
    assertTrue(found.isPresent());
    assertEquals(PaymentStatus.CANCELLED, found.get().getStatus());
  }

  @Test
  void testCancelNotFound() {
    assertFalse(service.cancel("non-existent-id"));
  }

  @Test
  void testMultiplePaymentMethods() {
    for (PaymentMethod method : PaymentMethod.values()) {
      Payment processed =
          service.process(
              new CreatePaymentRequest(
                  "order-" + method.name(), "user-test", new BigDecimal("100.00"), "USD", method));

      assertEquals(method, processed.getPaymentMethod());
    }

    assertEquals(5, service.findAll().size());
  }
}
