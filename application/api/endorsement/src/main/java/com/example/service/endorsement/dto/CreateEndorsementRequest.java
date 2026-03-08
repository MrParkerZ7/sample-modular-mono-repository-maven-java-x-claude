package com.example.service.endorsement.dto;

import com.example.service.endorsement.model.EndorsementType;
import java.time.Instant;

/** Request DTO for creating an endorsement. */
public class CreateEndorsementRequest {

  private String orderId;
  private String userId;
  private String productId;
  private EndorsementType endorsementType;
  private String description;
  private Instant effectiveDate;

  /** Default constructor. */
  public CreateEndorsementRequest() {}

  /** Full constructor. */
  public CreateEndorsementRequest(
      String orderId,
      String userId,
      String productId,
      EndorsementType endorsementType,
      String description,
      Instant effectiveDate) {
    this.orderId = orderId;
    this.userId = userId;
    this.productId = productId;
    this.endorsementType = endorsementType;
    this.description = description;
    this.effectiveDate = effectiveDate;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public EndorsementType getEndorsementType() {
    return endorsementType;
  }

  public void setEndorsementType(EndorsementType endorsementType) {
    this.endorsementType = endorsementType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Instant effectiveDate) {
    this.effectiveDate = effectiveDate;
  }
}
