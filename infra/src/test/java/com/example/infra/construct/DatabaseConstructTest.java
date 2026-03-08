package com.example.infra.construct;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.services.ec2.SubnetConfiguration;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;

class DatabaseConstructTest {

  @Test
  void testDatabaseConstruct() {
    App app = new App();
    Stack stack = new Stack(app, "TestStack");
    Vpc vpc =
        Vpc.Builder.create(stack, "TestVpc")
            .maxAzs(2)
            .subnetConfiguration(
                List.of(
                    SubnetConfiguration.builder()
                        .name("Public")
                        .subnetType(SubnetType.PUBLIC)
                        .cidrMask(24)
                        .build(),
                    SubnetConfiguration.builder()
                        .name("Isolated")
                        .subnetType(SubnetType.PRIVATE_ISOLATED)
                        .cidrMask(24)
                        .build()))
            .build();

    DatabaseConstruct database = new DatabaseConstruct(stack, "TestDatabase", vpc);

    assertNotNull(database);
    assertNotNull(database.getDatabase());
  }
}
