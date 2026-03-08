package com.example.service.payment.service;

import com.example.service.payment.dto.CreatePaymentRequest;
import com.example.service.payment.model.Payment;
import com.example.service.payment.model.PaymentStatus;
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

/** Implementation of PaymentService using in-memory storage. */
@Service
public class PaymentServiceImpl implements PaymentService {

  private final Map<String, Payment> payments = new ConcurrentHashMap<>();
  private final Map<String, String> transactionIndex = new ConcurrentHashMap<>();
  private final AtomicLong transactionCounter = new AtomicLong(0);

  @Override
  public List<Payment> findAll() {
    return new ArrayList<>(payments.values());
  }

  @Override
  public Optional<Payment> findById(String id) {
    return Optional.ofNullable(payments.get(id));
  }

  @Override
  public Optional<Payment> findByTransactionId(String transactionId) {
    String paymentId = transactionIndex.get(transactionId);
    if (paymentId == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(payments.get(paymentId));
  }

  @Override
  public List<Payment> findByOrderId(String orderId) {
    return payments.values().stream()
        .filter(p -> orderId.equals(p.getOrderId()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Payment> findByUserId(String userId) {
    return payments.values().stream()
        .filter(p -> userId.equals(p.getUserId()))
        .collect(Collectors.toList());
  }

  @Override
  public Payment process(CreatePaymentRequest request) {
    String id = UUID.randomUUID().toString();
    String transactionId = generateTransactionId();
    Instant now = Instant.now();

    Payment payment = new Payment();
    payment.setId(id);
    payment.setTransactionId(transactionId);
    payment.setOrderId(request.getOrderId());
    payment.setUserId(request.getUserId());
    payment.setAmount(request.getAmount());
    payment.setCurrency(request.getCurrency());
    payment.setPaymentMethod(request.getPaymentMethod());
    payment.setStatus(PaymentStatus.COMPLETED);
    payment.setCreatedAt(now);
    payment.setUpdatedAt(now);

    payments.put(id, payment);
    transactionIndex.put(transactionId, id);

    return payment;
  }

  @Override
  public Optional<Payment> refund(String id) {
    Payment existing = payments.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    existing.setStatus(PaymentStatus.REFUNDED);
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public boolean cancel(String id) {
    Payment existing = payments.get(id);
    if (existing == null) {
      return false;
    }

    existing.setStatus(PaymentStatus.CANCELLED);
    existing.setUpdatedAt(Instant.now());
    return true;
  }

  private String generateTransactionId() {
    long count = transactionCounter.incrementAndGet();
    return String.format("TXN-%d-%05d", Year.now().getValue(), count);
  }
}
