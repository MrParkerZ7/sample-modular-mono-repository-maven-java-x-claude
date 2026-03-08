package com.example.service.useractivity.service;

import com.example.service.useractivity.dto.CreateActivityRequest;
import com.example.service.useractivity.model.UserActivity;
import java.util.List;
import java.util.Optional;

/** Service interface for user activity operations. */
public interface UserActivityService {

  /**
   * Retrieves all user activities.
   *
   * @return list of all user activities
   */
  List<UserActivity> findAll();

  /**
   * Finds a user activity by ID.
   *
   * @param id the activity ID
   * @return optional containing the activity if found
   */
  Optional<UserActivity> findById(String id);

  /**
   * Finds all activities for a specific user.
   *
   * @param userId the user ID
   * @return list of activities for the user
   */
  List<UserActivity> findByUserId(String userId);

  /**
   * Creates a new user activity.
   *
   * @param request the create activity request
   * @return the created activity
   */
  UserActivity create(CreateActivityRequest request);

  /**
   * Deletes a user activity by ID.
   *
   * @param id the activity ID
   * @return true if deleted, false if not found
   */
  boolean delete(String id);
}
