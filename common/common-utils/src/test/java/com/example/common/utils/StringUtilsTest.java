package com.example.common.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

  @Test
  void testIsBlankWithNull() {
    assertTrue(StringUtils.isBlank(null));
  }

  @Test
  void testIsBlankWithEmpty() {
    assertTrue(StringUtils.isBlank(""));
  }

  @Test
  void testIsBlankWithWhitespace() {
    assertTrue(StringUtils.isBlank("   "));
  }

  @Test
  void testIsBlankWithText() {
    assertFalse(StringUtils.isBlank("text"));
  }

  @Test
  void testIsNotBlank() {
    assertFalse(StringUtils.isNotBlank(null));
    assertFalse(StringUtils.isNotBlank(""));
    assertTrue(StringUtils.isNotBlank("text"));
  }

  @Test
  void testDefaultIfBlank() {
    assertEquals("default", StringUtils.defaultIfBlank(null, "default"));
    assertEquals("default", StringUtils.defaultIfBlank("", "default"));
    assertEquals("value", StringUtils.defaultIfBlank("value", "default"));
  }

  @Test
  void testTruncate() {
    assertNull(StringUtils.truncate(null, 5));
    assertEquals("hello", StringUtils.truncate("hello", 10));
    assertEquals("hello", StringUtils.truncate("hello world", 5));
  }

  @Test
  void testGenerateUuid() {
    String uuid = StringUtils.generateUuid();
    assertNotNull(uuid);
    assertEquals(36, uuid.length());
  }

  @Test
  void testToUpperCase() {
    assertNull(StringUtils.toUpperCase(null));
    assertEquals("HELLO", StringUtils.toUpperCase("hello"));
  }

  @Test
  void testToLowerCase() {
    assertNull(StringUtils.toLowerCase(null));
    assertEquals("hello", StringUtils.toLowerCase("HELLO"));
  }

  @Test
  void testTrim() {
    assertNull(StringUtils.trim(null));
    assertEquals("hello", StringUtils.trim("  hello  "));
  }
}
