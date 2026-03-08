package com.example.service.userprofile.model;

import com.example.model.base.AuditableEntity;
import java.time.Instant;

/** Domain model representing a user profile. */
public class UserProfile extends AuditableEntity {

  private String email;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private UserStatus status;

  /** Default constructor. */
  public UserProfile() {}

  /** Full constructor. */
  public UserProfile(
      String id,
      String email,
      String firstName,
      String lastName,
      String phoneNumber,
      UserStatus status,
      Instant createdAt,
      Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.status = status;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public UserStatus getStatus() {
    return status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }
}
