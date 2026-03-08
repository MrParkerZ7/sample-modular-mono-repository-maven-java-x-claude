package com.example.service.useractivity.model;

import com.example.model.base.BaseEntity;
import java.time.Instant;
import java.util.Map;

/** Domain model representing a user activity event. */
public class UserActivity extends BaseEntity {

  private String userId;
  private ActivityType activityType;
  private String resourcePath;
  private Map<String, String> metadata;
  private Instant timestamp;
  private String ipAddress;
  private String userAgent;

  /** Default constructor. */
  public UserActivity() {}

  /** Full constructor. */
  public UserActivity(
      String id,
      String userId,
      ActivityType activityType,
      String resourcePath,
      Map<String, String> metadata,
      Instant timestamp,
      String ipAddress,
      String userAgent) {
    super(id);
    this.userId = userId;
    this.activityType = activityType;
    this.resourcePath = resourcePath;
    this.metadata = metadata;
    this.timestamp = timestamp;
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

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Instant timestamp) {
    this.timestamp = timestamp;
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
