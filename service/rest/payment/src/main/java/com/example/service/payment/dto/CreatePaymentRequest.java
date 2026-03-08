package com.example.service.payment.dto;

import com.example.service.payment.model.PaymentMethod;
import java.math.BigDecimal;

/** Request DTO for creating a new payment. */
public class CreatePaymentRequest {

  private String orderId;
  private String userId;
  private BigDecimal amount;
  private String currency;
  private PaymentMethod paymentMethod;

  /** Default constructor. */
  public CreatePaymentRequest() {}

  /** Full constructor. */
  public CreatePaymentRequest(
      String orderId,
      String userId,
      BigDecimal amount,
      String currency,
      PaymentMethod paymentMethod) {
    this.orderId = orderId;
    this.userId = userId;
    this.amount = amount;
    this.currency = currency;
    this.paymentMethod = paymentMethod;
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
}
