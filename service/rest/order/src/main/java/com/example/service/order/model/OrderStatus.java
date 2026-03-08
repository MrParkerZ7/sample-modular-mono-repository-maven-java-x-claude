package com.example.service.order.model;

/** Represents the status of an order. */
public enum OrderStatus {
  /** Order awaiting confirmation. */
  PENDING,

  /** Order has been confirmed. */
  CONFIRMED,

  /** Order is being processed. */
  PROCESSING,

  /** Order has been completed. */
  COMPLETED,

  /** Order has been cancelled. */
  CANCELLED,

  /** Order has been refunded. */
  REFUNDED
}
