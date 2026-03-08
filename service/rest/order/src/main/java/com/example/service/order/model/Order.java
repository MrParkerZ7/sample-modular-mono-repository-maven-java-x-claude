package com.example.service.order.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/** Domain model representing an order. */
public class Order {

  private String id;
  private String orderNumber;
  private String userId;
  private List<OrderItem> items;
  private BigDecimal subtotal;
  private BigDecimal tax;
  private BigDecimal total;
  private OrderStatus status;
  private Instant createdAt;
  private Instant updatedAt;

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
    this.id = id;
    this.orderNumber = orderNumber;
    this.userId = userId;
    this.items = items;
    this.subtotal = subtotal;
    this.tax = tax;
    this.total = total;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
    Order order = (Order) o;
    return Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
