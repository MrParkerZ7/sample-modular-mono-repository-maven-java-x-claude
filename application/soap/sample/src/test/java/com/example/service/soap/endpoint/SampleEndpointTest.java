package com.example.service.soap.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import com.example.service.soap.model.GetSampleRequest;
import com.example.service.soap.model.GetSampleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleEndpointTest {

  private SampleEndpoint endpoint;

  @BeforeEach
  void setUp() {
    endpoint = new SampleEndpoint();
  }

  @Test
  void testGetSample() {
    GetSampleRequest request = new GetSampleRequest();
    request.setId("123");

    GetSampleResponse response = endpoint.getSample(request);

    assertEquals("123", response.getId());
    assertEquals("Sample 123", response.getName());
    assertTrue(response.isActive());
  }
}
