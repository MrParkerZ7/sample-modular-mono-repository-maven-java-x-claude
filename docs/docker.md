# Docker

> Container deployment strategy using multi-stage builds and Spring Boot layered JARs.

---

## Quick Start

```bash
# 1. Build all modules
mvn clean package -DskipTests

# 2. Build shared base image
docker build -f docker/base.Dockerfile -t mono-base:latest .

# 3. Run all services
docker compose up --build
```

---

## Architecture

### Layered JARs

Spring Boot 3.x produces layered JARs with four layers, ordered by change frequency:

```
Layer                    Content                    Change Frequency
─────────────────────    ───────────────────────    ────────────────
dependencies             Third-party libs (~20MB)   Rarely
spring-boot-loader       Boot loader (~200KB)       Rarely
snapshot-dependencies    Internal modules           On rebuild
application              App classes                Frequently
```

### Multi-Stage Build

```
┌─────────────────────────────────────────────────────────────────┐
│                    docker/base.Dockerfile                        │
│                                                                  │
│  Stage 1: Builder (eclipse-temurin:21-jdk)                       │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │ mvn clean package -> fat JARs -> extract layers            │  │
│  └────────────────────────────────────────────────────────────┘  │
│                          |                                       │
│  Stage 2: Base (eclipse-temurin:21-jre)                          │
│  ┌────────────────────────────────────────────────────────────┐  │
│  │ dependencies/ + spring-boot-loader/  (~80MB, shared)       │  │
│  └────────────────────────────────────────────────────────────┘  │
│                          |                                       │
│                    mono-base:latest                               │
└──────────────────────────|──────────────────────────────────────┘
                           |
        ┌──────────────────┼──────────────────┐
        |                  |                  |
┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│ rest-sample  │  │ user-profile │  │  ... (x10)   │
│              │  │              │  │              │
│ snapshot-    │  │ snapshot-    │  │ snapshot-    │
│ dependencies │  │ dependencies │  │ dependencies │
│ application  │  │ application  │  │ application  │
│   (~1MB)     │  │   (~1MB)     │  │   (~1MB)     │
└──────────────┘  └──────────────┘  └──────────────┘
```

### Why This Works

Shared dependencies (Spring, Jackson, AWS SDK, etc.) are built once into `mono-base:latest`.
Each service image extends the base and adds only its own classes.

```
Without layered Docker:  10 x ~27MB = ~270MB (deps duplicated per image)
With layered Docker:     ~80MB base + 10 x ~1MB = ~90MB (deps shared)
```

---

## Files

| File | Purpose |
|------|---------|
| `docker/base.Dockerfile` | Shared base image with JRE and third-party dependencies |
| `docker/app.Dockerfile` | Per-service image that extends base with app-specific layers |
| `docker-compose.yml` | Orchestrates all 10 services with port mappings |
| `.dockerignore` | Excludes unnecessary files from build context |

---

## Commands

```bash
# Build base image only
docker build -f docker/base.Dockerfile -t mono-base:latest .

# Build a single service
docker compose build rest-sample

# Run a single service
docker compose up rest-sample

# Run all services
docker compose up --build

# Run in background
docker compose up -d

# View logs
docker compose logs -f rest-sample

# Stop all services
docker compose down
```

---

## Services

| Service | Port | Image |
|---------|------|-------|
| rest-sample | 8080 | rest-sample:latest |
| soap-sample | 8081 | soap-sample:latest |
| user-profile | 8082 | user-profile:latest |
| product | 8083 | product:latest |
| user-activity | 8084 | user-activity:latest |
| order | 8085 | order:latest |
| payment | 8086 | payment:latest |
| endorsement | 8087 | endorsement:latest |
| oauth-server | 9000 | oauth-server:latest |
| batch-sample | - | batch-sample:latest |

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Docker build fails | Ensure `mvn clean package -DskipTests` ran first |
| App won't start | Ensure base image is built: `docker build -f docker/base.Dockerfile -t mono-base:latest .` |
| Port conflict | Check if another process is using the port, or adjust mapping in `docker-compose.yml` |
| Stale image | Rebuild with `docker compose build --no-cache <service>` |
