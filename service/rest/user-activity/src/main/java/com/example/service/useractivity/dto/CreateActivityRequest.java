package com.example.service.useractivity.dto;

import com.example.service.useractivity.model.ActivityType;
import java.util.Map;

/** Request DTO for creating a new user activity. */
public class CreateActivityRequest {

  private String userId;
  private ActivityType activityType;
  private String resourcePath;
  private Map<String, String> metadata;
  private String ipAddress;
  private String userAgent;

  /** Default constructor. */
  public CreateActivityRequest() {}

  /** Full constructor. */
  public CreateActivityRequest(
      String userId,
      ActivityType activityType,
      String resourcePath,
      Map<String, String> metadata,
      String ipAddress,
      String userAgent) {
    this.userId = userId;
    this.activityType = activityType;
    this.resourcePath = resourcePath;
    this.metadata = metadata;
    this.ipAddress = ipAddress;
    this.userAgent = userAgent;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public ActivityType getActivityType() {
    return activityType;
  }

  public void setActivityType(ActivityType activityType) {
    this.activityType = activityType;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public void setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
}
