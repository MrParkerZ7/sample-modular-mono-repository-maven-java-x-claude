package com.example.service.payment.model;

/** Represents the payment method used. */
public enum PaymentMethod {
  /** Credit card payment. */
  CREDIT_CARD,

  /** Debit card payment. */
  DEBIT_CARD,

  /** Bank transfer payment. */
  BANK_TRANSFER,

  /** PayPal payment. */
  PAYPAL,

  /** Cryptocurrency payment. */
  CRYPTO
}
