package com.example.service.endorsement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.endorsement.config.SecurityConfig;
import com.example.service.endorsement.dto.CreateEndorsementRequest;
import com.example.service.endorsement.model.Endorsement;
import com.example.service.endorsement.model.EndorsementStatus;
import com.example.service.endorsement.model.EndorsementType;
import com.example.service.endorsement.service.EndorsementService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(EndorsementController.class)
@Import(SecurityConfig.class)
class EndorsementControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private EndorsementService endorsementService;

  @MockBean private JwtDecoder jwtDecoder;

  private Endorsement createTestEndorsement() {
    Instant now = Instant.now();
    Instant effective = Instant.parse("2024-07-01T00:00:00Z");
    return new Endorsement(
        "end-123",
        "END-2024-00001",
        "order-456",
        "user-789",
        "prod-101",
        EndorsementType.ADD_COVERAGE,
        EndorsementStatus.PENDING,
        "Add dental coverage",
        null,
        effective,
        now,
        now);
  }

  @Test
  void testGetAllEndorsements() throws Exception {
    Endorsement endorsement = createTestEndorsement();
    when(endorsementService.findAll()).thenReturn(Arrays.asList(endorsement));

    mockMvc
        .perform(get("/api/endorsements"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("end-123"))
        .andExpect(jsonPath("$[0].endorsementNumber").value("END-2024-00001"));
  }

  @Test
  void testGetEndorsementById() throws Exception {
    Endorsement endorsement = createTestEndorsement();
    when(endorsementService.findById("end-123")).thenReturn(Optional.of(endorsement));

    mockMvc
        .perform(get("/api/endorsements/end-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("end-123"))
        .andExpect(jsonPath("$.orderId").value("order-456"));
  }

  @Test
  void testGetEndorsementByIdNotFound() throws Exception {
    when(endorsementService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/endorsements/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetEndorsementByNumber() throws Exception {
    Endorsement endorsement = createTestEndorsement();
    when(endorsementService.findByEndorsementNumber("END-2024-00001"))
        .thenReturn(Optional.of(endorsement));

    mockMvc
        .perform(get("/api/endorsements/number/END-2024-00001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.endorsementNumber").value("END-2024-00001"));
  }

  @Test
  void testGetEndorsementByNumberNotFound() throws Exception {
    when(endorsementService.findByEndorsementNumber("END-9999-99999")).thenReturn(Optional.empty());

    mockMvc
        .perform(get("/api/endorsements/number/END-9999-99999"))
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetEndorsementsByOrderId() throws Exception {
    Endorsement endorsement = createTestEndorsement();
    when(endorsementService.findByOrderId("order-456")).thenReturn(Arrays.asList(endorsement));

    mockMvc
        .perform(get("/api/endorsements/order/order-456"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].orderId").value("order-456"));
  }

  @Test
  void testGetEndorsementsByOrderIdEmpty() throws Exception {
    when(endorsementService.findByOrderId("order-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/endorsements/order/order-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testGetEndorsementsByUserId() throws Exception {
    Endorsement endorsement = createTestEndorsement();
    when(endorsementService.findByUserId("user-789")).thenReturn(Arrays.asList(endorsement));

    mockMvc
        .perform(get("/api/endorsements/user/user-789"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userId").value("user-789"));
  }

  @Test
  void testGetEndorsementsByUserIdEmpty() throws Exception {
    when(endorsementService.findByUserId("user-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/endorsements/user/user-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testCreateEndorsement() throws Exception {
    Instant effective = Instant.parse("2024-07-01T00:00:00Z");
    CreateEndorsementRequest request =
        new CreateEndorsementRequest(
            "order-456",
            "user-789",
            "prod-101",
            EndorsementType.ADD_COVERAGE,
            "Add dental coverage",
            effective);
    Endorsement created = createTestEndorsement();

    when(endorsementService.create(any(CreateEndorsementRequest.class))).thenReturn(created);

    mockMvc
        .perform(
            post("/api/endorsements")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("end-123"));
  }

  @Test
  void testApproveEndorsement() throws Exception {
    Endorsement approved = createTestEndorsement();
    approved.setStatus(EndorsementStatus.APPROVED);

    when(endorsementService.approve("end-123")).thenReturn(Optional.of(approved));

    mockMvc
        .perform(post("/api/endorsements/end-123/approve").with(jwt()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("APPROVED"));
  }

  @Test
  void testApproveEndorsementNotFound() throws Exception {
    when(endorsementService.approve("non-existent")).thenReturn(Optional.empty());

    mockMvc
        .perform(post("/api/endorsements/non-existent/approve").with(jwt()))
        .andExpect(status().isNotFound());
  }

  @Test
  void testRejectEndorsement() throws Exception {
    Endorsement rejected = createTestEndorsement();
    rejected.setStatus(EndorsementStatus.REJECTED);
    rejected.setRejectionReason("Policy expired");

    when(endorsementService.reject(eq("end-123"), eq("Policy expired")))
        .thenReturn(Optional.of(rejected));

    mockMvc
        .perform(
            post("/api/endorsements/end-123/reject").with(jwt()).param("reason", "Policy expired"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("REJECTED"))
        .andExpect(jsonPath("$.rejectionReason").value("Policy expired"));
  }

  @Test
  void testRejectEndorsementNotFound() throws Exception {
    when(endorsementService.reject(eq("non-existent"), any())).thenReturn(Optional.empty());

    mockMvc
        .perform(
            post("/api/endorsements/non-existent/reject").with(jwt()).param("reason", "reason"))
        .andExpect(status().isNotFound());
  }

  @Test
  void testApplyEndorsement() throws Exception {
    Endorsement applied = createTestEndorsement();
    applied.setStatus(EndorsementStatus.APPLIED);

    when(endorsementService.apply("end-123")).thenReturn(Optional.of(applied));

    mockMvc
        .perform(post("/api/endorsements/end-123/apply").with(jwt()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("APPLIED"));
  }

  @Test
  void testApplyEndorsementNotFound() throws Exception {
    when(endorsementService.apply("non-existent")).thenReturn(Optional.empty());

    mockMvc
        .perform(post("/api/endorsements/non-existent/apply").with(jwt()))
        .andExpect(status().isNotFound());
  }

  @Test
  void testDeleteEndorsement() throws Exception {
    when(endorsementService.delete("end-123")).thenReturn(true);

    mockMvc
        .perform(delete("/api/endorsements/end-123").with(jwt()))
        .andExpect(status().isNoContent());
  }

  @Test
  void testDeleteEndorsementNotFound() throws Exception {
    when(endorsementService.delete("non-existent")).thenReturn(false);

    mockMvc
        .perform(delete("/api/endorsements/non-existent").with(jwt()))
        .andExpect(status().isNotFound());
  }
}
