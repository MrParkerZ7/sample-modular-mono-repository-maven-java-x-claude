package com.example.service.product.dto;

import com.example.service.product.model.ProductType;
import java.math.BigDecimal;

/** Request DTO for creating a new product. */
public class CreateProductRequest {

  private String code;
  private String name;
  private String description;
  private ProductType productType;
  private BigDecimal basePrice;
  private String currency;
  private Integer termMonths;

  /** Default constructor. */
  public CreateProductRequest() {}

  /** Full constructor. */
  public CreateProductRequest(
      String code,
      String name,
      String description,
      ProductType productType,
      BigDecimal basePrice,
      String currency,
      Integer termMonths) {
    this.code = code;
    this.name = name;
    this.description = description;
    this.productType = productType;
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
