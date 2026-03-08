package com.example.service.product.model;

import com.example.model.base.AuditableEntity;
import java.math.BigDecimal;
import java.time.Instant;

/** Domain model representing an insurance product. */
public class Product extends AuditableEntity {

  private String code;
  private String name;
  private String description;
  private ProductType productType;
  private ProductStatus status;
  private BigDecimal basePrice;
  private String currency;
  private Integer termMonths;

  /** Default constructor. */
  public Product() {}

  /** Full constructor. */
  public Product(
      String id,
      String code,
      String name,
      String description,
      ProductType productType,
      ProductStatus status,
      BigDecimal basePrice,
      String currency,
      Integer termMonths,
      Instant createdAt,
      Instant updatedAt) {
    super(id, createdAt, updatedAt);
    this.code = code;
    this.name = name;
    this.description = description;
    this.productType = productType;
    this.status = status;
    this.basePrice = basePrice;
    this.currency = currency;
    this.termMonths = termMonths;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductType getProductType() {
    return productType;
  }

  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  public ProductStatus getStatus() {
    return status;
  }

  public void setStatus(ProductStatus status) {
    this.status = status;
  }

  public BigDecimal getBasePrice() {
    return basePrice;
  }

  public void setBasePrice(BigDecimal basePrice) {
    this.basePrice = basePrice;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Integer getTermMonths() {
    return termMonths;
  }

  public void setTermMonths(Integer termMonths) {
    this.termMonths = termMonths;
  }
}
