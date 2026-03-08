package com.example.service.useractivity.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class UserActivityTest {

  @Test
  void testDefaultConstructor() {
    UserActivity activity = new UserActivity();
    assertNull(activity.getId());
    assertNull(activity.getUserId());
  }

  @Test
  void testFullConstructor() {
    Instant now = Instant.now();
    Map<String, String> metadata = new HashMap<>();
    metadata.put("key", "value");

    UserActivity activity =
        new UserActivity(
            "id-123",
            "user-456",
            ActivityType.PAGE_VIEW,
            "/dashboard",
            metadata,
            now,
            "192.168.1.1",
            "Mozilla/5.0");

    assertEquals("id-123", activity.getId());
    assertEquals("user-456", activity.getUserId());
    assertEquals(ActivityType.PAGE_VIEW, activity.getActivityType());
    assertEquals("/dashboard", activity.getResourcePath());
    assertEquals(metadata, activity.getMetadata());
    assertEquals(now, activity.getTimestamp());
    assertEquals("192.168.1.1", activity.getIpAddress());
    assertEquals("Mozilla/5.0", activity.getUserAgent());
  }

  @Test
  void testSetters() {
    UserActivity activity = new UserActivity();
    Instant now = Instant.now();
    Map<String, String> metadata = new HashMap<>();
    metadata.put("action", "click");

    activity.setId("id-789");
    activity.setUserId("user-123");
    activity.setActivityType(ActivityType.CLICK);
    activity.setResourcePath("/products");
    activity.setMetadata(metadata);
    activity.setTimestamp(now);
    activity.setIpAddress("10.0.0.1");
    activity.setUserAgent("Chrome/120");

    assertEquals("id-789", activity.getId());
    assertEquals("user-123", activity.getUserId());
    assertEquals(ActivityType.CLICK, activity.getActivityType());
    assertEquals("/products", activity.getResourcePath());
    assertEquals(metadata, activity.getMetadata());
    assertEquals(now, activity.getTimestamp());
    assertEquals("10.0.0.1", activity.getIpAddress());
    assertEquals("Chrome/120", activity.getUserAgent());
  }

  @Test
  void testEqualsAndHashCode() {
    UserActivity activity1 = new UserActivity();
    activity1.setId("same-id");

    UserActivity activity2 = new UserActivity();
    activity2.setId("same-id");

    UserActivity activity3 = new UserActivity();
    activity3.setId("different-id");

    assertEquals(activity1, activity2);
    assertEquals(activity1.hashCode(), activity2.hashCode());
    assertNotEquals(activity1, activity3);
    assertNotEquals(activity1, null);
    assertNotEquals(activity1, new Object());
    assertEquals(activity1, activity1);
  }
}
