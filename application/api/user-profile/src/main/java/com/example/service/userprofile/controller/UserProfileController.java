package com.example.service.userprofile.controller;

import com.example.service.userprofile.dto.CreateUserRequest;
import com.example.service.userprofile.dto.UpdateUserRequest;
import com.example.service.userprofile.model.UserProfile;
import com.example.service.userprofile.service.UserProfileService;
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

/** REST controller for user profile operations. */
@RestController
@RequestMapping("/api/users")
public class UserProfileController {

  private final UserProfileService userProfileService;

  public UserProfileController(UserProfileService userProfileService) {
    this.userProfileService = userProfileService;
  }

  @GetMapping
  public ResponseEntity<List<UserProfile>> getAllUsers() {
    return ResponseEntity.ok(userProfileService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserProfile> getUserById(@PathVariable String id) {
    return userProfileService
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<UserProfile> getUserByEmail(@PathVariable String email) {
    return userProfileService
        .findByEmail(email)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserProfile> createUser(@RequestBody CreateUserRequest request) {
    if (userProfileService.emailExists(request.getEmail())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    UserProfile created = userProfileService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserProfile> updateUser(
      @PathVariable String id, @RequestBody UpdateUserRequest request) {
    return userProfileService
        .update(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    if (userProfileService.delete(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
