package com.example.service.order.service;

import com.example.service.order.dto.CreateOrderRequest;
import com.example.service.order.dto.OrderItemRequest;
import com.example.service.order.dto.UpdateOrderRequest;
import com.example.service.order.model.Order;
import com.example.service.order.model.OrderItem;
import com.example.service.order.model.OrderStatus;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** Implementation of OrderService using in-memory storage. */
@Service
public class OrderServiceImpl implements OrderService {

  private static final BigDecimal TAX_RATE = new BigDecimal("0.085");

  private final Map<String, Order> orders = new ConcurrentHashMap<>();
  private final Map<String, String> orderNumberIndex = new ConcurrentHashMap<>();
  private final AtomicLong orderCounter = new AtomicLong(0);

  @Override
  public List<Order> findAll() {
    return new ArrayList<>(orders.values());
  }

  @Override
  public Optional<Order> findById(String id) {
    return Optional.ofNullable(orders.get(id));
  }

  @Override
  public Optional<Order> findByOrderNumber(String orderNumber) {
    String orderId = orderNumberIndex.get(orderNumber);
    if (orderId == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(orders.get(orderId));
  }

  @Override
  public List<Order> findByUserId(String userId) {
    return orders.values().stream()
        .filter(o -> userId.equals(o.getUserId()))
        .collect(Collectors.toList());
  }

  @Override
  public Order create(CreateOrderRequest request) {
    String id = UUID.randomUUID().toString();
    String orderNumber = generateOrderNumber();
    Instant now = Instant.now();

    List<OrderItem> items = convertToOrderItems(request.getItems());
    BigDecimal subtotal = calculateSubtotal(items);
    BigDecimal tax = subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
    BigDecimal total = subtotal.add(tax);

    Order order = new Order();
    order.setId(id);
    order.setOrderNumber(orderNumber);
    order.setUserId(request.getUserId());
    order.setItems(items);
    order.setSubtotal(subtotal);
    order.setTax(tax);
    order.setTotal(total);
    order.setStatus(OrderStatus.PENDING);
    order.setCreatedAt(now);
    order.setUpdatedAt(now);

    orders.put(id, order);
    orderNumberIndex.put(orderNumber, id);

    return order;
  }

  @Override
  public Optional<Order> update(String id, UpdateOrderRequest request) {
    Order existing = orders.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    if (request.getItems() != null && !request.getItems().isEmpty()) {
      List<OrderItem> items = convertToOrderItems(request.getItems());
      existing.setItems(items);

      BigDecimal subtotal = calculateSubtotal(items);
      BigDecimal tax = subtotal.multiply(TAX_RATE).setScale(2, RoundingMode.HALF_UP);
      BigDecimal total = subtotal.add(tax);

      existing.setSubtotal(subtotal);
      existing.setTax(tax);
      existing.setTotal(total);
    }
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public Optional<Order> updateStatus(String id, OrderStatus status) {
    Order existing = orders.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    existing.setStatus(status);
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public boolean cancel(String id) {
    Order existing = orders.get(id);
    if (existing == null) {
      return false;
    }

    existing.setStatus(OrderStatus.CANCELLED);
    existing.setUpdatedAt(Instant.now());
    return true;
  }

  private String generateOrderNumber() {
    long count = orderCounter.incrementAndGet();
    return String.format("ORD-%d-%05d", Year.now().getValue(), count);
  }

  private List<OrderItem> convertToOrderItems(List<OrderItemRequest> itemRequests) {
    return itemRequests.stream()
        .map(
            req -> {
              OrderItem item = new OrderItem();
              item.setId(UUID.randomUUID().toString());
              item.setProductId(req.getProductId());
              item.setProductName(req.getProductName());
              item.setQuantity(req.getQuantity());
              item.setUnitPrice(req.getUnitPrice());
              item.setTotalPrice(
                  req.getUnitPrice()
                      .multiply(BigDecimal.valueOf(req.getQuantity()))
                      .setScale(2, RoundingMode.HALF_UP));
              return item;
            })
        .collect(Collectors.toList());
  }

  private BigDecimal calculateSubtotal(List<OrderItem> items) {
    return items.stream().map(OrderItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
