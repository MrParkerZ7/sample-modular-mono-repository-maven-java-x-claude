package com.example.common.utils;

import java.util.UUID;

/** Utility class for string operations. */
public final class StringUtils {

  private StringUtils() {}

  public static boolean isBlank(String str) {
    return str == null || str.trim().isEmpty();
  }

  public static boolean isNotBlank(String str) {
    return !isBlank(str);
  }

  public static String defaultIfBlank(String str, String defaultValue) {
    return isBlank(str) ? defaultValue : str;
  }

  public static String truncate(String str, int maxLength) {
    if (str == null) {
      return null;
    }
    if (str.length() <= maxLength) {
      return str;
    }
    return str.substring(0, maxLength);
  }

  public static String generateUuid() {
    return UUID.randomUUID().toString();
  }

  public static String toUpperCase(String str) {
    return str == null ? null : str.toUpperCase();
  }

  public static String toLowerCase(String str) {
    return str == null ? null : str.toLowerCase();
  }

  public static String trim(String str) {
    return str == null ? null : str.trim();
  }
}
