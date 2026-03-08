package com.example.service.userprofile.dto;

import com.example.service.userprofile.model.UserStatus;

/** Request DTO for updating an existing user profile. */
public class UpdateUserRequest {

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private UserStatus status;

  /** Default constructor. */
  public UpdateUserRequest() {}

  /** Full constructor. */
  public UpdateUserRequest(
      String firstName, String lastName, String phoneNumber, UserStatus status) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.status = status;
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
