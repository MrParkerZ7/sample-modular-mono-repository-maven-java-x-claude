# REST Endorsement Service

Insurance policy endorsement REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/endorsement

# Or build and run JAR
mvn clean package -pl service/rest/endorsement -am
java -jar service/rest/endorsement/target/rest-endorsement.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/endorsements` | List all endorsements |
| GET | `/api/endorsements/{id}` | Get endorsement by ID |
| GET | `/api/endorsements/number/{endorsementNumber}` | Get by endorsement number |
| GET | `/api/endorsements/order/{orderId}` | Get endorsements by order |
| GET | `/api/endorsements/user/{userId}` | Get endorsements by user |
| POST | `/api/endorsements` | Create endorsement |
| POST | `/api/endorsements/{id}/approve` | Approve endorsement |
| POST | `/api/endorsements/{id}/reject?reason=` | Reject endorsement |
| POST | `/api/endorsements/{id}/apply` | Apply endorsement |
| DELETE | `/api/endorsements/{id}` | Delete endorsement |

## Port

- Default: `8087`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-endorsement</artifactId>
</dependency>
```
