package com.example.service.userprofile.model;

/** Represents the status of a user account. */
public enum UserStatus {
  /** User account is active and fully functional. */
  ACTIVE,

  /** User account is inactive but can be reactivated. */
  INACTIVE,

  /** User account is suspended due to policy violations. */
  SUSPENDED,

  /** User account is pending email verification. */
  PENDING_VERIFICATION
}
