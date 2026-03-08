package com.example.service.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.order.dto.CreateOrderRequest;
import com.example.service.order.dto.OrderItemRequest;
import com.example.service.order.dto.UpdateOrderRequest;
import com.example.service.order.model.Order;
import com.example.service.order.model.OrderItem;
import com.example.service.order.model.OrderStatus;
import com.example.service.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private OrderService orderService;

  private Order createTestOrder() {
    Instant now = Instant.now();
    List<OrderItem> items = new ArrayList<>();
    items.add(
        new OrderItem(
            "item-1",
            "prod-123",
            "Test Product",
            2,
            new BigDecimal("100.00"),
            new BigDecimal("200.00")));

    return new Order(
        "order-123",
        "ORD-2024-00001",
        "user-456",
        items,
        new BigDecimal("200.00"),
        new BigDecimal("17.00"),
        new BigDecimal("217.00"),
        OrderStatus.PENDING,
        now,
        now);
  }

  @Test
  void testGetAllOrders() throws Exception {
    Order order = createTestOrder();
    when(orderService.findAll()).thenReturn(Arrays.asList(order));

    mockMvc
        .perform(get("/api/orders"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("order-123"))
        .andExpect(jsonPath("$[0].orderNumber").value("ORD-2024-00001"));
  }

  @Test
  void testGetOrderById() throws Exception {
    Order order = createTestOrder();
    when(orderService.findById("order-123")).thenReturn(Optional.of(order));

    mockMvc
        .perform(get("/api/orders/order-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("order-123"))
        .andExpect(jsonPath("$.userId").value("user-456"));
  }

  @Test
  void testGetOrderByIdNotFound() throws Exception {
    when(orderService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/orders/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetOrderByNumber() throws Exception {
    Order order = createTestOrder();
    when(orderService.findByOrderNumber("ORD-2024-00001")).thenReturn(Optional.of(order));

    mockMvc
        .perform(get("/api/orders/number/ORD-2024-00001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.orderNumber").value("ORD-2024-00001"));
  }

  @Test
  void testGetOrderByNumberNotFound() throws Exception {
    when(orderService.findByOrderNumber("ORD-9999-99999")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/orders/number/ORD-9999-99999")).andExpect(status().isNotFound());
  }

  @Test
  void testGetOrdersByUserId() throws Exception {
    Order order = createTestOrder();
    when(orderService.findByUserId("user-456")).thenReturn(Arrays.asList(order));

    mockMvc
        .perform(get("/api/orders/user/user-456"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userId").value("user-456"));
  }

  @Test
  void testGetOrdersByUserIdEmpty() throws Exception {
    when(orderService.findByUserId("user-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/orders/user/user-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testCreateOrder() throws Exception {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-123", "Test Product", 2, new BigDecimal("100.00")));

    CreateOrderRequest request = new CreateOrderRequest("user-456", items);
    Order created = createTestOrder();

    when(orderService.create(any(CreateOrderRequest.class))).thenReturn(created);

    mockMvc
        .perform(
            post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("order-123"));
  }

  @Test
  void testUpdateOrder() throws Exception {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-123", "Updated Product", 3, new BigDecimal("100.00")));

    UpdateOrderRequest request = new UpdateOrderRequest(items);
    Order updated = createTestOrder();

    when(orderService.update(eq("order-123"), any(UpdateOrderRequest.class)))
        .thenReturn(Optional.of(updated));

    mockMvc
        .perform(
            put("/api/orders/order-123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("order-123"));
  }

  @Test
  void testUpdateOrderNotFound() throws Exception {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-123", "Product", 1, new BigDecimal("100.00")));

    UpdateOrderRequest request = new UpdateOrderRequest(items);

    when(orderService.update(eq("non-existent"), any(UpdateOrderRequest.class)))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(
            put("/api/orders/non-existent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }

  @Test
  void testUpdateOrderStatus() throws Exception {
    Order updated = createTestOrder();
    updated.setStatus(OrderStatus.CONFIRMED);

    when(orderService.updateStatus("order-123", OrderStatus.CONFIRMED))
        .thenReturn(Optional.of(updated));

    mockMvc
        .perform(put("/api/orders/order-123/status/CONFIRMED"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CONFIRMED"));
  }

  @Test
  void testUpdateOrderStatusNotFound() throws Exception {
    when(orderService.updateStatus("non-existent", OrderStatus.CONFIRMED))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(put("/api/orders/non-existent/status/CONFIRMED"))
        .andExpect(status().isNotFound());
  }

  @Test
  void testCancelOrder() throws Exception {
    when(orderService.cancel("order-123")).thenReturn(true);

    mockMvc.perform(delete("/api/orders/order-123")).andExpect(status().isNoContent());
  }

  @Test
  void testCancelOrderNotFound() throws Exception {
    when(orderService.cancel("non-existent")).thenReturn(false);

    mockMvc.perform(delete("/api/orders/non-existent")).andExpect(status().isNotFound());
  }
}
