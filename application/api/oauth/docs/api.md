# OAuth Server - API

## Token Endpoint

```
POST /oauth2/token
Authorization: Basic {base64(client_id:client_secret)}
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials&scope=api.read api.write
```

Response:
```json
{
  "access_token": "eyJhbGci...",
  "token_type": "Bearer",
  "expires_in": 300,
  "scope": "api.read api.write"
}
```

## JWKS Endpoint

```
GET /oauth2/jwks -> 200 JWK Set
```

## OIDC Discovery

```
GET /.well-known/openid-configuration -> 200 OpenID Configuration
```

## Authorization Endpoint

```
GET /oauth2/authorize?response_type=code&client_id=api-client&scope=openid&redirect_uri={uri}
```
