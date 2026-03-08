package com.example.service.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchApplicationTest {

  @Test
  void contextLoads() {
    assertTrue(true);
  }

  @Test
  void testMain() {
    assertDoesNotThrow(() -> BatchApplication.main(new String[] {}));
  }
}
