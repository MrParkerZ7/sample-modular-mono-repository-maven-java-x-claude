# OAuth Authorization Server

OAuth2 authorization server with JWT token issuance.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/oauth

# Or build and run JAR
mvn clean package -pl service/oauth -am
java -jar service/oauth/target/oauth-server.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/oauth2/authorize` | Authorization endpoint |
| POST | `/oauth2/token` | Token endpoint |
| GET | `/oauth2/jwks` | JWK Set endpoint |
| GET | `/.well-known/openid-configuration` | OIDC discovery |
| GET | `/login` | Form login page |
| GET | `/actuator/health` | Health check |

## Port

- Default: `9000`

## Registered Clients

| Client ID | Grant Types | Scopes |
|-----------|-------------|--------|
| `api-client` | client_credentials, authorization_code, refresh_token | openid, api.read, api.write |

## Token Request Example

```bash
# Client credentials flow
curl -X POST http://localhost:9000/oauth2/token \
  -u api-client:api-secret \
  -d "grant_type=client_credentials&scope=api.read api.write"
```

## Users

| Username | Password | Roles |
|----------|----------|-------|
| `admin` | `admin` | ADMIN |
