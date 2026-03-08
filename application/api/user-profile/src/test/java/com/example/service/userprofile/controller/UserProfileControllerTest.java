package com.example.service.userprofile.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.userprofile.config.SecurityConfig;
import com.example.service.userprofile.dto.CreateUserRequest;
import com.example.service.userprofile.dto.UpdateUserRequest;
import com.example.service.userprofile.model.UserProfile;
import com.example.service.userprofile.model.UserStatus;
import com.example.service.userprofile.service.UserProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserProfileController.class)
@Import(SecurityConfig.class)
class UserProfileControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private UserProfileService userProfileService;

  @MockBean private JwtDecoder jwtDecoder;

  private UserProfile createTestUser() {
    Instant now = Instant.now();
    return new UserProfile(
        "user-123", "test@example.com", "John", "Doe", "+1234567890", UserStatus.ACTIVE, now, now);
  }

  @Test
  void testGetAllUsers() throws Exception {
    UserProfile user = createTestUser();
    when(userProfileService.findAll()).thenReturn(Arrays.asList(user));

    mockMvc
        .perform(get("/api/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("user-123"))
        .andExpect(jsonPath("$[0].email").value("test@example.com"));
  }

  @Test
  void testGetUserById() throws Exception {
    UserProfile user = createTestUser();
    when(userProfileService.findById("user-123")).thenReturn(Optional.of(user));

    mockMvc
        .perform(get("/api/users/user-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("user-123"))
        .andExpect(jsonPath("$.firstName").value("John"));
  }

  @Test
  void testGetUserByIdNotFound() throws Exception {
    when(userProfileService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/users/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetUserByEmail() throws Exception {
    UserProfile user = createTestUser();
    when(userProfileService.findByEmail("test@example.com")).thenReturn(Optional.of(user));

    mockMvc
        .perform(get("/api/users/email/test@example.com"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("test@example.com"));
  }

  @Test
  void testGetUserByEmailNotFound() throws Exception {
    when(userProfileService.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/users/email/notfound@example.com")).andExpect(status().isNotFound());
  }

  @Test
  void testCreateUser() throws Exception {
    CreateUserRequest request =
        new CreateUserRequest("new@example.com", "Jane", "Smith", "+9876543210");
    UserProfile created = createTestUser();
    created.setEmail("new@example.com");

    when(userProfileService.emailExists("new@example.com")).thenReturn(false);
    when(userProfileService.create(any(CreateUserRequest.class))).thenReturn(created);

    mockMvc
        .perform(
            post("/api/users")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value("new@example.com"));
  }

  @Test
  void testCreateUserEmailConflict() throws Exception {
    CreateUserRequest request = new CreateUserRequest("exists@example.com", "Jane", "Smith", null);

    when(userProfileService.emailExists("exists@example.com")).thenReturn(true);

    mockMvc
        .perform(
            post("/api/users")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict());
  }

  @Test
  void testUpdateUser() throws Exception {
    UpdateUserRequest request =
        new UpdateUserRequest("Updated", "Name", "+1111111111", UserStatus.INACTIVE);
    UserProfile updated = createTestUser();
    updated.setFirstName("Updated");

    when(userProfileService.update(eq("user-123"), any(UpdateUserRequest.class)))
        .thenReturn(Optional.of(updated));

    mockMvc
        .perform(
            put("/api/users/user-123")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Updated"));
  }

  @Test
  void testUpdateUserNotFound() throws Exception {
    UpdateUserRequest request = new UpdateUserRequest("Updated", "Name", null, null);

    when(userProfileService.update(eq("non-existent"), any(UpdateUserRequest.class)))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(
            put("/api/users/non-existent")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }

  @Test
  void testDeleteUser() throws Exception {
    when(userProfileService.delete("user-123")).thenReturn(true);

    mockMvc.perform(delete("/api/users/user-123").with(jwt())).andExpect(status().isNoContent());
  }

  @Test
  void testDeleteUserNotFound() throws Exception {
    when(userProfileService.delete("non-existent")).thenReturn(false);

    mockMvc.perform(delete("/api/users/non-existent").with(jwt())).andExpect(status().isNotFound());
  }
}
