package com.example.infra;

import com.example.infra.construct.DatabaseConstruct;
import com.example.infra.construct.NetworkConstruct;
import com.example.infra.construct.RestApiConstruct;
import com.example.infra.construct.SoapApiConstruct;
import com.example.infra.construct.StorageConstruct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.apigateway.RestApi;
import software.amazon.awscdk.services.dynamodb.Table;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.rds.DatabaseInstance;
import software.amazon.awscdk.services.s3.Bucket;
import software.amazon.awscdk.services.sqs.Queue;
import software.constructs.Construct;

/** Main CDK stack composing all infrastructure constructs. */
public class MainStack extends Stack {

  private final NetworkConstruct network;
  private final StorageConstruct storage;
  private final DatabaseConstruct databaseConstruct;
  private final RestApiConstruct restApiConstruct;
  private final SoapApiConstruct soapApiConstruct;

  public MainStack(final Construct scope, final String id, final StackProps props) {
    super(scope, id, props);

    this.network = new NetworkConstruct(this, "Network");
    this.storage = new StorageConstruct(this, "Storage");
    this.databaseConstruct = new DatabaseConstruct(this, "Database", network.getVpc());
    this.restApiConstruct = new RestApiConstruct(this, "RestApi");
    this.soapApiConstruct = new SoapApiConstruct(this, "SoapApi");
  }

  public Vpc getVpc() {
    return network.getVpc();
  }

  public Bucket getBucket() {
    return storage.getBucket();
  }

  public Queue getQueue() {
    return storage.getQueue();
  }

  public Table getTable() {
    return storage.getTable();
  }

  public DatabaseInstance getDatabase() {
    return databaseConstruct.getDatabase();
  }

  public RestApi getRestApi() {
    return restApiConstruct.getRestApi();
  }

  public RestApi getSoapApi() {
    return soapApiConstruct.getSoapApi();
  }
}
