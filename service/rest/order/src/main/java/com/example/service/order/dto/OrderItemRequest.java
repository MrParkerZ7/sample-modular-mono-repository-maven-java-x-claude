package com.example.service.order.dto;

import java.math.BigDecimal;

/** DTO for order item in requests. */
public class OrderItemRequest {

  private String productId;
  private String productName;
  private Integer quantity;
  private BigDecimal unitPrice;

  /** Default constructor. */
  public OrderItemRequest() {}

  /** Full constructor. */
  public OrderItemRequest(
      String productId, String productName, Integer quantity, BigDecimal unitPrice) {
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }
}
