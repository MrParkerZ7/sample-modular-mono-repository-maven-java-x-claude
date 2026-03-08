package com.example.service.endorsement.service;

import com.example.service.endorsement.dto.CreateEndorsementRequest;
import com.example.service.endorsement.model.Endorsement;
import com.example.service.endorsement.model.EndorsementStatus;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** Implementation of EndorsementService using in-memory storage. */
@Service
public class EndorsementServiceImpl implements EndorsementService {

  private final Map<String, Endorsement> endorsements = new ConcurrentHashMap<>();
  private final Map<String, String> endorsementNumberIndex = new ConcurrentHashMap<>();
  private final AtomicLong endorsementCounter = new AtomicLong(0);

  @Override
  public List<Endorsement> findAll() {
    return new ArrayList<>(endorsements.values());
  }

  @Override
  public Optional<Endorsement> findById(String id) {
    return Optional.ofNullable(endorsements.get(id));
  }

  @Override
  public Optional<Endorsement> findByEndorsementNumber(String endorsementNumber) {
    String endorsementId = endorsementNumberIndex.get(endorsementNumber);
    if (endorsementId == null) {
      return Optional.empty();
    }
    return Optional.ofNullable(endorsements.get(endorsementId));
  }

  @Override
  public List<Endorsement> findByOrderId(String orderId) {
    return endorsements.values().stream()
        .filter(e -> orderId.equals(e.getOrderId()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Endorsement> findByUserId(String userId) {
    return endorsements.values().stream()
        .filter(e -> userId.equals(e.getUserId()))
        .collect(Collectors.toList());
  }

  @Override
  public Endorsement create(CreateEndorsementRequest request) {
    String id = UUID.randomUUID().toString();
    String endorsementNumber = generateEndorsementNumber();
    Instant now = Instant.now();

    Endorsement endorsement = new Endorsement();
    endorsement.setId(id);
    endorsement.setEndorsementNumber(endorsementNumber);
    endorsement.setOrderId(request.getOrderId());
    endorsement.setUserId(request.getUserId());
    endorsement.setProductId(request.getProductId());
    endorsement.setEndorsementType(request.getEndorsementType());
    endorsement.setStatus(EndorsementStatus.PENDING);
    endorsement.setDescription(request.getDescription());
    endorsement.setEffectiveDate(request.getEffectiveDate());
    endorsement.setCreatedAt(now);
    endorsement.setUpdatedAt(now);

    endorsements.put(id, endorsement);
    endorsementNumberIndex.put(endorsementNumber, id);

    return endorsement;
  }

  @Override
  public Optional<Endorsement> approve(String id) {
    Endorsement existing = endorsements.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    existing.setStatus(EndorsementStatus.APPROVED);
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public Optional<Endorsement> reject(String id, String reason) {
    Endorsement existing = endorsements.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    existing.setStatus(EndorsementStatus.REJECTED);
    existing.setRejectionReason(reason);
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public Optional<Endorsement> apply(String id) {
    Endorsement existing = endorsements.get(id);
    if (existing == null) {
      return Optional.empty();
    }

    existing.setStatus(EndorsementStatus.APPLIED);
    existing.setUpdatedAt(Instant.now());

    return Optional.of(existing);
  }

  @Override
  public boolean delete(String id) {
    Endorsement removed = endorsements.remove(id);
    if (removed == null) {
      return false;
    }
    endorsementNumberIndex.remove(removed.getEndorsementNumber());
    return true;
  }

  private String generateEndorsementNumber() {
    long count = endorsementCounter.incrementAndGet();
    return String.format("END-%d-%05d", Year.now().getValue(), count);
  }
}
