package com.example.service.order.dto;

import java.util.List;

/** Request DTO for updating an existing order. */
public class UpdateOrderRequest {

  private List<OrderItemRequest> items;

  /** Default constructor. */
  public UpdateOrderRequest() {}

  /** Full constructor. */
  public UpdateOrderRequest(List<OrderItemRequest> items) {
    this.items = items;
  }

  public List<OrderItemRequest> getItems() {
    return items;
  }

  public void setItems(List<OrderItemRequest> items) {
    this.items = items;
  }
}
