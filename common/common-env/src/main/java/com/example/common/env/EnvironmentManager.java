package com.example.common.env;

import java.util.Optional;

/** Manages environment variables and system properties. */
public final class EnvironmentManager {

  private EnvironmentManager() {}

  public static String getEnv(String name) {
    return System.getenv(name);
  }

  public static Optional<String> getEnvOptional(String name) {
    return Optional.ofNullable(System.getenv(name));
  }

  public static String getEnvOrDefault(String name, String defaultValue) {
    String value = System.getenv(name);
    return value != null ? value : defaultValue;
  }

  public static String getProperty(String name) {
    return System.getProperty(name);
  }

  public static Optional<String> getPropertyOptional(String name) {
    return Optional.ofNullable(System.getProperty(name));
  }

  public static String getPropertyOrDefault(String name, String defaultValue) {
    return System.getProperty(name, defaultValue);
  }

  public static boolean isDevelopment() {
    return "development".equalsIgnoreCase(getActiveProfile());
  }

  public static boolean isProduction() {
    return "production".equalsIgnoreCase(getActiveProfile());
  }

  public static boolean isTest() {
    return "test".equalsIgnoreCase(getActiveProfile());
  }

  public static String getActiveProfile() {
    String profile = getEnv("SPRING_PROFILES_ACTIVE");
    if (profile == null) {
      profile = getProperty("spring.profiles.active");
    }
    return profile != null ? profile : "default";
  }
}
