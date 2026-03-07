package com.example.common.env;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** Provides configuration values from various sources. */
public class ConfigurationProvider {

  private final Map<String, String> configurations;

  public ConfigurationProvider() {
    this.configurations = new HashMap<>();
  }

  public ConfigurationProvider(Map<String, String> initialConfig) {
    this.configurations = new HashMap<>(initialConfig);
  }

  public void set(String key, String value) {
    configurations.put(key, value);
  }

  public String get(String key) {
    String value = configurations.get(key);
    if (value == null) {
      value = EnvironmentManager.getEnv(key);
    }
    if (value == null) {
      value = EnvironmentManager.getProperty(key);
    }
    return value;
  }

  public Optional<String> getOptional(String key) {
    return Optional.ofNullable(get(key));
  }

  public String getOrDefault(String key, String defaultValue) {
    String value = get(key);
    return value != null ? value : defaultValue;
  }

  public int getInt(String key, int defaultValue) {
    String value = get(key);
    if (value == null) {
      return defaultValue;
    }
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    String value = get(key);
    if (value == null) {
      return defaultValue;
    }
    return Boolean.parseBoolean(value);
  }

  public boolean containsKey(String key) {
    return get(key) != null;
  }

  public void clear() {
    configurations.clear();
  }
}
