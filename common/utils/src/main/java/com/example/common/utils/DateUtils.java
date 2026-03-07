package com.example.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/** Utility class for date and time operations. */
public final class DateUtils {

  public static final ZoneId UTC = ZoneId.of("UTC");
  public static final DateTimeFormatter ISO_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;
  public static final DateTimeFormatter ISO_DATE = DateTimeFormatter.ISO_DATE;

  private DateUtils() {}

  public static Instant now() {
    return Instant.now();
  }

  public static LocalDate today() {
    return LocalDate.now(UTC);
  }

  public static LocalDateTime nowLocalDateTime() {
    return LocalDateTime.now(UTC);
  }

  public static ZonedDateTime nowZoned() {
    return ZonedDateTime.now(UTC);
  }

  public static String formatDateTime(LocalDateTime dateTime) {
    return dateTime.format(ISO_DATE_TIME);
  }

  public static String formatDate(LocalDate date) {
    return date.format(ISO_DATE);
  }

  public static LocalDateTime parseDateTime(String dateTimeString) {
    return LocalDateTime.parse(dateTimeString, ISO_DATE_TIME);
  }

  public static LocalDate parseDate(String dateString) {
    return LocalDate.parse(dateString, ISO_DATE);
  }

  public static Instant toInstant(LocalDateTime localDateTime) {
    return localDateTime.atZone(UTC).toInstant();
  }

  public static LocalDateTime toLocalDateTime(Instant instant) {
    return LocalDateTime.ofInstant(instant, UTC);
  }
}
