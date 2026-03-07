package com.example.common.aws.s3;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.common.exception.TechnicalException;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@ExtendWith(MockitoExtension.class)
class S3ClientWrapperTest {

  @Mock private S3Client s3Client;

  private S3ClientWrapper wrapper;

  @BeforeEach
  void setUp() {
    wrapper = new S3ClientWrapper(s3Client);
  }

  @Test
  void testPutObjectString() {
    when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
        .thenReturn(PutObjectResponse.builder().build());

    wrapper.putObject("bucket", "key", "content");

    verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
  }

  @Test
  void testPutObjectBytes() {
    when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
        .thenReturn(PutObjectResponse.builder().build());

    wrapper.putObject("bucket", "key", "content".getBytes());

    verify(s3Client).putObject(any(PutObjectRequest.class), any(RequestBody.class));
  }

  @Test
  void testPutObjectStringThrowsException() {
    when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
        .thenThrow(new RuntimeException("S3 error"));

    assertThrows(TechnicalException.class, () -> wrapper.putObject("bucket", "key", "content"));
  }

  @Test
  void testPutObjectBytesThrowsException() {
    when(s3Client.putObject(any(PutObjectRequest.class), any(RequestBody.class)))
        .thenThrow(new RuntimeException("S3 error"));

    assertThrows(
        TechnicalException.class, () -> wrapper.putObject("bucket", "key", "content".getBytes()));
  }

  @Test
  @SuppressWarnings("unchecked")
  void testGetObject() {
    ResponseInputStream<GetObjectResponse> mockResponse = mock(ResponseInputStream.class);
    when(s3Client.getObject(any(GetObjectRequest.class))).thenReturn(mockResponse);

    InputStream result = wrapper.getObject("bucket", "key");

    assertNotNull(result);
    verify(s3Client).getObject(any(GetObjectRequest.class));
  }

  @Test
  void testGetObjectThrowsException() {
    when(s3Client.getObject(any(GetObjectRequest.class)))
        .thenThrow(new RuntimeException("S3 error"));

    assertThrows(TechnicalException.class, () -> wrapper.getObject("bucket", "key"));
  }

  @Test
  void testDeleteObject() {
    wrapper.deleteObject("bucket", "key");

    verify(s3Client).deleteObject(any(DeleteObjectRequest.class));
  }

  @Test
  void testDeleteObjectThrowsException() {
    doThrow(new RuntimeException("S3 error"))
        .when(s3Client)
        .deleteObject(any(DeleteObjectRequest.class));

    assertThrows(TechnicalException.class, () -> wrapper.deleteObject("bucket", "key"));
  }

  @Test
  void testObjectExistsTrue() {
    when(s3Client.headObject(any(HeadObjectRequest.class)))
        .thenReturn(HeadObjectResponse.builder().build());

    assertTrue(wrapper.objectExists("bucket", "key"));
  }

  @Test
  void testObjectExistsFalse() {
    when(s3Client.headObject(any(HeadObjectRequest.class)))
        .thenThrow(NoSuchKeyException.builder().build());

    assertFalse(wrapper.objectExists("bucket", "key"));
  }

  @Test
  void testObjectExistsThrowsException() {
    when(s3Client.headObject(any(HeadObjectRequest.class)))
        .thenThrow(new RuntimeException("S3 error"));

    assertThrows(TechnicalException.class, () -> wrapper.objectExists("bucket", "key"));
  }
}
