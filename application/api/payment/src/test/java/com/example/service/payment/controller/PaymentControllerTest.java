package com.example.service.payment.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.payment.config.SecurityConfig;
import com.example.service.payment.dto.CreatePaymentRequest;
import com.example.service.payment.model.Payment;
import com.example.service.payment.model.PaymentMethod;
import com.example.service.payment.model.PaymentStatus;
import com.example.service.payment.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PaymentController.class)
@Import(SecurityConfig.class)
class PaymentControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private PaymentService paymentService;

  @MockBean private JwtDecoder jwtDecoder;

  private Payment createTestPayment() {
    Instant now = Instant.now();
    return new Payment(
        "pay-123",
        "TXN-2024-00001",
        "order-456",
        "user-789",
        new BigDecimal("108.50"),
        "USD",
        PaymentMethod.CREDIT_CARD,
        PaymentStatus.COMPLETED,
        null,
        now,
        now);
  }

  @Test
  void testGetAllPayments() throws Exception {
    Payment payment = createTestPayment();
    when(paymentService.findAll()).thenReturn(Arrays.asList(payment));

    mockMvc
        .perform(get("/api/payments"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("pay-123"))
        .andExpect(jsonPath("$[0].transactionId").value("TXN-2024-00001"));
  }

  @Test
  void testGetPaymentById() throws Exception {
    Payment payment = createTestPayment();
    when(paymentService.findById("pay-123")).thenReturn(Optional.of(payment));

    mockMvc
        .perform(get("/api/payments/pay-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("pay-123"))
        .andExpect(jsonPath("$.orderId").value("order-456"));
  }

  @Test
  void testGetPaymentByIdNotFound() throws Exception {
    when(paymentService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/payments/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetPaymentByTransactionId() throws Exception {
    Payment payment = createTestPayment();
    when(paymentService.findByTransactionId("TXN-2024-00001")).thenReturn(Optional.of(payment));

    mockMvc
        .perform(get("/api/payments/transaction/TXN-2024-00001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.transactionId").value("TXN-2024-00001"));
  }

  @Test
  void testGetPaymentByTransactionIdNotFound() throws Exception {
    when(paymentService.findByTransactionId("TXN-9999-99999")).thenReturn(Optional.empty());

    mockMvc
        .perform(get("/api/payments/transaction/TXN-9999-99999"))
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetPaymentsByOrderId() throws Exception {
    Payment payment = createTestPayment();
    when(paymentService.findByOrderId("order-456")).thenReturn(Arrays.asList(payment));

    mockMvc
        .perform(get("/api/payments/order/order-456"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].orderId").value("order-456"));
  }

  @Test
  void testGetPaymentsByOrderIdEmpty() throws Exception {
    when(paymentService.findByOrderId("order-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/payments/order/order-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testGetPaymentsByUserId() throws Exception {
    Payment payment = createTestPayment();
    when(paymentService.findByUserId("user-789")).thenReturn(Arrays.asList(payment));

    mockMvc
        .perform(get("/api/payments/user/user-789"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userId").value("user-789"));
  }

  @Test
  void testGetPaymentsByUserIdEmpty() throws Exception {
    when(paymentService.findByUserId("user-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/payments/user/user-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testProcessPayment() throws Exception {
    CreatePaymentRequest request =
        new CreatePaymentRequest(
            "order-456", "user-789", new BigDecimal("108.50"), "USD", PaymentMethod.CREDIT_CARD);
    Payment processed = createTestPayment();

    when(paymentService.process(any(CreatePaymentRequest.class))).thenReturn(processed);

    mockMvc
        .perform(
            post("/api/payments")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("pay-123"));
  }

  @Test
  void testRefundPayment() throws Exception {
    Payment refunded = createTestPayment();
    refunded.setStatus(PaymentStatus.REFUNDED);

    when(paymentService.refund("pay-123")).thenReturn(Optional.of(refunded));

    mockMvc
        .perform(post("/api/payments/pay-123/refund").with(jwt()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("REFUNDED"));
  }

  @Test
  void testRefundPaymentNotFound() throws Exception {
    when(paymentService.refund("non-existent")).thenReturn(Optional.empty());

    mockMvc
        .perform(post("/api/payments/non-existent/refund").with(jwt()))
        .andExpect(status().isNotFound());
  }

  @Test
  void testCancelPayment() throws Exception {
    when(paymentService.cancel("pay-123")).thenReturn(true);

    mockMvc.perform(delete("/api/payments/pay-123").with(jwt())).andExpect(status().isNoContent());
  }

  @Test
  void testCancelPaymentNotFound() throws Exception {
    when(paymentService.cancel("non-existent")).thenReturn(false);

    mockMvc
        .perform(delete("/api/payments/non-existent").with(jwt()))
        .andExpect(status().isNotFound());
  }
}
