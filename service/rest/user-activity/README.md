# REST User Activity Service

User activity tracking REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/user-activity

# Or build and run JAR
mvn clean package -pl service/rest/user-activity -am
java -jar service/rest/user-activity/target/rest-user-activity.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/activities` | List all activities |
| GET | `/api/activities/{id}` | Get activity by ID |
| GET | `/api/activities/user/{userId}` | Get activities by user |
| POST | `/api/activities` | Create activity |
| DELETE | `/api/activities/{id}` | Delete activity |

## Port

- Default: `8082`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-user-activity</artifactId>
</dependency>
```
