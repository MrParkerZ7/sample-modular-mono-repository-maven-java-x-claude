package com.example.service.oauth.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

@SpringBootTest(properties = "server.port=0")
class DefaultSecurityConfigTest {

  @Autowired private UserDetailsService userDetailsService;

  @Test
  void testUserDetailsService() {
    assertNotNull(userDetailsService);
    assertNotNull(userDetailsService.loadUserByUsername("admin"));
  }

  @Test
  void testAdminUserHasRole() {
    var user = userDetailsService.loadUserByUsername("admin");
    assertTrue(user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
  }
}
