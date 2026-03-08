package com.example.service.payment.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.payment.model.PaymentMethod;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class CreatePaymentRequestTest {

  @Test
  void testDefaultConstructor() {
    CreatePaymentRequest request = new CreatePaymentRequest();
    assertNull(request.getOrderId());
    assertNull(request.getUserId());
    assertNull(request.getAmount());
    assertNull(request.getCurrency());
    assertNull(request.getPaymentMethod());
  }

  @Test
  void testFullConstructor() {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-123", "user-456", new BigDecimal("108.50"), "USD", PaymentMethod.CREDIT_CARD);

    assertEquals("order-123", request.getOrderId());
    assertEquals("user-456", request.getUserId());
    assertEquals(new BigDecimal("108.50"), request.getAmount());
    assertEquals("USD", request.getCurrency());
    assertEquals(PaymentMethod.CREDIT_CARD, request.getPaymentMethod());
  }

  @Test
  void testSetters() {
    CreatePaymentRequest request = new CreatePaymentRequest();

    request.setOrderId("order-789");
    request.setUserId("user-123");
    request.setAmount(new BigDecimal("200.00"));
    request.setCurrency("EUR");
    request.setPaymentMethod(PaymentMethod.PAYPAL);

    assertEquals("order-789", request.getOrderId());
    assertEquals("user-123", request.getUserId());
    assertEquals(new BigDecimal("200.00"), request.getAmount());
    assertEquals("EUR", request.getCurrency());
    assertEquals(PaymentMethod.PAYPAL, request.getPaymentMethod());
  }
}
