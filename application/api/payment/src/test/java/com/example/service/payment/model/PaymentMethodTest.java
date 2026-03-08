package com.example.service.payment.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentMethodTest {

  @Test
  void testEnumValues() {
    PaymentMethod[] values = PaymentMethod.values();
    assertEquals(5, values.length);
    assertEquals(PaymentMethod.CREDIT_CARD, PaymentMethod.valueOf("CREDIT_CARD"));
    assertEquals(PaymentMethod.DEBIT_CARD, PaymentMethod.valueOf("DEBIT_CARD"));
    assertEquals(PaymentMethod.BANK_TRANSFER, PaymentMethod.valueOf("BANK_TRANSFER"));
    assertEquals(PaymentMethod.PAYPAL, PaymentMethod.valueOf("PAYPAL"));
    assertEquals(PaymentMethod.CRYPTO, PaymentMethod.valueOf("CRYPTO"));
  }
}
