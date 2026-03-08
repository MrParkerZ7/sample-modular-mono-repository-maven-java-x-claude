# OAuth Server - Rules

## Client Authentication

- Clients authenticate using HTTP Basic (client_id:client_secret)
- Client secrets use {noop} prefix (plain text, for development only)

## Token Rules

- Tokens are signed with RSA-2048 keys
- JWK key pair is generated at startup (ephemeral)
- OIDC discovery is enabled at /.well-known/openid-configuration

## Scopes

| Scope | Description |
|-------|-------------|
| openid | OIDC identity |
| api.read | Read access to APIs |
| api.write | Write access to APIs |

## Security

- Form login enabled for authorization_code flow
- Actuator health endpoint is public
- All other endpoints require authentication
