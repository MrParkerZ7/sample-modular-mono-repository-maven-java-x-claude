package com.example.common.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseExceptionTest {

  @Test
  void testBusinessExceptionWithMessage() {
    BusinessException exception = new BusinessException("Test message");

    assertEquals("Test message", exception.getMessage());
    assertEquals("ERR_BUSINESS", exception.getErrorCode());
  }

  @Test
  void testBusinessExceptionWithCodeAndMessage() {
    BusinessException exception = new BusinessException("CUSTOM_CODE", "Custom message");

    assertEquals("Custom message", exception.getMessage());
    assertEquals("CUSTOM_CODE", exception.getErrorCode());
  }

  @Test
  void testBusinessExceptionWithCause() {
    Throwable cause = new RuntimeException("Root cause");
    BusinessException exception = new BusinessException("ERR_001", "With cause", cause);

    assertEquals("With cause", exception.getMessage());
    assertEquals("ERR_001", exception.getErrorCode());
    assertEquals(cause, exception.getCause());
  }

  @Test
  void testTechnicalExceptionWithMessage() {
    TechnicalException exception = new TechnicalException("Technical error");

    assertEquals("Technical error", exception.getMessage());
    assertEquals("ERR_TECHNICAL", exception.getErrorCode());
  }

  @Test
  void testTechnicalExceptionWithCodeAndMessage() {
    TechnicalException exception = new TechnicalException("TECH_001", "Database error");

    assertEquals("Database error", exception.getMessage());
    assertEquals("TECH_001", exception.getErrorCode());
  }

  @Test
  void testTechnicalExceptionWithCause() {
    Throwable cause = new RuntimeException("Connection failed");
    TechnicalException exception = new TechnicalException("TECH_002", "Connection error", cause);

    assertEquals("Connection error", exception.getMessage());
    assertEquals("TECH_002", exception.getErrorCode());
    assertEquals(cause, exception.getCause());
  }
}
