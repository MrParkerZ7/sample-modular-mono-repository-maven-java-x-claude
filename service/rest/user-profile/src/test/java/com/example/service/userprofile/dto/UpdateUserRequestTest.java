package com.example.service.userprofile.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.userprofile.model.UserStatus;
import org.junit.jupiter.api.Test;

class UpdateUserRequestTest {

  @Test
  void testDefaultConstructor() {
    UpdateUserRequest request = new UpdateUserRequest();
    assertNull(request.getFirstName());
    assertNull(request.getLastName());
    assertNull(request.getPhoneNumber());
    assertNull(request.getStatus());
  }

  @Test
  void testFullConstructor() {
    UpdateUserRequest request =
        new UpdateUserRequest("John", "Doe", "+1234567890", UserStatus.ACTIVE);

    assertEquals("John", request.getFirstName());
    assertEquals("Doe", request.getLastName());
    assertEquals("+1234567890", request.getPhoneNumber());
    assertEquals(UserStatus.ACTIVE, request.getStatus());
  }

  @Test
  void testSetters() {
    UpdateUserRequest request = new UpdateUserRequest();

    request.setFirstName("Jane");
    request.setLastName("Smith");
    request.setPhoneNumber("+0987654321");
    request.setStatus(UserStatus.INACTIVE);

    assertEquals("Jane", request.getFirstName());
    assertEquals("Smith", request.getLastName());
    assertEquals("+0987654321", request.getPhoneNumber());
    assertEquals(UserStatus.INACTIVE, request.getStatus());
  }
}
