package com.example.service.userprofile.service;

import com.example.service.userprofile.dto.CreateUserRequest;
import com.example.service.userprofile.dto.UpdateUserRequest;
import com.example.service.userprofile.model.UserProfile;
import java.util.List;
import java.util.Optional;

/** Service interface for user profile operations. */
public interface UserProfileService {

  /**
   * Retrieves all user profiles.
   *
   * @return list of all user profiles
   */
  List<UserProfile> findAll();

  /**
   * Finds a user profile by ID.
   *
   * @param id the user ID
   * @return optional containing the user profile if found
   */
  Optional<UserProfile> findById(String id);

  /**
   * Finds a user profile by email.
   *
   * @param email the user email
   * @return optional containing the user profile if found
   */
  Optional<UserProfile> findByEmail(String email);

  /**
   * Creates a new user profile.
   *
   * @param request the create user request
   * @return the created user profile
   */
  UserProfile create(CreateUserRequest request);

  /**
   * Updates an existing user profile.
   *
   * @param id the user ID
   * @param request the update user request
   * @return optional containing the updated user profile if found
   */
  Optional<UserProfile> update(String id, UpdateUserRequest request);

  /**
   * Deletes a user profile by ID.
   *
   * @param id the user ID
   * @return true if deleted, false if not found
   */
  boolean delete(String id);

  /**
   * Checks if an email already exists.
   *
   * @param email the email to check
   * @return true if email exists
   */
  boolean emailExists(String email);
}
