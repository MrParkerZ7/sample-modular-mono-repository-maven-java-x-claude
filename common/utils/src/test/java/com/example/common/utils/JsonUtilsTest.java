package com.example.common.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {

  @Test
  void testToJson() {
    TestObject obj = new TestObject("test", 42);
    String json = JsonUtils.toJson(obj);

    assertTrue(json.contains("\"name\":\"test\""));
    assertTrue(json.contains("\"value\":42"));
  }

  @Test
  void testFromJson() {
    String json = "{\"name\":\"test\",\"value\":42}";
    TestObject obj = JsonUtils.fromJson(json, TestObject.class);

    assertEquals("test", obj.getName());
    assertEquals(42, obj.getValue());
  }

  @Test
  void testFromJsonSafeSuccess() {
    String json = "{\"name\":\"test\",\"value\":42}";
    Optional<TestObject> result = JsonUtils.fromJsonSafe(json, TestObject.class);

    assertTrue(result.isPresent());
    assertEquals("test", result.get().getName());
  }

  @Test
  void testFromJsonSafeFailure() {
    String invalidJson = "not valid json";
    Optional<TestObject> result = JsonUtils.fromJsonSafe(invalidJson, TestObject.class);

    assertFalse(result.isPresent());
  }

  @Test
  void testToJsonThrowsException() {
    assertThrows(
        JsonUtils.JsonSerializationException.class,
        () -> JsonUtils.toJson(new UnserializableObject()));
  }

  @Test
  void testFromJsonThrowsException() {
    assertThrows(
        JsonUtils.JsonSerializationException.class,
        () -> JsonUtils.fromJson("invalid", TestObject.class));
  }

  static class TestObject {
    private String name;
    private int value;

    public TestObject() {}

    public TestObject(String name, int value) {
      this.name = name;
      this.value = value;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }
  }

  static class UnserializableObject {
    public Object getSelf() {
      return this;
    }
  }
}
