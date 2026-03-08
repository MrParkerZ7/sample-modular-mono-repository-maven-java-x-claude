package com.example.service.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SampleController.class)
class SampleControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void testGetSample() throws Exception {
    mockMvc
        .perform(get("/api/samples/123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("123"))
        .andExpect(jsonPath("$.name").value("Sample 123"))
        .andExpect(jsonPath("$.active").value(true));
  }
}
