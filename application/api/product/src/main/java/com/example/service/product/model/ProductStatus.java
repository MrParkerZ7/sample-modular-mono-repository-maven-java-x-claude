package com.example.service.product.model;

/** Represents the availability status of a product. */
public enum ProductStatus {
  /** Product is available for purchase. */
  ACTIVE,

  /** Product is temporarily unavailable. */
  INACTIVE,

  /** Product is no longer offered. */
  DISCONTINUED,

  /** Product will be available in the future. */
  COMING_SOON
}
