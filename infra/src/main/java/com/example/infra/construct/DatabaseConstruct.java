package com.example.infra.construct;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.services.ec2.InstanceClass;
import software.amazon.awscdk.services.ec2.InstanceSize;
import software.amazon.awscdk.services.ec2.InstanceType;
import software.amazon.awscdk.services.ec2.SubnetSelection;
import software.amazon.awscdk.services.ec2.SubnetType;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.Credentials;
import software.amazon.awscdk.services.rds.DatabaseInstance;
import software.amazon.awscdk.services.rds.DatabaseInstanceEngine;
import software.amazon.awscdk.services.rds.PostgresEngineVersion;
import software.amazon.awscdk.services.rds.PostgresInstanceEngineProps;
import software.constructs.Construct;

/** Construct for RDS PostgreSQL database resources. */
public class DatabaseConstruct extends Construct {

  private final DatabaseInstance database;

  public DatabaseConstruct(final Construct scope, final String id, final Vpc vpc) {
    super(scope, id);

    this.database =
        DatabaseInstance.Builder.create(this, "OAuthDatabase")
            .engine(
                DatabaseInstanceEngine.postgres(
                    PostgresInstanceEngineProps.builder()
                        .version(PostgresEngineVersion.VER_16_1)
                        .build()))
            .instanceType(InstanceType.of(InstanceClass.BURSTABLE3, InstanceSize.MICRO))
            .vpc(vpc)
            .vpcSubnets(SubnetSelection.builder().subnetType(SubnetType.PRIVATE_ISOLATED).build())
            .credentials(Credentials.fromGeneratedSecret("oauthadmin"))
            .databaseName("oauth")
            .multiAz(false)
            .allocatedStorage(20)
            .removalPolicy(RemovalPolicy.DESTROY)
            .deletionProtection(false)
            .backupRetention(software.amazon.awscdk.Duration.days(1))
            .build();
  }

  public DatabaseInstance getDatabase() {
    return database;
  }
}
