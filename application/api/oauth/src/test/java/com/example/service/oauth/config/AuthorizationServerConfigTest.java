package com.example.service.oauth.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;

@SpringBootTest(properties = "server.port=0")
class AuthorizationServerConfigTest {

  @Autowired private RegisteredClientRepository registeredClientRepository;

  @Autowired private JwtDecoder jwtDecoder;

  @Autowired private AuthorizationServerSettings authorizationServerSettings;

  @Test
  void testRegisteredClientRepository() {
    assertNotNull(registeredClientRepository);
    assertNotNull(registeredClientRepository.findByClientId("api-client"));
  }

  @Test
  void testJwtDecoder() {
    assertNotNull(jwtDecoder);
  }

  @Test
  void testAuthorizationServerSettings() {
    assertNotNull(authorizationServerSettings);
  }
}
