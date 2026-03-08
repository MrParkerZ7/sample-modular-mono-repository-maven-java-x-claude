package com.example.service.useractivity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.useractivity.config.SecurityConfig;
import com.example.service.useractivity.dto.CreateActivityRequest;
import com.example.service.useractivity.model.ActivityType;
import com.example.service.useractivity.model.UserActivity;
import com.example.service.useractivity.service.UserActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserActivityController.class)
@Import(SecurityConfig.class)
class UserActivityControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private UserActivityService userActivityService;

  @MockBean private JwtDecoder jwtDecoder;

  private UserActivity createTestActivity() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("key", "value");
    return new UserActivity(
        "activity-123",
        "user-456",
        ActivityType.PAGE_VIEW,
        "/dashboard",
        metadata,
        Instant.now(),
        "192.168.1.1",
        "Mozilla/5.0");
  }

  @Test
  void testGetAllActivities() throws Exception {
    UserActivity activity = createTestActivity();
    when(userActivityService.findAll()).thenReturn(Arrays.asList(activity));

    mockMvc
        .perform(get("/api/activities"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("activity-123"))
        .andExpect(jsonPath("$[0].userId").value("user-456"));
  }

  @Test
  void testGetActivityById() throws Exception {
    UserActivity activity = createTestActivity();
    when(userActivityService.findById("activity-123")).thenReturn(Optional.of(activity));

    mockMvc
        .perform(get("/api/activities/activity-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("activity-123"))
        .andExpect(jsonPath("$.activityType").value("PAGE_VIEW"));
  }

  @Test
  void testGetActivityByIdNotFound() throws Exception {
    when(userActivityService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/activities/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetActivitiesByUserId() throws Exception {
    UserActivity activity = createTestActivity();
    when(userActivityService.findByUserId("user-456")).thenReturn(Arrays.asList(activity));

    mockMvc
        .perform(get("/api/activities/user/user-456"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userId").value("user-456"));
  }

  @Test
  void testGetActivitiesByUserIdEmpty() throws Exception {
    when(userActivityService.findByUserId("user-999")).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/activities/user/user-999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testCreateActivity() throws Exception {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("action", "submit");

    CreateActivityRequest request =
        new CreateActivityRequest(
            "user-123", ActivityType.FORM_SUBMIT, "/contact", metadata, "10.0.0.1", "Chrome/120");
    UserActivity created = createTestActivity();

    when(userActivityService.create(any(CreateActivityRequest.class))).thenReturn(created);

    mockMvc
        .perform(
            post("/api/activities")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("activity-123"));
  }

  @Test
  void testDeleteActivity() throws Exception {
    when(userActivityService.delete("activity-123")).thenReturn(true);

    mockMvc
        .perform(delete("/api/activities/activity-123").with(jwt()))
        .andExpect(status().isNoContent());
  }

  @Test
  void testDeleteActivityNotFound() throws Exception {
    when(userActivityService.delete("non-existent")).thenReturn(false);

    mockMvc
        .perform(delete("/api/activities/non-existent").with(jwt()))
        .andExpect(status().isNotFound());
  }
}
