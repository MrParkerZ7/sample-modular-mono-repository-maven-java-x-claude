package com.example.service.endorsement.service;

import com.example.service.endorsement.dto.CreateEndorsementRequest;
import com.example.service.endorsement.model.Endorsement;
import java.util.List;
import java.util.Optional;

/** Service interface for endorsement operations. */
public interface EndorsementService {

  /**
   * Retrieves all endorsements.
   *
   * @return list of all endorsements
   */
  List<Endorsement> findAll();

  /**
   * Finds an endorsement by ID.
   *
   * @param id the endorsement ID
   * @return optional containing the endorsement if found
   */
  Optional<Endorsement> findById(String id);

  /**
   * Finds an endorsement by endorsement number.
   *
   * @param endorsementNumber the endorsement number
   * @return optional containing the endorsement if found
   */
  Optional<Endorsement> findByEndorsementNumber(String endorsementNumber);

  /**
   * Finds all endorsements for a specific order.
   *
   * @param orderId the order ID
   * @return list of endorsements for the order
   */
  List<Endorsement> findByOrderId(String orderId);

  /**
   * Finds all endorsements for a specific user.
   *
   * @param userId the user ID
   * @return list of endorsements for the user
   */
  List<Endorsement> findByUserId(String userId);

  /**
   * Creates a new endorsement.
   *
   * @param request the create endorsement request
   * @return the created endorsement
   */
  Endorsement create(CreateEndorsementRequest request);

  /**
   * Approves an endorsement.
   *
   * @param id the endorsement ID
   * @return optional containing the approved endorsement if found
   */
  Optional<Endorsement> approve(String id);

  /**
   * Rejects an endorsement.
   *
   * @param id the endorsement ID
   * @param reason the rejection reason
   * @return optional containing the rejected endorsement if found
   */
  Optional<Endorsement> reject(String id, String reason);

  /**
   * Applies an approved endorsement.
   *
   * @param id the endorsement ID
   * @return optional containing the applied endorsement if found
   */
  Optional<Endorsement> apply(String id);

  /**
   * Deletes an endorsement.
   *
   * @param id the endorsement ID
   * @return true if deleted, false if not found
   */
  boolean delete(String id);
}
