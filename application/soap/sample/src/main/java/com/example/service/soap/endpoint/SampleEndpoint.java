package com.example.service.soap.endpoint;

import com.example.service.soap.model.GetSampleRequest;
import com.example.service.soap.model.GetSampleResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/** Sample SOAP endpoint. */
@Endpoint
public class SampleEndpoint {

  public static final String NAMESPACE_URI = "http://example.com/sample";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSampleRequest")
  @ResponsePayload
  public GetSampleResponse getSample(@RequestPayload GetSampleRequest request) {
    GetSampleResponse response = new GetSampleResponse();
    response.setId(request.getId());
    response.setName("Sample " + request.getId());
    response.setActive(true);
    return response;
  }
}
