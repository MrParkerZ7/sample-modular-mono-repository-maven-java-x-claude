package com.example.service.endorsement.model;

import com.example.model.base.AuditableEntity;
import java.time.Instant;

/** Domain model representing an insurance policy endorsement. */
public class Endorsement extends AuditableEntity {

  private String endorsementNumber;
  private String orderId;
  private String userId;
  private String productId;
  private EndorsementType endorsementType;
  private EndorsementStatus status;
  private String description;
  private String rejectionReason;
  private Instant effectiveDate;

  /** Default constructor. */
  public Endorsement() {}

  /** Full constructor. */
  public Endorsement(
      String id,
      String endorsementNumber,
      String orderId,
      String userId,
      String productId,
      EndorsementType endorsementType,
      EndorsementStatus status,
      String description,
      String rejectionReason,
      Instant effectiveDate,
      Instant createdAt,
      Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.endorsementNumber = endorsementNumber;
    this.orderId = orderId;
    this.userId = userId;
    this.productId = productId;
    this.endorsementType = endorsementType;
    this.status = status;
    this.description = description;
    this.rejectionReason = rejectionReason;
    this.effectiveDate = effectiveDate;
  }

  public String getEndorsementNumber() {
    return endorsementNumber;
  }

  public void setEndorsementNumber(String endorsementNumber) {
    this.endorsementNumber = endorsementNumber;
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

  public EndorsementStatus getStatus() {
    return status;
  }

  public void setStatus(EndorsementStatus status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRejectionReason() {
    return rejectionReason;
  }

  public void setRejectionReason(String rejectionReason) {
    this.rejectionReason = rejectionReason;
  }

  public Instant getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Instant effectiveDate) {
    this.effectiveDate = effectiveDate;
  }
}
