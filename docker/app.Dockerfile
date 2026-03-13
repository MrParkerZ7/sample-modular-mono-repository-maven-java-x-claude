# Multi-stage Dockerfile for individual Spring Boot applications.
# Extends the base image to add only app-specific layers.
#
# Required build args:
#   MODULE_PATH  - path to the module (e.g., application/api/sample)
#   JAR_NAME     - JAR filename (e.g., rest-sample-1.0.0-SNAPSHOT.jar)
#
# Usage:
#   docker build -f docker/app.Dockerfile \
#     --build-arg MODULE_PATH=application/api/sample \
#     --build-arg JAR_NAME=rest-sample-1.0.0-SNAPSHOT.jar \
#     -t rest-sample:latest .

# --- Stage 1: Extract layers from the specific app JAR ---
FROM eclipse-temurin:21-jdk AS extractor

ARG MODULE_PATH
ARG JAR_NAME

WORKDIR /build
COPY ${MODULE_PATH}/target/${JAR_NAME} app.jar

RUN java -Djarmode=tools -jar app.jar extract --layers --launcher --destination /layers

# --- Stage 2: Final image built on shared base ---
FROM mono-base:latest

ARG MODULE_PATH

# Only app-specific layers (small, changes frequently)
COPY --from=extractor /layers/snapshot-dependencies/ ./
COPY --from=extractor /layers/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
