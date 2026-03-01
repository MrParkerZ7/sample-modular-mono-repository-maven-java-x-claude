package com.example.service.soap.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/** Response object for getSample SOAP operation. */
@XmlRootElement(name = "getSampleResponse", namespace = "http://example.com/sample")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetSampleResponse {

  @XmlElement(required = true)
  private String id;

  @XmlElement(required = true)
  private String name;

  @XmlElement(required = true)
  private boolean active;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
