package com.example.service.userprofile.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreateUserRequestTest {

  @Test
  void testDefaultConstructor() {
    CreateUserRequest request = new CreateUserRequest();
    assertNull(request.getEmail());
    assertNull(request.getFirstName());
    assertNull(request.getLastName());
    assertNull(request.getPhoneNumber());
  }

  @Test
  void testFullConstructor() {
    CreateUserRequest request =
        new CreateUserRequest("test@example.com", "John", "Doe", "+1234567890");

    assertEquals("test@example.com", request.getEmail());
    assertEquals("John", request.getFirstName());
    assertEquals("Doe", request.getLastName());
    assertEquals("+1234567890", request.getPhoneNumber());
  }

  @Test
  void testSetters() {
    CreateUserRequest request = new CreateUserRequest();

    request.setEmail("updated@example.com");
    request.setFirstName("Jane");
    request.setLastName("Smith");
    request.setPhoneNumber("+0987654321");

    assertEquals("updated@example.com", request.getEmail());
    assertEquals("Jane", request.getFirstName());
    assertEquals("Smith", request.getLastName());
    assertEquals("+0987654321", request.getPhoneNumber());
  }
}
