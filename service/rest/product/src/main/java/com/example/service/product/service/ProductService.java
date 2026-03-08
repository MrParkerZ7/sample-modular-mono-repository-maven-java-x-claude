package com.example.service.product.service;

import com.example.service.product.dto.CreateProductRequest;
import com.example.service.product.dto.UpdateProductRequest;
import com.example.service.product.model.Product;
import com.example.service.product.model.ProductType;
import java.util.List;
import java.util.Optional;

/** Service interface for product operations. */
public interface ProductService {

  /**
   * Retrieves all products.
   *
   * @return list of all products
   */
  List<Product> findAll();

  /**
   * Finds a product by ID.
   *
   * @param id the product ID
   * @return optional containing the product if found
   */
  Optional<Product> findById(String id);

  /**
   * Finds a product by code.
   *
   * @param code the product code
   * @return optional containing the product if found
   */
  Optional<Product> findByCode(String code);

  /**
   * Finds products by type.
   *
   * @param type the product type
   * @return list of products matching the type
   */
  List<Product> findByType(ProductType type);

  /**
   * Creates a new product.
   *
   * @param request the create product request
   * @return the created product
   */
  Product create(CreateProductRequest request);

  /**
   * Updates an existing product.
   *
   * @param id the product ID
   * @param request the update product request
   * @return optional containing the updated product if found
   */
  Optional<Product> update(String id, UpdateProductRequest request);

  /**
   * Deletes a product by ID.
   *
   * @param id the product ID
   * @return true if deleted, false if not found
   */
  boolean delete(String id);

  /**
   * Checks if a product code already exists.
   *
   * @param code the code to check
   * @return true if code exists
   */
  boolean codeExists(String code);
}
