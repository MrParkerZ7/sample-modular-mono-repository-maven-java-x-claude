package com.example.infra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;

class MainStackTest {

  @Test
  void testStackCreation() {
    App app = new App();
    MainStack stack = new MainStack(app, "TestStack", null);

    assertNotNull(stack);
    assertNotNull(stack.getVpc());
    assertNotNull(stack.getBucket());
    assertNotNull(stack.getQueue());
    assertNotNull(stack.getTable());
    assertNotNull(stack.getDatabase());
    assertNotNull(stack.getRestApi());
    assertNotNull(stack.getSoapApi());
  }

  @Test
  void testStackSynthesis() {
    App app = new App();
    MainStack stack = new MainStack(app, "TestStack2", null);

    assertDoesNotThrow(() -> app.synth());
    assertNotNull(stack.getVpc());
    assertNotNull(stack.getBucket());
    assertNotNull(stack.getQueue());
    assertNotNull(stack.getTable());
    assertNotNull(stack.getDatabase());
    assertNotNull(stack.getRestApi());
    assertNotNull(stack.getSoapApi());
  }
}
