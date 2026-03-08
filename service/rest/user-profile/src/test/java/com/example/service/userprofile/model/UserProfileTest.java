package com.example.service.userprofile.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class UserProfileTest {

  @Test
  void testDefaultConstructor() {
    UserProfile user = new UserProfile();
    assertNull(user.getId());
    assertNull(user.getEmail());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    UserProfile user =
        new UserProfile(
            "id-123",
            "test@example.com",
            "John",
            "Doe",
            "+1234567890",
            UserStatus.ACTIVE,
            now,
            now);

    assertEquals("id-123", user.getId());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("John", user.getFirstName());
    assertEquals("Doe", user.getLastName());
    assertEquals("+1234567890", user.getPhoneNumber());
    assertEquals(UserStatus.ACTIVE, user.getStatus());
    assertEquals(now, user.getCreatedAt());
    assertEquals(now, user.getUpdatedAt());
  }

  @Test
  void testSetters() {
    UserProfile user = new UserProfile();
    Instant now = Instant.now();

    user.setId("id-456");
    user.setEmail("updated@example.com");
    user.setFirstName("Jane");
    user.setLastName("Smith");
    user.setPhoneNumber("+0987654321");
    user.setStatus(UserStatus.INACTIVE);
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    assertEquals("id-456", user.getId());
    assertEquals("updated@example.com", user.getEmail());
    assertEquals("Jane", user.getFirstName());
    assertEquals("Smith", user.getLastName());
    assertEquals("+0987654321", user.getPhoneNumber());
    assertEquals(UserStatus.INACTIVE, user.getStatus());
    assertEquals(now, user.getCreatedAt());
    assertEquals(now, user.getUpdatedAt());
  }

  @Test
  void testEqualsAndHashCode() {
    UserProfile user1 = new UserProfile();
    user1.setId("same-id");

    UserProfile user2 = new UserProfile();
    user2.setId("same-id");

    UserProfile user3 = new UserProfile();
    user3.setId("different-id");

    assertEquals(user1, user2);
    assertEquals(user1.hashCode(), user2.hashCode());
    assertNotEquals(user1, user3);
    assertNotEquals(user1, null);
    assertNotEquals(user1, new Object());
    assertEquals(user1, user1);
  }
}
