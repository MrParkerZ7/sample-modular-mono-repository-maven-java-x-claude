package com.example.service.product.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.service.product.config.SecurityConfig;
import com.example.service.product.dto.CreateProductRequest;
import com.example.service.product.dto.UpdateProductRequest;
import com.example.service.product.model.Product;
import com.example.service.product.model.ProductStatus;
import com.example.service.product.model.ProductType;
import com.example.service.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
class ProductControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private ProductService productService;

  @MockBean private JwtDecoder jwtDecoder;

  private Product createTestProduct() {
    Instant now = Instant.now();
    return new Product(
        "prod-123",
        "LIFE-001",
        "Basic Life Insurance",
        "Entry-level product",
        ProductType.LIFE_INSURANCE,
        ProductStatus.ACTIVE,
        new BigDecimal("99.99"),
        "USD",
        12,
        now,
        now);
  }

  @Test
  void testGetAllProducts() throws Exception {
    Product product = createTestProduct();
    when(productService.findAll()).thenReturn(Arrays.asList(product));

    mockMvc
        .perform(get("/api/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("prod-123"))
        .andExpect(jsonPath("$[0].code").value("LIFE-001"));
  }

  @Test
  void testGetProductById() throws Exception {
    Product product = createTestProduct();
    when(productService.findById("prod-123")).thenReturn(Optional.of(product));

    mockMvc
        .perform(get("/api/products/prod-123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("prod-123"))
        .andExpect(jsonPath("$.name").value("Basic Life Insurance"));
  }

  @Test
  void testGetProductByIdNotFound() throws Exception {
    when(productService.findById("non-existent")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/products/non-existent")).andExpect(status().isNotFound());
  }

  @Test
  void testGetProductByCode() throws Exception {
    Product product = createTestProduct();
    when(productService.findByCode("LIFE-001")).thenReturn(Optional.of(product));

    mockMvc
        .perform(get("/api/products/code/LIFE-001"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.code").value("LIFE-001"));
  }

  @Test
  void testGetProductByCodeNotFound() throws Exception {
    when(productService.findByCode("NOTFOUND")).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/products/code/NOTFOUND")).andExpect(status().isNotFound());
  }

  @Test
  void testGetProductsByType() throws Exception {
    Product product = createTestProduct();
    when(productService.findByType(ProductType.LIFE_INSURANCE)).thenReturn(Arrays.asList(product));

    mockMvc
        .perform(get("/api/products/type/LIFE_INSURANCE"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].productType").value("LIFE_INSURANCE"));
  }

  @Test
  void testGetProductsByTypeEmpty() throws Exception {
    when(productService.findByType(ProductType.AUTO_INSURANCE)).thenReturn(Collections.emptyList());

    mockMvc
        .perform(get("/api/products/type/AUTO_INSURANCE"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testCreateProduct() throws Exception {
    CreateProductRequest request =
        new CreateProductRequest(
            "NEW-001",
            "New Product",
            "Description",
            ProductType.HEALTH_INSURANCE,
            new BigDecimal("149.99"),
            "USD",
            24);
    Product created = createTestProduct();
    created.setCode("NEW-001");

    when(productService.codeExists("NEW-001")).thenReturn(false);
    when(productService.create(any(CreateProductRequest.class))).thenReturn(created);

    mockMvc
        .perform(
            post("/api/products")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.code").value("NEW-001"));
  }

  @Test
  void testCreateProductCodeConflict() throws Exception {
    CreateProductRequest request =
        new CreateProductRequest(
            "EXISTS-001", "Existing", null, ProductType.LIFE_INSURANCE, null, null, null);

    when(productService.codeExists("EXISTS-001")).thenReturn(true);

    mockMvc
        .perform(
            post("/api/products")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isConflict());
  }

  @Test
  void testUpdateProduct() throws Exception {
    UpdateProductRequest request =
        new UpdateProductRequest(
            "Updated Name", "Updated desc", ProductStatus.INACTIVE, new BigDecimal("199.99"), 36);
    Product updated = createTestProduct();
    updated.setName("Updated Name");

    when(productService.update(eq("prod-123"), any(UpdateProductRequest.class)))
        .thenReturn(Optional.of(updated));

    mockMvc
        .perform(
            put("/api/products/prod-123")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Updated Name"));
  }

  @Test
  void testUpdateProductNotFound() throws Exception {
    UpdateProductRequest request = new UpdateProductRequest("Updated", null, null, null, null);

    when(productService.update(eq("non-existent"), any(UpdateProductRequest.class)))
        .thenReturn(Optional.empty());

    mockMvc
        .perform(
            put("/api/products/non-existent")
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }

  @Test
  void testDeleteProduct() throws Exception {
    when(productService.delete("prod-123")).thenReturn(true);

    mockMvc.perform(delete("/api/products/prod-123").with(jwt())).andExpect(status().isNoContent());
  }

  @Test
  void testDeleteProductNotFound() throws Exception {
    when(productService.delete("non-existent")).thenReturn(false);

    mockMvc
        .perform(delete("/api/products/non-existent").with(jwt()))
        .andExpect(status().isNotFound());
  }
}
