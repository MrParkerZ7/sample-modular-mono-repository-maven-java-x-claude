package com.example.infra;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;

class InfraAppTest {

  @Test
  void testMain() {
    assertDoesNotThrow(() -> InfraApp.main(new String[] {}));
  }

  @Test
  void testPrivateConstructor() throws Exception {
    Constructor<InfraApp> constructor = InfraApp.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    assertNotNull(constructor.newInstance());
  }
}
