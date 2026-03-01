package com.example.infra;

import software.amazon.awscdk.RemovalPolicy;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.dynamodb.Attribute;
import software.amazon.awscdk.services.dynamodb.AttributeType;
import software.amazon.awscdk.services.dynamodb.BillingMode;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.s3.BlockPublicAccess;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.s3.BucketEncryption;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

/** Main CDK stack with sample AWS resources. */
public class MainStack extends Stack {

  private final Bucket bucket;
  private final Queue queue;
  private final Table table;

  public MainStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    this.bucket =
        Bucket.Builder.create(this, "SampleBucket")
            .encryption(BucketEncryption.S3_MANAGED)
            .blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
            .removalPolicy(RemovalPolicy.DESTROY)
            .autoDeleteObjects(true)
            .build();

    this.queue = Queue.Builder.create(this, "SampleQueue").build();

    this.table =
        Table.Builder.create(this, "SampleTable")
            .partitionKey(Attribute.builder().name("id").type(AttributeType.STRING).build())
            .billingMode(BillingMode.PAY_PER_REQUEST)
            .removalPolicy(RemovalPolicy.DESTROY)
            .build();
  }

  public Bucket getBucket() {
    return bucket;
  }

  public Queue getQueue() {
    return queue;
  }

  public Table getTable() {
    return table;
  }
}
