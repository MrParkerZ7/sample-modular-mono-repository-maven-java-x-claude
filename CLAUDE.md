# CLAUDE.md

> AI agent instructions for this Java Maven monorepo.

---

## Quick Reference

```
BUILD:    mvn clean verify       # Full build with tests
FORMAT:   mvn spotless:apply     # Fix formatting (REQUIRED)
TEST:     mvn test               # Run tests only
COVERAGE: 100% line coverage     # Enforced by JaCoCo
```

---

## AI-Native Module Pattern

Each module is **self-contained** with code, tests, and documentation:

```
module/
├── src/              # Source code
├── tests/            # Test code
├── docs/
│   ├── overview.md   # What & why (purpose, design)
│   ├── api.md        # Contracts (endpoints, interfaces)
│   └── rules.md      # Business logic (validation, constraints)
└── README.md         # Quick start guide
```

**Key Principle**: Documentation lives with the code it describes.

---

## Project Structure

```
repo/
├── docs/                  # Documentation
│   ├── system.md          # Architecture overview
│   ├── glossary.md        # Terms
│   ├── adr/               # ADRs
│   ├── analysis/          # Analysis docs
│   └── diagrams/          # Draw.io diagrams
│
├── common/                # Shared utility modules
│   ├── exception/         # Base/Business/Technical
│   ├── utils/             # JSON/Date/String
│   ├── env/               # Configuration
│   └── aws/               # AWS SDK wrappers (modular)
│       ├── aws-s3/        # S3 only
│       ├── aws-sqs/       # SQS only
│       └── aws-dynamodb/  # DynamoDB only
│
├── model/                 # Shared domain model modules
│   └── base/              # BaseEntity, AuditableEntity, interfaces
│
├── application/             # Application modules (hierarchical)
│   ├── api/               # API aggregator (REST services)
│   │   ├── sample/        # Sample REST API (port 8080)
│   │   ├── user-profile/  # User Profile API (port 8082)
│   │   ├── product/       # Product API (port 8083)
│   │   ├── user-activity/ # User Activity API (port 8084)
│   │   ├── order/         # Order API (port 8085)
│   │   ├── payment/       # Payment API (port 8086)
│   │   ├── endorsement/   # Endorsement API (port 8087)
│   │   └── oauth/         # OAuth2 Auth Server (port 9000)
│   ├── batch/             # Batch aggregator
│   │   └── sample/        # Sample batch job
│   └── soap/              # SOAP aggregator
│       └── sample/        # Sample SOAP (port 8081)
│
└── infra/                 # AWS CDK infrastructure
```

---

## Context Loading Order

When working on a module, load context in this order:

```
1. CLAUDE.md                  # This file (conventions)
2. docs/system.md             # System architecture
3. module/README.md           # Module quick start
4. module/docs/overview.md    # Module design
5. module/docs/api.md         # API contracts
6. module/docs/rules.md       # Business rules
7. Target source file         # Code to modify
8. Target test file           # Expected behavior
```

---

## Build Commands

```bash
# Full build (primary)
mvn clean verify

# Compile only
mvn clean compile

# Build specific module
mvn clean verify -pl application/api -am

# Format code (REQUIRED before commit)
mvn spotless:apply
```

---

## Key Conventions

### Dependencies
- Versions in parent POM `<dependencyManagement>`
- Child modules: NO version declarations

### Exceptions
```java
// Business errors (client fault)
throw new BusinessException("ERR_CODE", "Message");

// Technical errors (system fault)
throw new TechnicalException("ERR_CODE", "Message", cause);
```

### Testing
- 100% line coverage required
- Pattern: `@ExtendWith(MockitoExtension.class)`
- Naming: `*Test.java`

### Code Style
- Google Java Format (Spotless)
- No emojis
- Javadoc for public members

---

## Module Quick Reference

| Type | Module | Artifact ID | Path | Port |
|------|--------|-------------|------|------|
| Model | Base | `model-base` | `model/base` | - |
| API | Sample | `rest-sample` | `application/api/sample` | 8080 |
| API | User Profile | `user-profile` | `application/api/user-profile` | 8082 |
| API | Product | `product` | `application/api/product` | 8083 |
| API | User Activity | `user-activity` | `application/api/user-activity` | 8084 |
| API | Order | `order` | `application/api/order` | 8085 |
| API | Payment | `payment` | `application/api/payment` | 8086 |
| API | Endorsement | `endorsement` | `application/api/endorsement` | 8087 |
| API | OAuth | `oauth` | `application/api/oauth` | 9000 |
| Batch | Sample | `batch-sample` | `application/batch/sample` | - |
| SOAP | Sample | `soap-sample` | `application/soap/sample` | 8081 |
| Infra | CDK | `infra` | `infra` | - |

**Note**: Each application type (api, batch, soap) is an aggregator supporting multiple services.

### Layer Dependencies
```
Common  (utilities, no domain knowledge)
  |
Model   (shared domain models, extends common)
  |
Application (API endpoints, depends on model + common)
```

---

## Adding New Module

### New Common Module
```
common/new-module/
├── pom.xml              # relativePath: ../../pom.xml
├── README.md
├── src/
├── tests/
└── docs/
```
Add `<module>new-module</module>` to `common/pom.xml`

### New Model Module
```
model/new-model/
├── pom.xml              # relativePath: ../../pom.xml
├── README.md            # artifactId: model-new-model
├── src/
├── tests/
└── docs/
```
Add `<module>new-model</module>` to `model/pom.xml`

### New Application Module
```
application/{type}/new-service/
├── pom.xml              # relativePath: ../../../pom.xml
├── README.md            # artifactId: {type}-new-service
├── src/
├── tests/
└── docs/
```
Add `<module>new-service</module>` to `application/{type}/pom.xml`

Example: `application/batch/notify-daily/` with artifactId `batch-notify-daily`

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Format fails | `mvn spotless:apply` |
| Coverage fails | Check `target/site/jacoco/` |
| Module not found | `mvn install -DskipTests` |
