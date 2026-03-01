package com.example.common.env;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class EnvironmentManagerTest {

  @Test
  void testGetEnvReturnsValue() {
    String path = EnvironmentManager.getEnv("PATH");
    assertNotNull(path);
  }

  @Test
  void testGetEnvReturnsNullForMissing() {
    String value = EnvironmentManager.getEnv("NON_EXISTENT_VAR_12345");
    assertNull(value);
  }

  @Test
  void testGetEnvOptionalPresent() {
    Optional<String> path = EnvironmentManager.getEnvOptional("PATH");
    assertTrue(path.isPresent());
  }

  @Test
  void testGetEnvOptionalEmpty() {
    Optional<String> value = EnvironmentManager.getEnvOptional("NON_EXISTENT_VAR_12345");
    assertFalse(value.isPresent());
  }

  @Test
  void testGetEnvOrDefaultWithValue() {
    String path = EnvironmentManager.getEnvOrDefault("PATH", "default");
    assertNotEquals("default", path);
  }

  @Test
  void testGetEnvOrDefaultWithDefault() {
    String value = EnvironmentManager.getEnvOrDefault("NON_EXISTENT_VAR_12345", "default");
    assertEquals("default", value);
  }

  @Test
  void testGetProperty() {
    System.setProperty("test.property", "testValue");
    String value = EnvironmentManager.getProperty("test.property");
    assertEquals("testValue", value);
    System.clearProperty("test.property");
  }

  @Test
  void testGetPropertyOptional() {
    System.setProperty("test.property.opt", "testValue");
    Optional<String> value = EnvironmentManager.getPropertyOptional("test.property.opt");
    assertTrue(value.isPresent());
    assertEquals("testValue", value.get());
    System.clearProperty("test.property.opt");
  }

  @Test
  void testGetPropertyOrDefault() {
    String value = EnvironmentManager.getPropertyOrDefault("non.existent", "default");
    assertEquals("default", value);
  }

  @Test
  void testIsDevelopment() {
    System.setProperty("spring.profiles.active", "development");
    assertTrue(EnvironmentManager.isDevelopment());
    System.clearProperty("spring.profiles.active");
  }

  @Test
  void testIsProduction() {
    System.setProperty("spring.profiles.active", "production");
    assertTrue(EnvironmentManager.isProduction());
    System.clearProperty("spring.profiles.active");
  }

  @Test
  void testIsTest() {
    System.setProperty("spring.profiles.active", "test");
    assertTrue(EnvironmentManager.isTest());
    System.clearProperty("spring.profiles.active");
  }

  @Test
  void testGetActiveProfileDefault() {
    System.clearProperty("spring.profiles.active");
    String profile = EnvironmentManager.getActiveProfile();
    assertNotNull(profile);
  }
}
