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
├── architecture/           # System docs & decisions
│   ├── system.md          # Architecture overview
│   ├── decisions/         # ADRs
│   └── glossary.md        # Terms
│
├── service/               # Service modules
│   ├── service-rest/      # REST API (port 8080)
│   ├── service-batch/     # Batch jobs
│   └── service-soap/      # SOAP (port 8081)
│
├── common/                # Shared modules
│   ├── exception/         # Base/Business/Technical
│   ├── utils/             # JSON/Date/String
│   ├── env/               # Configuration
│   └── aws/               # AWS SDK wrappers (modular)
│       ├── aws-s3/        # S3 only
│       ├── aws-sqs/       # SQS only
│       └── aws-dynamodb/  # DynamoDB only
│
└── infra/                 # AWS CDK infrastructure
```

---

## Context Loading Order

When working on a module, load context in this order:

```
1. CLAUDE.md                  # This file (conventions)
2. architecture/system.md     # System architecture
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
mvn clean verify -pl service/service-rest -am

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

| Module | Path | Port |
|--------|------|------|
| REST | `service/service-rest` | 8080 |
| Batch | `service/service-batch` | - |
| SOAP | `service/service-soap` | 8081 |
| CDK | `infra` | - |

---

## Adding New Module

1. Create module directory with structure:
   ```
   module/
   ├── pom.xml
   ├── README.md
   ├── src/
   ├── tests/
   └── docs/
       ├── overview.md
       ├── api.md
       └── rules.md
   ```
2. Add to parent POM modules list
3. Write code with 100% test coverage
4. Run `mvn spotless:apply`

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Format fails | `mvn spotless:apply` |
| Coverage fails | Check `target/site/jacoco/` |
| Module not found | `mvn install -DskipTests` |
