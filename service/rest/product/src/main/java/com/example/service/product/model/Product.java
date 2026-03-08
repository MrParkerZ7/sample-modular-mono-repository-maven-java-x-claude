package com.example.service.product.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/** Domain model representing an insurance product. */
public class Product {

  private String id;
  private String code;
  private String name;
  private String description;
  private ProductType productType;
  private ProductStatus status;
  private BigDecimal basePrice;
  private String currency;
  private Integer termMonths;
  private Instant createdAt;
  private Instant updatedAt;

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
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
    this.productType = productType;
    this.status = status;
    this.basePrice = basePrice;
    this.currency = currency;
    this.termMonths = termMonths;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
