package com.example.service.product.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.product.dto.CreateProductRequest;
import com.example.service.product.dto.UpdateProductRequest;
import com.example.service.product.model.Product;
import com.example.service.product.model.ProductStatus;
import com.example.service.product.model.ProductType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductServiceImplTest {

  private ProductServiceImpl service;

  @BeforeEach
  void setUp() {
    service = new ProductServiceImpl();
  }

  @Test
  void testFindAllEmpty() {
    List<Product> products = service.findAll();
    assertTrue(products.isEmpty());
  }

  @Test
  void testCreateAndFindById() {
    CreateProductRequest request =
        new CreateProductRequest(
            "LIFE-001",
            "Basic Life Insurance",
            "Entry-level product",
            ProductType.LIFE_INSURANCE,
            new BigDecimal("99.99"),
            "USD",
            12);

    Product created = service.create(request);

    assertNotNull(created.getId());
    assertEquals("LIFE-001", created.getCode());
    assertEquals("Basic Life Insurance", created.getName());
    assertEquals("Entry-level product", created.getDescription());
    assertEquals(ProductType.LIFE_INSURANCE, created.getProductType());
    assertEquals(ProductStatus.COMING_SOON, created.getStatus());
    assertEquals(new BigDecimal("99.99"), created.getBasePrice());
    assertEquals("USD", created.getCurrency());
    assertEquals(12, created.getTermMonths());
    assertNotNull(created.getCreatedAt());
    assertNotNull(created.getUpdatedAt());

    Optional<Product> found = service.findById(created.getId());
    assertTrue(found.isPresent());
    assertEquals(created.getId(), found.get().getId());
  }

  @Test
  void testFindByIdNotFound() {
    Optional<Product> found = service.findById("non-existent-id");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByCode() {
    CreateProductRequest request =
        new CreateProductRequest(
            "HEALTH-001", "Health Plan", null, ProductType.HEALTH_INSURANCE, null, null, null);
    service.create(request);

    Optional<Product> found = service.findByCode("HEALTH-001");
    assertTrue(found.isPresent());
    assertEquals("Health Plan", found.get().getName());
  }

  @Test
  void testFindByCodeCaseInsensitive() {
    CreateProductRequest request =
        new CreateProductRequest(
            "Auto-001", "Auto Plan", null, ProductType.AUTO_INSURANCE, null, null, null);
    service.create(request);

    Optional<Product> found = service.findByCode("auto-001");
    assertTrue(found.isPresent());
  }

  @Test
  void testFindByCodeNotFound() {
    Optional<Product> found = service.findByCode("NONEXISTENT");
    assertTrue(found.isEmpty());
  }

  @Test
  void testFindByType() {
    service.create(
        new CreateProductRequest(
            "LIFE-001", "Life 1", null, ProductType.LIFE_INSURANCE, null, null, null));
    service.create(
        new CreateProductRequest(
            "LIFE-002", "Life 2", null, ProductType.LIFE_INSURANCE, null, null, null));
    service.create(
        new CreateProductRequest(
            "HEALTH-001", "Health 1", null, ProductType.HEALTH_INSURANCE, null, null, null));

    List<Product> lifeProducts = service.findByType(ProductType.LIFE_INSURANCE);
    assertEquals(2, lifeProducts.size());

    List<Product> healthProducts = service.findByType(ProductType.HEALTH_INSURANCE);
    assertEquals(1, healthProducts.size());

    List<Product> autoProducts = service.findByType(ProductType.AUTO_INSURANCE);
    assertTrue(autoProducts.isEmpty());
  }

  @Test
  void testFindAll() {
    service.create(
        new CreateProductRequest(
            "PROD-001", "Product 1", null, ProductType.LIFE_INSURANCE, null, null, null));
    service.create(
        new CreateProductRequest(
            "PROD-002", "Product 2", null, ProductType.HEALTH_INSURANCE, null, null, null));

    List<Product> products = service.findAll();
    assertEquals(2, products.size());
  }

  @Test
  void testUpdate() {
    CreateProductRequest createRequest =
        new CreateProductRequest(
            "UPDATE-001",
            "Original Name",
            "Original description",
            ProductType.LIFE_INSURANCE,
            new BigDecimal("100.00"),
            "USD",
            12);
    Product created = service.create(createRequest);

    UpdateProductRequest updateRequest =
        new UpdateProductRequest(
            "Updated Name",
            "Updated description",
            ProductStatus.ACTIVE,
            new BigDecimal("150.00"),
            24);

    Optional<Product> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals("Updated Name", updated.get().getName());
    assertEquals("Updated description", updated.get().getDescription());
    assertEquals(ProductStatus.ACTIVE, updated.get().getStatus());
    assertEquals(new BigDecimal("150.00"), updated.get().getBasePrice());
    assertEquals(24, updated.get().getTermMonths());
  }

  @Test
  void testUpdatePartial() {
    CreateProductRequest createRequest =
        new CreateProductRequest(
            "PARTIAL-001",
            "Original Name",
            "Original description",
            ProductType.LIFE_INSURANCE,
            new BigDecimal("100.00"),
            "USD",
            12);
    Product created = service.create(createRequest);

    UpdateProductRequest updateRequest = new UpdateProductRequest();
    updateRequest.setName("Only Name Updated");

    Optional<Product> updated = service.update(created.getId(), updateRequest);

    assertTrue(updated.isPresent());
    assertEquals("Only Name Updated", updated.get().getName());
    assertEquals("Original description", updated.get().getDescription());
    assertEquals(new BigDecimal("100.00"), updated.get().getBasePrice());
  }

  @Test
  void testUpdateNotFound() {
    UpdateProductRequest updateRequest =
        new UpdateProductRequest("New Name", null, null, null, null);
    Optional<Product> updated = service.update("non-existent-id", updateRequest);
    assertTrue(updated.isEmpty());
  }

  @Test
  void testDelete() {
    CreateProductRequest request =
        new CreateProductRequest(
            "DELETE-001", "Delete Me", null, ProductType.LIFE_INSURANCE, null, null, null);
    Product created = service.create(request);

    assertTrue(service.delete(created.getId()));
    assertTrue(service.findById(created.getId()).isEmpty());
    assertTrue(service.findByCode("DELETE-001").isEmpty());
  }

  @Test
  void testDeleteNotFound() {
    assertFalse(service.delete("non-existent-id"));
  }

  @Test
  void testCodeExists() {
    service.create(
        new CreateProductRequest(
            "EXISTS-001", "Existing", null, ProductType.LIFE_INSURANCE, null, null, null));

    assertTrue(service.codeExists("EXISTS-001"));
    assertTrue(service.codeExists("exists-001"));
    assertFalse(service.codeExists("NOTEXISTS-001"));
  }
}
