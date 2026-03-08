# REST User Profile Service

User profile management REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/user-profile

# Or build and run JAR
mvn clean package -pl service/rest/user-profile -am
java -jar service/rest/user-profile/target/rest-user-profile.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/users` | List all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/email/{email}` | Get user by email |
| POST | `/api/users` | Create user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

## Port

- Default: `8083`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-user-profile</artifactId>
</dependency>
```
