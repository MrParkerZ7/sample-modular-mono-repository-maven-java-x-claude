# Fat JAR Dependency Duplication

## Problem Statement

In a modular monorepo with transitive dependencies, a shared library can appear
multiple times across the dependency tree:

```
common/
  some-util       (imports a.jar)
  some-common     (imports some-util + a.jar)
application/
  some-app        (imports some-util + some-common + a.jar)
```

If `a.jar` is 5MB, does `some-app` end up with 15MB from `a.jar` alone (3 copies)?

## Resolution in Maven

**No duplication occurs.** Maven's dependency resolver deduplicates automatically.

### During compilation (`mvn clean install`)

- Each module's thin JAR contains only its own compiled classes.
- Dependencies are resolved from the local `.m2` repository at build/runtime.
- Maven's dependency tree flattens transitive dependencies and includes each
  artifact exactly once.

Verify with:

```bash
mvn dependency:tree -pl <module>
```

Duplicates appear as `(omitted for duplicate)` or `(omitted for conflict)`.

### During fat JAR packaging (`spring-boot-maven-plugin repackage`)

Spring Boot flattens all dependencies into `BOOT-INF/lib/` with one copy each:

```
some-app-1.0.0.jar
  BOOT-INF/lib/
    a.jar              <- single copy
    some-util.jar
    some-common.jar
```

### Where duplication DOES occur

When deploying **multiple fat JARs** side by side, each is self-contained:

```
target/
  some-app-1.0.0.jar        <- contains a.jar (5MB)
  another-app-1.0.0.jar     <- also contains a.jar (5MB)
```

This is by design for independent deployability.

## Mitigation Strategies (cross-app duplication)

| Approach | Description | Trade-off |
|----------|-------------|-----------|
| Thin launcher | `spring-boot-thin-launcher` downloads deps at startup | Smaller artifact, needs network at runtime |
| Layered JARs | Spring Boot layers mode for Docker image caching | Shared layers cached, only app code changes per deploy |
| Shared lib folder | Thin JARs + shared `lib/` directory | Smallest disk, harder dependency management |
| Docker multi-stage | Base image contains shared dependencies | Standard production approach |

## Implemented Solution: Docker Multi-Stage + Layered JARs

Spring Boot 3.x produces layered JARs by default with four layers:

```
dependencies            <- third-party libs (~20MB, rarely changes)
spring-boot-loader      <- boot loader (~200KB, rarely changes)
snapshot-dependencies   <- internal modules (changes on rebuild)
application             <- app classes (changes frequently)
```

### Architecture

```
docker/
  base.Dockerfile       <- builds shared base image with dependencies layer
  app.Dockerfile        <- extends base, adds only app-specific layers

docker-compose.yml      <- orchestrates all 10 services
```

### How it solves duplication

```
WITHOUT layered Docker (10 fat JARs):
  10 x ~27MB = ~270MB total (shared deps duplicated in each image)

WITH layered Docker (shared base):
  base image:  ~80MB  (JRE + shared dependencies, built once)
  per service: ~1MB   (snapshot-dependencies + application layers)
  total:       ~90MB  (base cached, only app layers per service)
```

### Usage

```bash
# Step 1: Build all modules
mvn clean package -DskipTests

# Step 2: Build base image (shared dependencies)
docker build -f docker/base.Dockerfile -t mono-base:latest .

# Step 3: Build and run all services
docker compose up --build

# Or build a single service
docker compose build rest-sample
docker compose up rest-sample
```

## Status

- Maven/Java: Resolved by build tool (no action needed).
- Docker: Implemented with multi-stage builds and layered JARs.
- Node/Webpack: To be investigated (Webpack tree-shaking and code splitting may
  handle this differently).
