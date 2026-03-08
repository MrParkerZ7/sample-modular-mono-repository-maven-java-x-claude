package com.example.service.rest.controller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Sample REST controller demonstrating API endpoints. */
@RestController
@RequestMapping("/api/samples")
public class SampleController {

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getSample(@PathVariable String id) {
    return ResponseEntity.ok(Map.of("id", id, "name", "Sample " + id, "active", true));
  }
}
