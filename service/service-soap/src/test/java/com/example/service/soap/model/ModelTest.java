package com.example.service.soap.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ModelTest {

  @Test
  void testGetSampleRequest() {
    GetSampleRequest request = new GetSampleRequest();
    request.setId("test-id");

    assertEquals("test-id", request.getId());
  }

  @Test
  void testGetSampleResponse() {
    GetSampleResponse response = new GetSampleResponse();
    response.setId("test-id");
    response.setName("Test Name");
    response.setActive(true);

    assertEquals("test-id", response.getId());
    assertEquals("Test Name", response.getName());
    assertTrue(response.isActive());
  }
}
