package com.example.service.userprofile.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.userprofile.dto.CreateUserRequest;
import com.example.service.userprofile.dto.UpdateUserRequest;
import com.example.service.userprofile.model.UserProfile;
import com.example.service.userprofile.model.UserStatus;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserProfileServiceImplTest {

  private UserProfileServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new UserProfileServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<UserProfile> users = service.findAll();
    assertTrue(users.isEmpty());
  }

  @Test
  void testCreateAndFindById() {
    CreateUserRequest request =
        new CreateUserRequest("test@example.com", "John", "Doe", "+1234567890");

    UserProfile created = service.create(request);

    assertNotNull(created.getId());
    assertEquals("test@example.com", created.getEmail());
    assertEquals("John", created.getFirstName());
    assertEquals("Doe", created.getLastName());
    assertEquals("+1234567890", created.getPhoneNumber());
    assertEquals(UserStatus.PENDING_VERIFICATION, created.getStatus());
    assertNotNull(created.getCreatedAt());
    assertNotNull(created.getUpdatedAt());

    Optional<UserProfile> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<UserProfile> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByEmail() {
    CreateUserRequest request = new CreateUserRequest("findme@example.com", "Jane", "Smith", null);
    service.create(request);

    Optional<UserProfile> found = service.findByEmail("findme@example.com");
    assertTrue(found.isPresent());
    assertEquals("Jane", found.get().getFirstName());
  }

  @Test
  void testFindByEmailCaseInsensitive() {
    CreateUserRequest request = new CreateUserRequest("CaseMix@Example.Com", "Test", "User", null);
    service.create(request);

    Optional<UserProfile> found = service.findByEmail("casemix@example.com");
    assertTrue(found.isPresent());
  }

  @Test
  void testFindByEmailNotFound() {
    Optional<UserProfile> found = service.findByEmail("nonexistent@example.com");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindAll() {
    service.create(new CreateUserRequest("user1@example.com", "User", "One", null));
    service.create(new CreateUserRequest("user2@example.com", "User", "Two", null));

    List<UserProfile> users = service.findAll();
    assertEquals(2, users.size());
  }

  @Test
  void testUpdate() {
    CreateUserRequest createRequest =
        new CreateUserRequest("update@example.com", "Original", "Name", "+1111111111");
    UserProfile created = service.create(createRequest);

    UpdateUserRequest updateRequest =
        new UpdateUserRequest("Updated", "NewName", "+2222222222", UserStatus.ACTIVE);

    Optional<UserProfile> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals("Updated", updated.get().getFirstName());
    assertEquals("NewName", updated.get().getLastName());
    assertEquals("+2222222222", updated.get().getPhoneNumber());
    assertEquals(UserStatus.ACTIVE, updated.get().getStatus());
  }

  @Test
  void testUpdatePartial() {
    CreateUserRequest createRequest =
        new CreateUserRequest("partial@example.com", "Original", "Name", "+1111111111");
    UserProfile created = service.create(createRequest);

    UpdateUserRequest updateRequest = new UpdateUserRequest();
    updateRequest.setFirstName("OnlyFirst");

    Optional<UserProfile> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals("OnlyFirst", updated.get().getFirstName());
    assertEquals("Name", updated.get().getLastName());
    assertEquals("+1111111111", updated.get().getPhoneNumber());
  }

  @Test
  void testUpdateNotFound() {
    UpdateUserRequest updateRequest = new UpdateUserRequest("New", "Name", null, null);
    Optional<UserProfile> updated = service.update("non-existent-id", updateRequest);
    assertTrue(updated.isEmpty());
  }

  @Test
  void testDelete() {
    CreateUserRequest request = new CreateUserRequest("delete@example.com", "Delete", "Me", null);
    UserProfile created = service.create(request);

    assertTrue(service.delete(created.getId()));
    assertTrue(service.findById(created.getId()).isEmpty());
    assertTrue(service.findByEmail("delete@example.com").isEmpty());
  }

  @Test
  void testDeleteNotFound() {
    assertFalse(service.delete("non-existent-id"));
  }

  @Test
  void testEmailExists() {
    service.create(new CreateUserRequest("exists@example.com", "Test", "User", null));

    assertTrue(service.emailExists("exists@example.com"));
    assertTrue(service.emailExists("EXISTS@EXAMPLE.COM"));
    assertFalse(service.emailExists("notexists@example.com"));
  }
}
