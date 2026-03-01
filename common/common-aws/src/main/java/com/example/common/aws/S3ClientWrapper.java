package com.example.common.aws;

import com.example.common.exception.TechnicalException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/** Wrapper for AWS S3 operations. */
public class S3ClientWrapper {

  private final S3Client s3Client;

  public S3ClientWrapper(S3Client s3Client) {
    this.s3Client = s3Client;
  }

  public void putObject(String bucket, String key, String content) {
    try {
      PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(key).build();
      s3Client.putObject(request, RequestBody.fromString(content, StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new TechnicalException("S3_PUT_ERROR", "Failed to put object to S3", e);
    }
  }

  public void putObject(String bucket, String key, byte[] content) {
    try {
      PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(key).build();
      s3Client.putObject(request, RequestBody.fromBytes(content));
    } catch (Exception e) {
      throw new TechnicalException("S3_PUT_ERROR", "Failed to put object to S3", e);
    }
  }

  public InputStream getObject(String bucket, String key) {
    try {
      GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(key).build();
      ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
      return response;
    } catch (Exception e) {
      throw new TechnicalException("S3_GET_ERROR", "Failed to get object from S3", e);
    }
  }

  public void deleteObject(String bucket, String key) {
    try {
      DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucket).key(key).build();
      s3Client.deleteObject(request);
    } catch (Exception e) {
      throw new TechnicalException("S3_DELETE_ERROR", "Failed to delete object from S3", e);
    }
  }

  public boolean objectExists(String bucket, String key) {
    try {
      HeadObjectRequest request = HeadObjectRequest.builder().bucket(bucket).key(key).build();
      s3Client.headObject(request);
      return true;
    } catch (NoSuchKeyException e) {
      return false;
    } catch (Exception e) {
      throw new TechnicalException("S3_HEAD_ERROR", "Failed to check object existence in S3", e);
    }
  }
}
