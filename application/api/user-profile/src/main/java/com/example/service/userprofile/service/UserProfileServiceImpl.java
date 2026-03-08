package com.example.service.userprofile.service;

import com.example.service.userprofile.dto.CreateUserRequest;
import com.example.service.userprofile.dto.UpdateUserRequest;
import com.example.service.userprofile.model.UserProfile;
import com.example.service.userprofile.model.UserStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/** Implementation of UserProfileService using in-memory storage. */
@Service
public class UserProfileServiceImpl implements UserProfileService {

  private final Map<String, UserProfile> users = new ConcurrentHashMap<>();
  private final Map<String, String> emailIndex = new ConcurrentHashMap<>();

  @Override
  public List<UserProfile> findAll() {
    return new ArrayList<>(users.values());
  }

  @Override
  public Optional<UserProfile> findById(String id) {
    return Optional.ofNullable(users.get(id));
  }

  @Override
  public Optional<UserProfile> findByEmail(String email) {
    String userId = emailIndex.get(email.toLowerCase());
    if (userId == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(users.get(userId));
  }

  @Override
  public UserProfile create(CreateUserRequest request) {
    String id = UUID.randomUUID().toString();
    Instant now = Instant.now();

    UserProfile user = new UserProfile();
    user.setId(id);
    user.setEmail(request.getEmail());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setStatus(UserStatus.PENDING_VERIFICATION);
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    users.put(id, user);
    emailIndex.put(request.getEmail().toLowerCase(), id);

    return user;
  }

  @Override
  public Optional<UserProfile> update(String id, UpdateUserRequest request) {
    UserProfile existing = users.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    if (request.getFirstName() != null) {
      existing.setFirstName(request.getFirstName());
    }
    if (request.getLastName() != null) {
      existing.setLastName(request.getLastName());
    }
    if (request.getPhoneNumber() != null) {
      existing.setPhoneNumber(request.getPhoneNumber());
    }
    if (request.getStatus() != null) {
      existing.setStatus(request.getStatus());
    }
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public boolean delete(String id) {
    UserProfile removed = users.remove(id);
    if (removed != null) {
      emailIndex.remove(removed.getEmail().toLowerCase());
      return true;
    }
    return false;
  }

  @Override
  public boolean emailExists(String email) {
    return emailIndex.containsKey(email.toLowerCase());
  }
}
