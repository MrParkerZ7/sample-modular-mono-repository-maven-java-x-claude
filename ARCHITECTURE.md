# Architecture Decision Records & Design Principles

> This document captures key architectural decisions and design principles for the AI-Native Modular Monorepo.

---

## Table of Contents

- [Design Principles](#design-principles)
- [Architectural Patterns](#architectural-patterns)
- [Decision Records](#decision-records)
- [Module Design Guidelines](#module-design-guidelines)
- [AI-Native Considerations](#ai-native-considerations)

---

## Design Principles

### 1. Single Responsibility Modules

Each module has one clear purpose:

| Module | Single Responsibility |
|--------|----------------------|
| `common-exception` | Exception class hierarchy only |
| `common-utils` | Stateless utility functions |
| `common-env` | Environment/configuration access |
| `common-aws` | AWS SDK operation wrappers |
| `service-rest` | REST API endpoints |
| `service-batch` | Batch job execution |
| `service-soap` | SOAP web services |
| `infra` | Infrastructure definitions |

### 2. Dependency Inversion

```
Services depend on → Abstractions (common modules)
Common modules depend on → Nothing or other common modules
Infrastructure depends on → Nothing (isolated)
```

### 3. Explicit Over Implicit

- No "magic" configuration
- All dependencies declared in `pom.xml`
- All conventions documented in `CLAUDE.md`
- All exceptions have explicit error codes

### 4. Fail Fast

- 100% code coverage enforced at build time
- Code formatting checked before compilation
- No runtime configuration errors

---

## Architectural Patterns

### Pattern 1: Exception Hierarchy

```
BaseException (abstract)
├── errorCode: String
├── message: String
└── cause: Throwable (optional)

BusinessException extends BaseException
└── For: Invalid input, business rule violations

TechnicalException extends BaseException
└── For: Database errors, API failures, infrastructure issues
```

**Usage**:
```java
// Business error (client's fault)
throw new BusinessException("INVALID_INPUT", "Email format is invalid");

// Technical error (system's fault)
throw new TechnicalException("DB_CONNECTION_FAILED", "Cannot connect to database", cause);
```

### Pattern 2: AWS SDK Wrapper

```
┌─────────────────────┐
│   Service Code      │
└──────────┬──────────┘
           │ calls
           ▼
┌─────────────────────┐
│  XxxClientWrapper   │  ← Simplified interface
├─────────────────────┤
│ - wraps SDK client  │
│ - translates errors │
│ - provides helpers  │
└──────────┬──────────┘
           │ uses
           ▼
┌─────────────────────┐
│   AWS SDK Client    │
└─────────────────────┘
```

**Benefits**:
- Simplified API for common operations
- Consistent error handling (→ TechnicalException)
- Testable (mock the wrapper, not SDK)

### Pattern 3: Configuration Provider

```
ConfigurationProvider.get("CONFIG_KEY")
       │
       ▼
┌──────────────────────────────┐
│ 1. Check explicit config map │
│ 2. Check environment vars    │
│ 3. Check system properties   │
│ 4. Return null if not found  │
└──────────────────────────────┘
```

**Lookup Order**:
1. Explicitly provided configuration (Map)
2. Environment variables (`System.getenv`)
3. System properties (`System.getProperty`)
4. `null` (not found)

### Pattern 4: Modular Monolith

```
┌─────────────────────────────────────────────┐
│              MONO REPOSITORY                │
├─────────────────────────────────────────────┤
│  ┌─────────┐  ┌─────────┐  ┌─────────┐      │
│  │ Module1 │  │ Module2 │  │ Module3 │      │
│  └────┬────┘  └────┬────┘  └────┬────┘      │
│       │            │            │           │
│       └────────────┼────────────┘           │
│                    │                        │
│               Shared Build                  │
│              Shared Versioning              │
│            Shared Quality Gates             │
└─────────────────────────────────────────────┘
```

**Characteristics**:
- Single repository, multiple modules
- Unified build and versioning
- Modules can be extracted to microservices later
- Clear internal boundaries

---

## Decision Records

### ADR-001: Use Maven Multi-Module Structure

**Status**: Accepted

**Context**: Need to organize a large codebase with multiple services sharing common code.

**Decision**: Use Maven multi-module project with parent POM for dependency management.

**Consequences**:
- (+) Single versioning across modules
- (+) Consistent dependency versions
- (+) Easy local development
- (-) Full build required for releases
- (-) Larger initial checkout

### ADR-002: Enforce 100% Code Coverage

**Status**: Accepted

**Context**: Need to ensure code quality and prevent regressions.

**Decision**: Use JaCoCo to enforce 100% line coverage at build time.

**Consequences**:
- (+) High confidence in code quality
- (+) Forces testable design
- (+) Documentation through tests
- (-) Slower development initially
- (-) May encourage trivial tests for getters/setters

### ADR-003: Use Google Java Format

**Status**: Accepted

**Context**: Need consistent code formatting without debates.

**Decision**: Use Spotless with Google Java Format, enforced at validate phase.

**Consequences**:
- (+) Zero formatting discussions
- (+) Consistent code style
- (+) AI agents can follow format
- (-) Different from default IDE settings
- (-) Requires running `mvn spotless:apply`

### ADR-004: AWS SDK Wrappers

**Status**: Accepted

**Context**: Direct AWS SDK usage leads to inconsistent error handling and verbose code.

**Decision**: Create wrapper classes in `common-aws` module for each AWS service.

**Consequences**:
- (+) Simplified API
- (+) Consistent error translation
- (+) Easy to mock in tests
- (-) Additional abstraction layer
- (-) Must maintain wrappers

### ADR-005: Spring Boot for Services

**Status**: Accepted

**Context**: Need a framework for building production-ready services.

**Decision**: Use Spring Boot 3.x for all service modules.

**Consequences**:
- (+) Production-ready features (health, metrics)
- (+) Large ecosystem
- (+) Well-documented
- (-) Framework lock-in
- (-) Startup time for serverless

### ADR-006: AWS CDK for Infrastructure

**Status**: Accepted

**Context**: Need to define AWS infrastructure as code.

**Decision**: Use AWS CDK with Java (same language as services).

**Consequences**:
- (+) Type-safe infrastructure code
- (+) Same language as application
- (+) IDE support, refactoring
- (-) CDK version updates needed
- (-) Learning curve

### ADR-007: AI-Native Documentation Structure

**Status**: Accepted

**Context**: AI coding agents need to understand codebase quickly with limited context windows.

**Decision**: Implement layered documentation with `CLAUDE.md` and `AI_CONTEXT.md` files.

**Consequences**:
- (+) AI agents understand codebase faster
- (+) Reduced hallucination risk
- (+) Self-documenting architecture
- (-) Additional documentation to maintain
- (-) May become outdated

---

## Module Design Guidelines

### Creating a New Common Module

1. **Purpose**: Define single responsibility
2. **Location**: `common/module-name/`
3. **Dependencies**: Minimal, only what's needed
4. **Tests**: 100% coverage required

```
common/module-name/
├── pom.xml                    # Inherit from ../../pom.xml
├── AI_CONTEXT.md              # Optional: AI guidance
└── src/
    ├── main/java/com/example/common/modulename/
    │   └── MainClass.java
    └── test/java/com/example/common/modulename/
        └── MainClassTest.java
```

### Creating a New Service Module

1. **Purpose**: Define API or job responsibility
2. **Location**: `service/service-name/`
3. **Entry Point**: `Application.java` with `@SpringBootApplication`
4. **Configuration**: `application.yml`

```
service/service-name/
├── pom.xml                    # With spring-boot-maven-plugin
├── AI_CONTEXT.md              # Optional: AI guidance
└── src/
    ├── main/
    │   ├── java/com/example/service/name/
    │   │   ├── Application.java
    │   │   └── controller/ or job/
    │   └── resources/
    │       └── application.yml
    └── test/java/com/example/service/name/
```

### Creating a New CDK Construct

1. **Purpose**: Define infrastructure responsibility
2. **Location**: `infra/src/main/java/.../construct/`
3. **Pattern**: Extends `Construct`

```java
public class XxxConstruct extends Construct {
    public XxxConstruct(final Construct scope, final String id) {
        super(scope, id);
        // Define AWS resources
    }
}
```

---

## AI-Native Considerations

### Context Window Optimization

| Strategy | Implementation |
|----------|---------------|
| Flat structure | Avoid deep nesting (max 4 levels) |
| Short paths | `common/common-utils/` not `libs/shared/common/utilities/` |
| Descriptive names | `BusinessException` not `BizEx` |
| Single file purpose | One class per file |

### Reducing Hallucination Risk

1. **Explicit conventions**: All rules in `CLAUDE.md`
2. **No hidden magic**: Configuration visible in files
3. **Consistent patterns**: Same structure everywhere
4. **Clear dependencies**: `pom.xml` is source of truth
5. **Test as documentation**: Examples in test files

### AI Context Loading Order

For any task, AI agents should load context in this order:

```
1. CLAUDE.md              # Build and conventions
2. README.md              # Architecture overview
3. Target module pom.xml  # Dependencies
4. Target file            # Code to modify
5. Related test file      # Expected behavior
6. AI_CONTEXT.md          # If exists, module details
```

### Machine-Readable Markers

Use consistent markers AI can parse:

```java
// Purpose: Brief description of class purpose
public class ClassName {

    // Entry point: Describe what this method does
    public void mainMethod() {
        // Implementation
    }
}
```

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0.0 | 2024-01 | Initial architecture |

---

*This document should be updated when significant architectural decisions are made.*
