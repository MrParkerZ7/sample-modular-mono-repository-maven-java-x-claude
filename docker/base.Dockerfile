# Base image with shared dependencies for all Spring Boot applications.
# Build once, reuse across all service images to avoid dependency duplication.
#
# Usage:
#   docker build -f docker/base.Dockerfile -t mono-base:latest .

# --- Stage 1: Build all modules ---
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build
COPY pom.xml .
COPY common/ common/
COPY model/ model/
COPY application/ application/
COPY infra/ infra/

RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests -q

# Extract layers from a representative app to get shared dependencies
RUN java -Djarmode=tools -jar application/api/sample/target/rest-sample-1.0.0-SNAPSHOT.jar extract --layers --launcher --destination /layers

# --- Stage 2: Base runtime with shared dependencies ---
FROM eclipse-temurin:21-jre AS base

WORKDIR /app

# These layers are shared across all services (cached by Docker)
COPY --from=builder /layers/dependencies/ ./
COPY --from=builder /layers/spring-boot-loader/ ./

LABEL maintainer="monorepo"
LABEL description="Base image with shared Spring Boot dependencies"
