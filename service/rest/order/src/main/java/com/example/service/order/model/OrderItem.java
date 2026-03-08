package com.example.service.order.model;

import java.math.BigDecimal;
import java.util.Objects;

/** Domain model representing an order line item. */
public class OrderItem {

  private String id;
  private String productId;
  private String productName;
  private Integer quantity;
  private BigDecimal unitPrice;
  private BigDecimal totalPrice;

  /** Default constructor. */
  public OrderItem() {}

  /** Full constructor. */
  public OrderItem(
      String id,
      String productId,
      String productName,
      Integer quantity,
      BigDecimal unitPrice,
      BigDecimal totalPrice) {
    this.id = id;
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderItem orderItem = (OrderItem) o;
    return Objects.equals(id, orderItem.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
