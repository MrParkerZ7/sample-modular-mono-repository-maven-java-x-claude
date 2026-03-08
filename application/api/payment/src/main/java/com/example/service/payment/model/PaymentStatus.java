package com.example.service.payment.model;

/** Represents the status of a payment. */
public enum PaymentStatus {
  /** Payment awaiting processing. */
  PENDING,

  /** Payment is being processed. */
  PROCESSING,

  /** Payment completed successfully. */
  COMPLETED,

  /** Payment failed. */
  FAILED,

  /** Payment has been refunded. */
  REFUNDED,

  /** Payment has been cancelled. */
  CANCELLED
}
