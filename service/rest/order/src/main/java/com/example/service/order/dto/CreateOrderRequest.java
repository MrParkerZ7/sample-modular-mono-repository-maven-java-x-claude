package com.example.service.order.dto;

import java.util.List;

/** Request DTO for creating a new order. */
public class CreateOrderRequest {

  private String userId;
  private List<OrderItemRequest> items;

  /** Default constructor. */
  public CreateOrderRequest() {}

  /** Full constructor. */
  public CreateOrderRequest(String userId, List<OrderItemRequest> items) {
    this.userId = userId;
    this.items = items;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<OrderItemRequest> getItems() {
    return items;
  }

  public void setItems(List<OrderItemRequest> items) {
    this.items = items;
  }
}
