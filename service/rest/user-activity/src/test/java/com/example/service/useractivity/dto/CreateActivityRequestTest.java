package com.example.service.useractivity.dto;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.useractivity.model.ActivityType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CreateActivityRequestTest {

  @Test
  void testDefaultConstructor() {
    CreateActivityRequest request = new CreateActivityRequest();
    assertNull(request.getUserId());
    assertNull(request.getActivityType());
    assertNull(request.getResourcePath());
    assertNull(request.getMetadata());
    assertNull(request.getIpAddress());
    assertNull(request.getUserAgent());
  }

  @Test
  void testFullConstructor() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("key", "value");

    CreateActivityRequest request =
        new CreateActivityRequest(
            "user-123", ActivityType.LOGIN, "/login", metadata, "192.168.1.1", "Mozilla/5.0");

    assertEquals("user-123", request.getUserId());
    assertEquals(ActivityType.LOGIN, request.getActivityType());
    assertEquals("/login", request.getResourcePath());
    assertEquals(metadata, request.getMetadata());
    assertEquals("192.168.1.1", request.getIpAddress());
    assertEquals("Mozilla/5.0", request.getUserAgent());
  }

  @Test
  void testSetters() {
    CreateActivityRequest request = new CreateActivityRequest();
    Map<String, String> metadata = new HashMap<>();
    metadata.put("action", "submit");

    request.setUserId("user-456");
    request.setActivityType(ActivityType.FORM_SUBMIT);
    request.setResourcePath("/contact");
    request.setMetadata(metadata);
    request.setIpAddress("10.0.0.1");
    request.setUserAgent("Chrome/120");

    assertEquals("user-456", request.getUserId());
    assertEquals(ActivityType.FORM_SUBMIT, request.getActivityType());
    assertEquals("/contact", request.getResourcePath());
    assertEquals(metadata, request.getMetadata());
    assertEquals("10.0.0.1", request.getIpAddress());
    assertEquals("Chrome/120", request.getUserAgent());
  }
}
