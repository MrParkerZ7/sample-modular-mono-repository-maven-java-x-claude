package com.example.service.payment.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/** Domain model representing a payment. */
public class Payment {

  private String id;
  private String transactionId;
  private String orderId;
  private String userId;
  private BigDecimal amount;
  private String currency;
  private PaymentMethod paymentMethod;
  private PaymentStatus status;
  private String failureReason;
  private Instant createdAt;
  private Instant updatedAt;

  /** Default constructor. */
  public Payment() {}

  /** Full constructor. */
  public Payment(
      String id,
      String transactionId,
      String orderId,
      String userId,
      BigDecimal amount,
      String currency,
      PaymentMethod paymentMethod,
      PaymentStatus status,
      String failureReason,
      Instant createdAt,
      Instant updatedAt) {
    this.id = id;
    this.transactionId = transactionId;
    this.orderId = orderId;
    this.userId = userId;
    this.amount = amount;
    this.currency = currency;
    this.paymentMethod = paymentMethod;
    this.status = status;
    this.failureReason = failureReason;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public String getFailureReason() {
    return failureReason;
  }

  public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Payment payment = (Payment) o;
    return Objects.equals(id, payment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
