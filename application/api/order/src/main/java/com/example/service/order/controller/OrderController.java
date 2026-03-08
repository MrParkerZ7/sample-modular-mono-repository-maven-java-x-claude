package com.example.service.order.controller;

import com.example.service.order.dto.CreateOrderRequest;
import com.example.service.order.dto.UpdateOrderRequest;
import com.example.service.order.model.Order;
import com.example.service.order.model.OrderStatus;
import com.example.service.order.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for order operations. */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<List<Order>> getAllOrders() {
    return ResponseEntity.ok(orderService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getOrderById(@PathVariable String id) {
    return orderService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/number/{orderNumber}")
  public ResponseEntity<Order> getOrderByNumber(@PathVariable String orderNumber) {
    return orderService
        .findByOrderNumber(orderNumber)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(orderService.findByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request) {
    Order created = orderService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Order> updateOrder(
      @PathVariable String id, @RequestBody UpdateOrderRequest request) {
    return orderService
        .update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}/status/{status}")
  public ResponseEntity<Order> updateOrderStatus(
      @PathVariable String id, @PathVariable OrderStatus status) {
    return orderService
        .updateStatus(id, status)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> cancelOrder(@PathVariable String id) {
    if (orderService.cancel(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
