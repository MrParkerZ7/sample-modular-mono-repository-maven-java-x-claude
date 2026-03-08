package com.example.service.userprofile.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserStatusTest {

  @Test
  void testEnumValues() {
    UserStatus[] values = UserStatus.values();
    assertEquals(4, values.length);
    assertEquals(UserStatus.ACTIVE, UserStatus.valueOf("ACTIVE"));
    assertEquals(UserStatus.INACTIVE, UserStatus.valueOf("INACTIVE"));
    assertEquals(UserStatus.SUSPENDED, UserStatus.valueOf("SUSPENDED"));
    assertEquals(UserStatus.PENDING_VERIFICATION, UserStatus.valueOf("PENDING_VERIFICATION"));
  }
}
