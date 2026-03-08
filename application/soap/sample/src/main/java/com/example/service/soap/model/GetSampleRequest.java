package com.example.service.soap.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/** Request object for getSample SOAP operation. */
@XmlRootElement(name = "getSampleRequest", namespace = "http://example.com/sample")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSampleRequest {

  @XmlElement(required = true)
  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
