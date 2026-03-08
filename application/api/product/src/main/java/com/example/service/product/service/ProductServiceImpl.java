package com.example.service.product.service;

import com.example.service.product.dto.CreateProductRequest;
import com.example.service.product.dto.UpdateProductRequest;
import com.example.service.product.model.Product;
import com.example.service.product.model.ProductStatus;
import com.example.service.product.model.ProductType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** Implementation of ProductService using in-memory storage. */
@Service
public class ProductServiceImpl implements ProductService {

  private final Map<String, Product> products = new ConcurrentHashMap<>();
  private final Map<String, String> codeIndex = new ConcurrentHashMap<>();

  @Override
  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  @Override
  public Optional<Product> findById(String id) {
    return Optional.ofNullable(products.get(id));
  }

  @Override
  public Optional<Product> findByCode(String code) {
    String productId = codeIndex.get(code.toUpperCase());
    if (productId == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(products.get(productId));
  }

  @Override
  public List<Product> findByType(ProductType type) {
    return products.values().stream()
        .filter(p -> p.getProductType() == type)
        .collect(Collectors.toList());
  }

  @Override
  public Product create(CreateProductRequest request) {
    String id = UUID.randomUUID().toString();
    Instant now = Instant.now();

    Product product = new Product();
    product.setId(id);
    product.setCode(request.getCode());
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setProductType(request.getProductType());
    product.setStatus(ProductStatus.COMING_SOON);
    product.setBasePrice(request.getBasePrice());
    product.setCurrency(request.getCurrency());
    product.setTermMonths(request.getTermMonths());
    product.setCreatedAt(now);
    product.setUpdatedAt(now);

    products.put(id, product);
    codeIndex.put(request.getCode().toUpperCase(), id);

    return product;
  }

  @Override
  public Optional<Product> update(String id, UpdateProductRequest request) {
    Product existing = products.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    if (request.getName() != null) {
      existing.setName(request.getName());
    }
    if (request.getDescription() != null) {
      existing.setDescription(request.getDescription());
    }
    if (request.getStatus() != null) {
      existing.setStatus(request.getStatus());
    }
    if (request.getBasePrice() != null) {
      existing.setBasePrice(request.getBasePrice());
    }
    if (request.getTermMonths() != null) {
      existing.setTermMonths(request.getTermMonths());
    }
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public boolean delete(String id) {
    Product removed = products.remove(id);
    if (removed != null) {
      codeIndex.remove(removed.getCode().toUpperCase());
      return true;
    }
    return false;
  }

  @Override
  public boolean codeExists(String code) {
    return codeIndex.containsKey(code.toUpperCase());
  }
}
