package com.example.service.product.controller;

import com.example.service.product.dto.CreateProductRequest;
import com.example.service.product.dto.UpdateProductRequest;
import com.example.service.product.model.Product;
import com.example.service.product.model.ProductType;
import com.example.service.product.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for product operations. */
@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable String id) {
    return productService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/code/{code}")
  public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
    return productService
        .findByCode(code)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/type/{type}")
  public ResponseEntity<List<Product>> getProductsByType(@PathVariable ProductType type) {
    return ResponseEntity.ok(productService.findByType(type));
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
    if (productService.codeExists(request.getCode())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    Product created = productService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(
      @PathVariable String id, @RequestBody UpdateProductRequest request) {
    return productService
        .update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
    if (productService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
