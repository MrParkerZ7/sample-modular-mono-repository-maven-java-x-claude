package com.example.service.useractivity.service;

import com.example.service.useractivity.dto.CreateActivityRequest;
import com.example.service.useractivity.model.UserActivity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** Implementation of UserActivityService using in-memory storage. */
@Service
public class UserActivityServiceImpl implements UserActivityService {

  private final Map<String, UserActivity> activities = new ConcurrentHashMap<>();

  @Override
  public List<UserActivity> findAll() {
    return new ArrayList<>(activities.values());
  }

  @Override
  public Optional<UserActivity> findById(String id) {
    return Optional.ofNullable(activities.get(id));
  }

  @Override
  public List<UserActivity> findByUserId(String userId) {
    return activities.values().stream()
        .filter(a -> userId.equals(a.getUserId()))
        .collect(Collectors.toList());
  }

  @Override
  public UserActivity create(CreateActivityRequest request) {
    String id = UUID.randomUUID().toString();

    UserActivity activity = new UserActivity();
    activity.setId(id);
    activity.setUserId(request.getUserId());
    activity.setActivityType(request.getActivityType());
    activity.setResourcePath(request.getResourcePath());
    activity.setMetadata(request.getMetadata());
    activity.setTimestamp(Instant.now());
    activity.setIpAddress(request.getIpAddress());
    activity.setUserAgent(request.getUserAgent());

    activities.put(id, activity);

    return activity;
  }

  @Override
  public boolean delete(String id) {
    return activities.remove(id) != null;
  }
}
