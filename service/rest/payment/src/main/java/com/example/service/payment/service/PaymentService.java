package com.example.service.payment.service;

import com.example.service.payment.dto.CreatePaymentRequest;
import com.example.service.payment.model.Payment;
import java.util.List;
import java.util.Optional;

/** Service interface for payment operations. */
public interface PaymentService {

  /**
   * Retrieves all payments.
   *
   * @return list of all payments
   */
  List<Payment> findAll();

  /**
   * Finds a payment by ID.
   *
   * @param id the payment ID
   * @return optional containing the payment if found
   */
  Optional<Payment> findById(String id);

  /**
   * Finds a payment by transaction ID.
   *
   * @param transactionId the transaction ID
   * @return optional containing the payment if found
   */
  Optional<Payment> findByTransactionId(String transactionId);

  /**
   * Finds all payments for a specific order.
   *
   * @param orderId the order ID
   * @return list of payments for the order
   */
  List<Payment> findByOrderId(String orderId);

  /**
   * Finds all payments for a specific user.
   *
   * @param userId the user ID
   * @return list of payments for the user
   */
  List<Payment> findByUserId(String userId);

  /**
   * Processes a new payment.
   *
   * @param request the create payment request
   * @return the processed payment
   */
  Payment process(CreatePaymentRequest request);

  /**
   * Refunds a payment.
   *
   * @param id the payment ID
   * @return optional containing the refunded payment if found
   */
  Optional<Payment> refund(String id);

  /**
   * Cancels a payment.
   *
   * @param id the payment ID
   * @return true if cancelled, false if not found
   */
  boolean cancel(String id);
}
