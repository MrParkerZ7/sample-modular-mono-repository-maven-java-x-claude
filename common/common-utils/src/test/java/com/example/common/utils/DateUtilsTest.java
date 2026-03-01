package com.example.common.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;

class DateUtilsTest {

  @Test
  void testNow() {
    Instant now = DateUtils.now();
    assertNotNull(now);
  }

  @Test
  void testToday() {
    LocalDate today = DateUtils.today();
    assertNotNull(today);
  }

  @Test
  void testNowLocalDateTime() {
    LocalDateTime now = DateUtils.nowLocalDateTime();
    assertNotNull(now);
  }

  @Test
  void testNowZoned() {
    ZonedDateTime now = DateUtils.nowZoned();
    assertNotNull(now);
    assertEquals(DateUtils.UTC, now.getZone());
  }

  @Test
  void testFormatDateTime() {
    LocalDateTime dateTime = LocalDateTime.of(2024, 3, 15, 10, 30, 0);
    String formatted = DateUtils.formatDateTime(dateTime);

    assertEquals("2024-03-15T10:30:00", formatted);
  }

  @Test
  void testFormatDate() {
    LocalDate date = LocalDate.of(2024, 3, 15);
    String formatted = DateUtils.formatDate(date);

    assertEquals("2024-03-15", formatted);
  }

  @Test
  void testParseDateTime() {
    String dateTimeString = "2024-03-15T10:30:00";
    LocalDateTime parsed = DateUtils.parseDateTime(dateTimeString);

    assertEquals(LocalDateTime.of(2024, 3, 15, 10, 30, 0), parsed);
  }

  @Test
  void testParseDate() {
    String dateString = "2024-03-15";
    LocalDate parsed = DateUtils.parseDate(dateString);

    assertEquals(LocalDate.of(2024, 3, 15), parsed);
  }

  @Test
  void testToInstant() {
    LocalDateTime localDateTime = LocalDateTime.of(2024, 3, 15, 10, 30, 0);
    Instant instant = DateUtils.toInstant(localDateTime);

    assertNotNull(instant);
  }

  @Test
  void testToLocalDateTime() {
    Instant instant = Instant.parse("2024-03-15T10:30:00Z");
    LocalDateTime localDateTime = DateUtils.toLocalDateTime(instant);

    assertEquals(LocalDateTime.of(2024, 3, 15, 10, 30, 0), localDateTime);
  }

  @Test
  void testUtcConstant() {
    assertNotNull(DateUtils.UTC);
    assertEquals("UTC", DateUtils.UTC.getId());
  }
}
