package com.example.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Optional;

/** Utility class for JSON serialization and deserialization. */
public final class JsonUtils {

  private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

  private JsonUtils() {}

  private static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

  public static String toJson(Object object) {
    try {
      return OBJECT_MAPPER.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new JsonSerializationException("Failed to serialize object to JSON", e);
    }
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return OBJECT_MAPPER.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new JsonSerializationException("Failed to deserialize JSON to object", e);
    }
  }

  public static <T> Optional<T> fromJsonSafe(String json, Class<T> clazz) {
    try {
      return Optional.of(OBJECT_MAPPER.readValue(json, clazz));
    } catch (JsonProcessingException e) {
      return Optional.empty();
    }
  }

  /** Exception thrown when JSON serialization/deserialization fails. */
  public static class JsonSerializationException extends RuntimeException {
    public JsonSerializationException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
