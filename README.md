# Sample Mono Repository - Maven Java

> **AI-Native Modular Monorepo** with self-contained modules combining code, tests, and documentation.

---

## AI-Native Module Pattern

Each module is **self-contained** with code, tests, and documentation together:

```
module/
├── src/              # Source code
├── tests/            # Test code
├── docs/
│   ├── overview.md   # What & why (purpose, design, architecture)
│   ├── api.md        # Contracts (endpoints, interfaces, methods)
│   └── rules.md      # Business logic (validation, constraints)
└── README.md         # Quick start guide
```

**Key Principle**: Documentation lives with the code it describes - making each module easy for both humans and AI agents to understand in isolation.

---

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         MONO REPOSITORY                                 │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│  ┌─────────────────┐                                                    │
│  │  architecture/  │  System-level documentation & decisions            │
│  └─────────────────┘                                                    │
│                                                                         │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                      service/ (modules)                         │    │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐              │    │
│  │  │ service-rest│  │service-batch│  │ service-soap│              │    │
│  │  │  ├ src/     │  │  ├ src/     │  │  ├ src/     │              │    │
│  │  │  ├ tests/   │  │  ├ tests/   │  │  ├ tests/   │              │    │
│  │  │  ├ docs/    │  │  ├ docs/    │  │  ├ docs/    │              │    │
│  │  │  └ README   │  │  └ README   │  │  └ README   │              │    │
│  │  └─────────────┘  └─────────────┘  └─────────────┘              │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                      common/ (modules)                          │    │
│  │  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐         │    │
│  │  │exception │  │  utils   │  │   env    │  │   aws    │         │    │
│  │  └──────────┘  └──────────┘  └──────────┘  └──────────┘         │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  ┌─────────────────────────────────────────────────────────────────┐    │
│  │                         infra/                                  │    │
│  │  AWS CDK Infrastructure (self-contained module)                 │    │
│  └─────────────────────────────────────────────────────────────────┘    │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Project Structure

```
sample-mono-repository/
│
├── pom.xml                              # Parent POM (BOM + plugin management)
├── README.md                            # This file
├── CLAUDE.md                            # AI agent instructions
│
├── architecture/                        # ══ SYSTEM-LEVEL DOCUMENTATION ══
│   ├── system.md                        # System architecture & diagrams
│   ├── decisions/                       # Architectural Decision Records
│   │   ├── README.md                    # ADR index & template
│   │   ├── ADR-001-maven-structure.md
│   │   └── ADR-002-ai-native-docs.md
│   └── glossary.md                      # Domain terms & abbreviations
│
├── components/                          # ══ APPLICATION COMPONENTS ══
│   │
│   ├── service-rest/                    # REST API Component (Port 8080)
│   │   ├── pom.xml
│   │   ├── README.md                    # Component overview
│   │   ├── src/
│   │   │   └── main/java/.../rest/
│   │   │       ├── Application.java
│   │   │       └── controller/
│   │   ├── tests/
│   │   │   └── java/.../rest/
│   │   │       └── controller/
│   │   └── docs/
│   │       ├── overview.md              # Component purpose & design
│   │       ├── api.md                   # API endpoints & contracts
│   │       └── rules.md                 # Business rules & validation
│   │
│   ├── service-batch/                   # Batch Processing Component
│   │   ├── pom.xml
│   │   ├── README.md
│   │   ├── src/
│   │   ├── tests/
│   │   └── docs/
│   │       ├── overview.md
│   │       ├── api.md                   # Job definitions & triggers
│   │       └── rules.md                 # Processing rules
│   │
│   ├── service-soap/                    # SOAP Service Component (Port 8081)
│   │   ├── pom.xml
│   │   ├── README.md
│   │   ├── src/
│   │   ├── tests/
│   │   └── docs/
│   │       ├── overview.md
│   │       ├── api.md                   # WSDL & operations
│   │       └── rules.md
│   │
│   └── infra/                           # Infrastructure Component (AWS CDK)
│       ├── pom.xml
│       ├── README.md
│       ├── src/
│       ├── tests/
│       └── docs/
│           ├── overview.md              # Infrastructure architecture
│           ├── api.md                   # Construct interfaces
│           └── rules.md                 # Deployment rules
│
├── shared/                              # ══ SHARED LIBRARIES ══
│   │
│   ├── exception/                       # Exception Handling
│   │   ├── pom.xml
│   │   ├── README.md
│   │   ├── src/
│   │   │   └── main/java/.../exception/
│   │   │       ├── BaseException.java
│   │   │       ├── BusinessException.java
│   │   │       └── TechnicalException.java
│   │   ├── tests/
│   │   └── docs/
│   │       ├── overview.md
│   │       └── rules.md                 # When to use each exception
│   │
│   ├── utils/                           # Utility Functions
│   │   ├── pom.xml
│   │   ├── README.md
│   │   ├── src/
│   │   │   └── main/java/.../utils/
│   │   │       ├── JsonUtils.java
│   │   │       ├── DateUtils.java
│   │   │       └── StringUtils.java
│   │   ├── tests/
│   │   └── docs/
│   │       ├── overview.md
│   │       └── api.md                   # Utility method reference
│   │
│   ├── env/                             # Environment Configuration
│   │   ├── pom.xml
│   │   ├── README.md
│   │   ├── src/
│   │   ├── tests/
│   │   └── docs/
│   │       ├── overview.md
│   │       └── rules.md                 # Configuration precedence
│   │
│   └── aws/                             # AWS SDK Wrappers
│       ├── pom.xml
│       ├── README.md
│       ├── src/
│       │   └── main/java/.../aws/
│       │       ├── S3ClientWrapper.java
│       │       ├── SqsClientWrapper.java
│       │       └── DynamoDbClientWrapper.java
│       ├── tests/
│       └── docs/
│           ├── overview.md
│           ├── api.md                   # Wrapper method reference
│           └── rules.md                 # Error handling rules
│
└── docs/                                # ══ ADDITIONAL RESOURCES ══
    └── diagrams/                        # Visual diagrams (Draw.io)
        ├── c4-context.drawio
        ├── c4-container.drawio
        └── infrastructure.drawio
```

---

## Module Structure Pattern

Each module is **self-contained** with code, tests, and documentation:

```
module/
├── pom.xml           # Build configuration
├── README.md         # Quick start guide
├── src/              # Source code
├── tests/            # Test code
└── docs/
    ├── overview.md   # What & why (purpose, design)
    ├── api.md        # Contracts (endpoints, interfaces)
    └── rules.md      # Business logic (validation, constraints)
```

### Documentation Files

| File | Purpose |
|------|---------|
| `README.md` | Quick start, build commands |
| `docs/overview.md` | Purpose, architecture, design decisions |
| `docs/api.md` | API endpoints, methods, contracts |
| `docs/rules.md` | Business rules, validation, constraints |

---

## Technology Stack

| Category | Technology | Version |
|----------|------------|---------|
| Runtime | Java | 21 (LTS) |
| Framework | Spring Boot | 3.4.3 |
| Build | Apache Maven | 3.9+ |
| Cloud | AWS SDK v2 | 2.25.70 |
| Infrastructure | AWS CDK | 2.130.0 |
| Coverage | JaCoCo | 0.8.12 (100% enforced) |
| Formatting | Spotless | 2.43.0 (Google Java Format) |

---

## Module Dependency Graph

```
┌─────────────────────────────────────────────────────────────────┐
│                        COMPONENTS                               │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐              │
│  │service-rest │  │service-batch│  │ service-soap│              │
│  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘              │
│         │                │                │                     │
│         └────────────────┼────────────────┘                     │
│                          │                                      │
│                          ▼                                      │
├─────────────────────────────────────────────────────────────────┤
│                         SHARED                                  │
│  ┌────────┐  ┌────────┐  ┌────────┐  ┌────────┐                 │
│  │  env   │  │ utils  │  │  aws   │──│exception│                │
│  └────────┘  └────────┘  └───┬────┘  └────────┘                 │
│                              │                                  │
│                              ▼                                  │
│                      AWS SDK / External                         │
└─────────────────────────────────────────────────────────────────┘
```

---

## Quick Start

### Build Commands

```bash
# Full build with tests and coverage
mvn clean verify

# Format code (REQUIRED before commit)
mvn spotless:apply

# Build specific component
mvn clean verify -pl components/service-rest -am

# Run tests only
mvn test
```

### Run Services

```bash
# REST API (http://localhost:8080)
cd components/service-rest && mvn spring-boot:run

# SOAP Service (http://localhost:8081)
cd components/service-soap && mvn spring-boot:run

# Batch Jobs
cd components/service-batch && mvn spring-boot:run
```

### Deploy Infrastructure

```bash
cd components/infra
cdk synth    # Generate CloudFormation
cdk deploy   # Deploy to AWS
```

---

## API Endpoints

### REST Service (Port 8080)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/health` | Health check |
| GET | `/api/samples/{id}` | Get sample by ID |

### SOAP Service (Port 8081)

| Endpoint | Description |
|----------|-------------|
| POST `/ws` | SOAP request handler |
| GET `/ws/sample.wsdl` | WSDL definition |

---

## Documentation Hierarchy

```
LEVEL 1: System (architecture/)
├── system.md           # Overall architecture
├── decisions/          # Why we made choices (ADRs)
└── glossary.md         # Terminology

LEVEL 2: Component (components/*/docs/)
├── overview.md         # Component architecture
├── api.md              # Interface contracts
└── rules.md            # Business rules

LEVEL 3: Code
├── README.md           # Quick reference
├── Javadoc             # API documentation
└── Test classes        # Usage examples
```

---

## AI Agent Guidelines

### Context Loading Order

```
1. CLAUDE.md                  # Build & conventions
2. architecture/system.md     # System architecture
3. module/README.md           # Module quick start
4. module/docs/overview.md    # Module design
5. module/docs/api.md         # API contracts
6. module/docs/rules.md       # Business rules
7. Target source file         # Code to modify
8. Target test file           # Expected behavior
```

### When Modifying a Module

1. Read `module/README.md` first
2. Check `module/docs/rules.md` for business constraints
3. Review `module/docs/api.md` for contracts
4. Read target source file
5. Read corresponding test file
6. Make changes following patterns
7. Update tests (100% coverage required)
8. Run `mvn spotless:apply`

---

## Key Conventions

### Exception Handling

```java
// Business errors (invalid input, rule violations)
throw new BusinessException("ERR_CODE", "User message");

// Technical errors (infrastructure failures)
throw new TechnicalException("ERR_CODE", "Technical message", cause);
```

### Code Quality

- **Coverage**: 100% line coverage (JaCoCo)
- **Format**: Google Java Format (Spotless)
- **Javadoc**: Required for public classes/methods
- **No emojis**: Never in code or comments

### Testing

```java
@ExtendWith(MockitoExtension.class)
class MyClassTest {
    @Mock private Dependency dep;
    @InjectMocks private MyClass myClass;

    @Test
    void shouldDoSomething() {
        // Arrange, Act, Assert
    }
}
```

---

## Adding New Modules

### New Service Module

```
service/service-new/
├── pom.xml           # Inherit from parent
├── README.md         # Quick start
├── src/              # Source code
├── tests/            # Test code
└── docs/
    ├── overview.md   # Purpose & design
    ├── api.md        # Endpoints
    └── rules.md      # Business rules
```

### New Common Module

```
common/common-new/
├── pom.xml           # Inherit from parent
├── README.md         # Quick start
├── src/              # Source code
├── tests/            # Test code
└── docs/
    ├── overview.md   # Purpose & design
    ├── api.md        # Methods & interfaces
    └── rules.md      # Usage rules
```

---

## License

MIT
