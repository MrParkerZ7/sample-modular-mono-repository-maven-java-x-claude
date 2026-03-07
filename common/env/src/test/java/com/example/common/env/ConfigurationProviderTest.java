package com.example.common.env;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConfigurationProviderTest {

  private ConfigurationProvider provider;

  @BeforeEach
  void setUp() {
    provider = new ConfigurationProvider();
  }

  @Test
  void testSetAndGet() {
    provider.set("key", "value");
    assertEquals("value", provider.get("key"));
  }

  @Test
  void testGetReturnsNullForMissing() {
    assertNull(provider.get("non.existent.key.12345"));
  }

  @Test
  void testGetOptionalPresent() {
    provider.set("key", "value");
    Optional<String> result = provider.getOptional("key");
    assertTrue(result.isPresent());
    assertEquals("value", result.get());
  }

  @Test
  void testGetOptionalEmpty() {
    Optional<String> result = provider.getOptional("non.existent.key.12345");
    assertFalse(result.isPresent());
  }

  @Test
  void testGetOrDefault() {
    assertEquals("default", provider.getOrDefault("missing", "default"));
    provider.set("key", "value");
    assertEquals("value", provider.getOrDefault("key", "default"));
  }

  @Test
  void testGetInt() {
    provider.set("number", "42");
    assertEquals(42, provider.getInt("number", 0));
  }

  @Test
  void testGetIntDefault() {
    assertEquals(10, provider.getInt("missing", 10));
  }

  @Test
  void testGetIntInvalidFormat() {
    provider.set("invalid", "not-a-number");
    assertEquals(10, provider.getInt("invalid", 10));
  }

  @Test
  void testGetBoolean() {
    provider.set("enabled", "true");
    assertTrue(provider.getBoolean("enabled", false));
  }

  @Test
  void testGetBooleanDefault() {
    assertTrue(provider.getBoolean("missing", true));
    assertFalse(provider.getBoolean("missing", false));
  }

  @Test
  void testContainsKey() {
    assertFalse(provider.containsKey("key"));
    provider.set("key", "value");
    assertTrue(provider.containsKey("key"));
  }

  @Test
  void testClear() {
    provider.set("key", "value");
    provider.clear();
    assertNull(provider.get("key"));
  }

  @Test
  void testConstructorWithInitialConfig() {
    Map<String, String> initial = new HashMap<>();
    initial.put("key1", "value1");
    initial.put("key2", "value2");

    ConfigurationProvider providerWithConfig = new ConfigurationProvider(initial);

    assertEquals("value1", providerWithConfig.get("key1"));
    assertEquals("value2", providerWithConfig.get("key2"));
  }
}
