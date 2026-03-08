package com.example.service.order.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.order.dto.CreateOrderRequest;
import com.example.service.order.dto.OrderItemRequest;
import com.example.service.order.dto.UpdateOrderRequest;
import com.example.service.order.model.Order;
import com.example.service.order.model.OrderStatus;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceImplTest {

  private OrderServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new OrderServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<Order> orders = service.findAll();
    assertTrue(orders.isEmpty());
  }

  @Test
  void testCreateAndFindById() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-123", "Test Product", 2, new BigDecimal("100.00")));

    CreateOrderRequest request = new CreateOrderRequest("user-456", items);

    Order created = service.create(request);

    assertNotNull(created.getId());
    assertNotNull(created.getOrderNumber());
    assertTrue(created.getOrderNumber().startsWith("ORD-"));
    assertEquals("user-456", created.getUserId());
    assertEquals(1, created.getItems().size());
    assertEquals(new BigDecimal("200.00"), created.getSubtotal());
    assertEquals(new BigDecimal("17.00"), created.getTax());
    assertEquals(new BigDecimal("217.00"), created.getTotal());
    assertEquals(OrderStatus.PENDING, created.getStatus());
    assertNotNull(created.getCreatedAt());
    assertNotNull(created.getUpdatedAt());

    Optional<Order> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Order> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByOrderNumber() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("50.00")));

    CreateOrderRequest request = new CreateOrderRequest("user-123", items);
    Order created = service.create(request);

    Optional<Order> found = service.findByOrderNumber(created.getOrderNumber());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByOrderNumberNotFound() {
    Optional<Order> found = service.findByOrderNumber("ORD-9999-99999");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByUserId() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("50.00")));

    service.create(new CreateOrderRequest("user-123", items));
    service.create(new CreateOrderRequest("user-123", items));
    service.create(new CreateOrderRequest("user-456", items));

    List<Order> user123Orders = service.findByUserId("user-123");
    assertEquals(2, user123Orders.size());

    List<Order> user456Orders = service.findByUserId("user-456");
    assertEquals(1, user456Orders.size());

    List<Order> nonExistentOrders = service.findByUserId("user-999");
    assertTrue(nonExistentOrders.isEmpty());
  }

  @Test
  void testFindAll() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("50.00")));

    service.create(new CreateOrderRequest("user-1", items));
    service.create(new CreateOrderRequest("user-2", items));

    List<Order> orders = service.findAll();
    assertEquals(2, orders.size());
  }

  @Test
  void testUpdate() {
    List<OrderItemRequest> initialItems = new ArrayList<>();
    initialItems.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    Order created = service.create(new CreateOrderRequest("user-123", initialItems));

    List<OrderItemRequest> updatedItems = new ArrayList<>();
    updatedItems.add(new OrderItemRequest("prod-1", "Product 1", 3, new BigDecimal("100.00")));

    UpdateOrderRequest updateRequest = new UpdateOrderRequest(updatedItems);
    Optional<Order> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals(new BigDecimal("300.00"), updated.get().getSubtotal());
    assertEquals(new BigDecimal("25.50"), updated.get().getTax());
    assertEquals(new BigDecimal("325.50"), updated.get().getTotal());
  }

  @Test
  void testUpdateWithEmptyItems() {
    List<OrderItemRequest> initialItems = new ArrayList<>();
    initialItems.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    Order created = service.create(new CreateOrderRequest("user-123", initialItems));
    BigDecimal originalSubtotal = created.getSubtotal();

    UpdateOrderRequest updateRequest = new UpdateOrderRequest(new ArrayList<>());
    Optional<Order> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals(originalSubtotal, updated.get().getSubtotal());
  }

  @Test
  void testUpdateNotFound() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    UpdateOrderRequest updateRequest = new UpdateOrderRequest(items);
    Optional<Order> updated = service.update("non-existent-id", updateRequest);
    assertTrue(updated.isEmpty());
  }

  @Test
  void testUpdateStatus() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    Order created = service.create(new CreateOrderRequest("user-123", items));

    Optional<Order> updated = service.updateStatus(created.getId(), OrderStatus.CONFIRMED);

    assertTrue(updated.isPresent());
    assertEquals(OrderStatus.CONFIRMED, updated.get().getStatus());
  }

  @Test
  void testUpdateStatusNotFound() {
    Optional<Order> updated = service.updateStatus("non-existent-id", OrderStatus.CONFIRMED);
    assertTrue(updated.isEmpty());
  }

  @Test
  void testCancel() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 1, new BigDecimal("100.00")));

    Order created = service.create(new CreateOrderRequest("user-123", items));

    assertTrue(service.cancel(created.getId()));

    Optional<Order> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(OrderStatus.CANCELLED, found.get().getStatus());
  }

  @Test
  void testCancelNotFound() {
    assertFalse(service.cancel("non-existent-id"));
  }

  @Test
  void testMultipleItems() {
    List<OrderItemRequest> items = new ArrayList<>();
    items.add(new OrderItemRequest("prod-1", "Product 1", 2, new BigDecimal("50.00")));
    items.add(new OrderItemRequest("prod-2", "Product 2", 1, new BigDecimal("75.00")));

    Order created = service.create(new CreateOrderRequest("user-123", items));

    assertEquals(2, created.getItems().size());
    assertEquals(new BigDecimal("175.00"), created.getSubtotal());
    assertEquals(new BigDecimal("14.88"), created.getTax());
    assertEquals(new BigDecimal("189.88"), created.getTotal());
  }
}
