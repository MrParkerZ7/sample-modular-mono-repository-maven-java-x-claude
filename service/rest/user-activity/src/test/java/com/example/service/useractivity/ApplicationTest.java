package com.example.service.useractivity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

  @Test
  void contextLoads() {
    assertTrue(true);
  }

  @Test
  void testMain() {
    assertDoesNotThrow(() -> Application.main(new String[] {"--server.port=0"}));
  }
}
