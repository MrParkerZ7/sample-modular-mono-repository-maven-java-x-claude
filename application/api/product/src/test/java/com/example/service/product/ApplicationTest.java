package com.example.service.product;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@SpringBootTest
class ApplicationTest {

  @MockBean JwtDecoder jwtDecoder;

  @Test
  void contextLoads() {
    assertTrue(true);
  }

  @Test
  void testMain() {
    assertDoesNotThrow(() -> Application.main(new String[] {"--server.port=0"}));
  }
}
