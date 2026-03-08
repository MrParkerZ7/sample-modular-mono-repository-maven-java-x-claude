# Batch Sample Service

Sample batch job demonstrating Spring Batch.

## Quick Start

```bash
# Run the batch job
mvn spring-boot:run -pl service/batch/sample

# Or build and run JAR
mvn clean package -pl service/batch/sample -am
java -jar service/batch/sample/target/batch-sample.jar
```

## Jobs

| Job | Description |
|-----|-------------|
| `sampleJob` | Demonstrates batch processing with tasklet |

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>batch-sample</artifactId>
</dependency>
```
