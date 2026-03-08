package com.example.service.endorsement.controller;

import com.example.service.endorsement.dto.CreateEndorsementRequest;
import com.example.service.endorsement.model.Endorsement;
import com.example.service.endorsement.service.EndorsementService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for endorsement operations. */
@RestController
@RequestMapping("/api/endorsements")
public class EndorsementController {

  private final EndorsementService endorsementService;

  public EndorsementController(EndorsementService endorsementService) {
    this.endorsementService = endorsementService;
  }

  @GetMapping
  public ResponseEntity<List<Endorsement>> getAllEndorsements() {
    return ResponseEntity.ok(endorsementService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Endorsement> getEndorsementById(@PathVariable String id) {
    return endorsementService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/number/{endorsementNumber}")
  public ResponseEntity<Endorsement> getEndorsementByNumber(
      @PathVariable String endorsementNumber) {
    return endorsementService
        .findByEndorsementNumber(endorsementNumber)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/order/{orderId}")
  public ResponseEntity<List<Endorsement>> getEndorsementsByOrderId(@PathVariable String orderId) {
    return ResponseEntity.ok(endorsementService.findByOrderId(orderId));
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Endorsement>> getEndorsementsByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(endorsementService.findByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<Endorsement> createEndorsement(
      @RequestBody CreateEndorsementRequest request) {
    Endorsement created = endorsementService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PostMapping("/{id}/approve")
  public ResponseEntity<Endorsement> approveEndorsement(@PathVariable String id) {
    return endorsementService
        .approve(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/reject")
  public ResponseEntity<Endorsement> rejectEndorsement(
      @PathVariable String id, @RequestParam String reason) {
    return endorsementService
        .reject(id, reason)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/{id}/apply")
  public ResponseEntity<Endorsement> applyEndorsement(@PathVariable String id) {
    return endorsementService
        .apply(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEndorsement(@PathVariable String id) {
    if (endorsementService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
