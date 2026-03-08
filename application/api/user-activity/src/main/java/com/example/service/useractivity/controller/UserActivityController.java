package com.example.service.useractivity.controller;

import com.example.service.useractivity.dto.CreateActivityRequest;
import com.example.service.useractivity.model.UserActivity;
import com.example.service.useractivity.service.UserActivityService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** REST controller for user activity operations. */
@RestController
@RequestMapping("/api/activities")
public class UserActivityController {

  private final UserActivityService userActivityService;

  public UserActivityController(UserActivityService userActivityService) {
    this.userActivityService = userActivityService;
  }

  @GetMapping
  public ResponseEntity<List<UserActivity>> getAllActivities() {
    return ResponseEntity.ok(userActivityService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserActivity> getActivityById(@PathVariable String id) {
    return userActivityService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<UserActivity>> getActivitiesByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(userActivityService.findByUserId(userId));
  }

  @PostMapping
  public ResponseEntity<UserActivity> createActivity(@RequestBody CreateActivityRequest request) {
    UserActivity created = userActivityService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteActivity(@PathVariable String id) {
    if (userActivityService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
