# REST Sample Service

Sample REST API service demonstrating Spring Boot Web.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/sample

# Or build and run JAR
mvn clean package -pl service/rest/sample -am
java -jar service/rest/sample/target/rest-sample.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/health` | Health check |
| GET | `/api/samples/{id}` | Get sample by ID |

## Port

- Default: `8080`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-sample</artifactId>
</dependency>
```
