package com.example.service.order.service;

import com.example.service.order.dto.CreateOrderRequest;
import com.example.service.order.dto.UpdateOrderRequest;
import com.example.service.order.model.Order;
import com.example.service.order.model.OrderStatus;
import java.util.List;
import java.util.Optional;

/** Service interface for order operations. */
public interface OrderService {

  /**
   * Retrieves all orders.
   *
   * @return list of all orders
   */
  List<Order> findAll();

  /**
   * Finds an order by ID.
   *
   * @param id the order ID
   * @return optional containing the order if found
   */
  Optional<Order> findById(String id);

  /**
   * Finds an order by order number.
   *
   * @param orderNumber the order number
   * @return optional containing the order if found
   */
  Optional<Order> findByOrderNumber(String orderNumber);

  /**
   * Finds all orders for a specific user.
   *
   * @param userId the user ID
   * @return list of orders for the user
   */
  List<Order> findByUserId(String userId);

  /**
   * Creates a new order.
   *
   * @param request the create order request
   * @return the created order
   */
  Order create(CreateOrderRequest request);

  /**
   * Updates an existing order.
   *
   * @param id the order ID
   * @param request the update order request
   * @return optional containing the updated order if found
   */
  Optional<Order> update(String id, UpdateOrderRequest request);

  /**
   * Updates the status of an order.
   *
   * @param id the order ID
   * @param status the new status
   * @return optional containing the updated order if found
   */
  Optional<Order> updateStatus(String id, OrderStatus status);

  /**
   * Cancels an order by ID.
   *
   * @param id the order ID
   * @return true if cancelled, false if not found
   */
  boolean cancel(String id);
}
