package com.example.service.product.dto;

import com.example.service.product.model.ProductStatus;
import java.math.BigDecimal;

/** Request DTO for updating an existing product. */
public class UpdateProductRequest {

  private String name;
  private String description;
  private ProductStatus status;
  private BigDecimal basePrice;
  private Integer termMonths;

  /** Default constructor. */
  public UpdateProductRequest() {}

  /** Full constructor. */
  public UpdateProductRequest(
      String name,
      String description,
      ProductStatus status,
      BigDecimal basePrice,
      Integer termMonths) {
    this.name = name;
    this.description = description;
    this.status = status;
    this.basePrice = basePrice;
    this.termMonths = termMonths;
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

  public Integer getTermMonths() {
    return termMonths;
  }

  public void setTermMonths(Integer termMonths) {
    this.termMonths = termMonths;
  }
}
