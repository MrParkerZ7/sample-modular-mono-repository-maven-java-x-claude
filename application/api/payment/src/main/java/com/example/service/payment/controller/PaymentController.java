package com.example.service.payment.controller;

import com.example.service.payment.dto.CreatePaymentRequest;
import com.example.service.payment.model.Payment;
import com.example.service.payment.service.PaymentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for payment operations. */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

  private final PaymentService paymentService;

  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @GetMapping
  public ResponseEntity<List<Payment>> getAllPayments() {
    return ResponseEntity.ok(paymentService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Payment> getPaymentById(@PathVariable String id) {
    return paymentService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/transaction/{transactionId}")
  public ResponseEntity<Payment> getPaymentByTransactionId(@PathVariable String transactionId) {
    return paymentService
        .findByTransactionId(transactionId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/order/{orderId}")
  public ResponseEntity<List<Payment>> getPaymentsByOrderId(@PathVariable String orderId) {
    return ResponseEntity.ok(paymentService.findByOrderId(orderId));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Payment>> getPaymentsByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(paymentService.findByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<Payment> processPayment(@RequestBody CreatePaymentRequest request) {
    Payment processed = paymentService.process(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(processed);
  }

  @PostMapping("/{id}/refund")
  public ResponseEntity<Payment> refundPayment(@PathVariable String id) {
    return paymentService
        .refund(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> cancelPayment(@PathVariable String id) {
    if (paymentService.cancel(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
