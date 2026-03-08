package com.example.service.order.model;

import com.example.model.base.AuditableEntity;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/** Domain model representing an order. */
public class Order extends AuditableEntity {

  private String orderNumber;
  private String userId;
  private List<OrderItem> items;
  private BigDecimal subtotal;
  private BigDecimal tax;
  private BigDecimal total;
  private OrderStatus status;

  /** Default constructor. */
  public Order() {}

  /** Full constructor. */
  public Order(
      String id,
      String orderNumber,
      String userId,
      List<OrderItem> items,
      BigDecimal subtotal,
      BigDecimal tax,
      BigDecimal total,
      OrderStatus status,
      Instant createdAt,
      Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.orderNumber = orderNumber;
    this.userId = userId;
    this.items = items;
    this.subtotal = subtotal;
    this.tax = tax;
    this.total = total;
    this.status = status;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<OrderItem> getItems() {
    return items;
  }

  public void setItems(List<OrderItem> items) {
    this.items = items;
  }

  public BigDecimal getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }

  public BigDecimal getTax() {
    return tax;
  }

  public void setTax(BigDecimal tax) {
    this.tax = tax;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
