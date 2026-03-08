package com.example.service.useractivity.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.useractivity.dto.CreateActivityRequest;
import com.example.service.useractivity.model.ActivityType;
import com.example.service.useractivity.model.UserActivity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserActivityServiceImplTest {

  private UserActivityServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new UserActivityServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<UserActivity> activities = service.findAll();
    assertTrue(activities.isEmpty());
  }

  @Test
  void testCreateAndFindById() {
    Map<String, String> metadata = new HashMap<>();
    metadata.put("page", "home");

    CreateActivityRequest request =
        new CreateActivityRequest(
            "user-123", ActivityType.PAGE_VIEW, "/home", metadata, "192.168.1.1", "Mozilla/5.0");

    UserActivity created = service.create(request);

    assertNotNull(created.getId());
    assertEquals("user-123", created.getUserId());
    assertEquals(ActivityType.PAGE_VIEW, created.getActivityType());
    assertEquals("/home", created.getResourcePath());
    assertEquals(metadata, created.getMetadata());
    assertNotNull(created.getTimestamp());
    assertEquals("192.168.1.1", created.getIpAddress());
    assertEquals("Mozilla/5.0", created.getUserAgent());

    Optional<UserActivity> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<UserActivity> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByUserId() {
    service.create(
        new CreateActivityRequest("user-123", ActivityType.LOGIN, "/login", null, null, null));
    service.create(
        new CreateActivityRequest("user-123", ActivityType.PAGE_VIEW, "/home", null, null, null));
    service.create(
        new CreateActivityRequest("user-456", ActivityType.LOGIN, "/login", null, null, null));

    List<UserActivity> user123Activities = service.findByUserId("user-123");
    assertEquals(2, user123Activities.size());

    List<UserActivity> user456Activities = service.findByUserId("user-456");
    assertEquals(1, user456Activities.size());

    List<UserActivity> nonExistentActivities = service.findByUserId("user-999");
    assertTrue(nonExistentActivities.isEmpty());
  }

  @Test
  void testFindAll() {
    service.create(new CreateActivityRequest("user-1", ActivityType.LOGIN, null, null, null, null));
    service.create(
        new CreateActivityRequest("user-2", ActivityType.LOGOUT, null, null, null, null));

    List<UserActivity> activities = service.findAll();
    assertEquals(2, activities.size());
  }

  @Test
  void testDelete() {
    CreateActivityRequest request =
        new CreateActivityRequest("user-123", ActivityType.DOWNLOAD, "/file.pdf", null, null, null);
    UserActivity created = service.create(request);

    assertTrue(service.delete(created.getId()));
    assertTrue(service.findById(created.getId()).isEmpty());
  }

  @Test
  void testDeleteNotFound() {
    assertFalse(service.delete("non-existent-id"));
  }

  @Test
  void testCreateWithNullMetadata() {
    CreateActivityRequest request =
        new CreateActivityRequest("user-123", ActivityType.CLICK, "/button", null, null, null);

    UserActivity created = service.create(request);

    assertNotNull(created.getId());
    assertNull(created.getMetadata());
  }
}
