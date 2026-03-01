package com.example.service.soap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SoapApplicationTest {

  @Test
  void contextLoads() {
    assertTrue(true);
  }

  @Test
  void testMain() {
    assertDoesNotThrow(() -> SoapApplication.main(new String[] {"--server.port=0"}));
  }
}
