# System Architecture

> System-level architecture documentation for the AI-Native Modular Monorepo.

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

**Key Principle**: Documentation lives with the code it describes - making each module easy for both humans and AI agents to understand in isolation.

---

## System Context (C4 Level 1)

```
┌─────────────────────────────────────────────────────────────────────────┐
│                              EXTERNAL ACTORS                            │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐ │
│  │ Web Client  │  │ Mobile App  │  │ External    │  │ AI Coding Agent │ │
│  │ (Browser)   │  │             │  │ Systems     │  │ (Claude/Copilot)│ │
│  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘  └────────┬────────┘ │
└─────────┼────────────────┼────────────────┼──────────────────┼──────────┘
          │ HTTP           │ HTTP           │ SOAP             │ Code
          ▼                ▼                ▼                  ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                    SAMPLE MONO REPOSITORY SYSTEM                        │
│ ┌─────────────────────────────────────────────────────────────────────┐ │
│ │                        SERVICE LAYER                                │ │
│ │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐                  │ │
│ │  │ REST API    │  │ SOAP API    │  │ Batch Jobs  │                  │ │
│ │  │ (Port 8080) │  │ (Port 8081) │  │ (Scheduled) │                  │ │
│ │  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘                  │ │
│ └─────────┼────────────────┼────────────────┼─────────────────────────┘ │
│           │                │                │                           │
│           ▼                ▼                ▼                           │
│ ┌─────────────────────────────────────────────────────────────────────┐ │
│ │                        SHARED LAYER                                 │ │
│ │  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐             │ │
│ │  │Exception │  │  Utils   │  │   Env    │  │   AWS    │             │ │
│ │  │Handling  │  │(JSON/Date│  │  Config  │  │ Wrappers │             │ │
│ │  └──────────┘  └──────────┘  └──────────┘  └────┬─────┘             │ │
│ └────────────────────────────────────────────────┼────────────────────┘ │
└──────────────────────────────────────────────────┼──────────────────────┘
                                                   │
                                                   ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                          AWS CLOUD                                      │
│  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐  ┌─────────┐        │
│  │   VPC   │  │   S3    │  │   SQS   │  │DynamoDB │  │   API   │        │
│  │ Network │  │ Storage │  │  Queue  │  │  Table  │  │ Gateway │        │
│  └─────────┘  └─────────┘  └─────────┘  └─────────┘  └─────────┘        │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Container Diagram (C4 Level 2)

```
┌─────────────────────────────────────────────────────────────────────────┐
│                         COMPONENTS                                      │
│                                                                         │
│  ┌───────────────────────────────────────────────────────────────────┐  │
│  │                      service-rest                                 │  │
│  │  ┌─────────────┐  ┌─────────────┐                                 │  │
│  │  │HealthCtrl   │  │SampleCtrl   │  Spring Boot 3.4               │  │
│  │  │ GET /health │  │GET /samples │  Port 8080                     │  │
│  │  └─────────────┘  └─────────────┘                                 │  │
│  └───────────────────────────────────────────────────────────────────┘  │
│                                                                         │
│  ┌───────────────────────────────────────────────────────────────────┐  │
│  │                      service-soap                                 │  │
│  │  ┌─────────────┐  ┌─────────────┐                                 │  │
│  │  │WebSvcConfig │  │SampleEndpt  │  Spring WS                     │  │
│  │  │ WSDL setup  │  │ SOAP ops    │  Port 8081                     │  │
│  │  └─────────────┘  └─────────────┘                                 │  │
│  └───────────────────────────────────────────────────────────────────┘  │
│                                                                         │
│  ┌───────────────────────────────────────────────────────────────────┐  │
│  │                      service-batch                                │  │
│  │  ┌─────────────┐  ┌─────────────┐                                 │  │
│  │  │SampleJobCfg │  │SampleTasklet│  Spring Batch                  │  │
│  │  │ Job config  │  │ Job step    │  HSQLDB                        │  │
│  │  └─────────────┘  └─────────────┘                                 │  │
│  └───────────────────────────────────────────────────────────────────┘  │
│                                                                         │
│  ┌───────────────────────────────────────────────────────────────────┐  │
│  │                         infra (CDK)                               │  │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐              │  │
│  │  │ Network  │ │ Storage  │ │ RestApi  │ │ SoapApi  │              │  │
│  │  │Construct │ │Construct │ │Construct │ │Construct │              │  │
│  │  └──────────┘ └──────────┘ └──────────┘ └──────────┘              │  │
│  └───────────────────────────────────────────────────────────────────┘  │
│                                                                         │
├─────────────────────────────────────────────────────────────────────────┤
│                          SHARED                                         │
│                                                                         │
│  ┌────────────┐  ┌────────────┐  ┌────────────┐  ┌────────────┐         │
│  │ exception  │  │   utils    │  │    env     │  │    aws     │         │
│  │            │  │            │  │            │  │            │         │
│  │ Base       │  │ JsonUtils  │  │ ConfigProv │  │ S3Wrapper  │         │
│  │ Business   │  │ DateUtils  │  │ EnvManager │  │ SqsWrapper │         │
│  │ Technical  │  │ StringUtils│  │            │  │ DdbWrapper │         │
│  └────────────┘  └────────────┘  └────────────┘  └────────────┘         │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Technology Stack

| Layer | Technology | Version | Purpose |
|-------|------------|---------|---------|
| Runtime | Java | 21 LTS | Language runtime |
| Framework | Spring Boot | 3.4.3 | Application framework |
| Build | Apache Maven | 3.9+ | Build automation |
| Cloud SDK | AWS SDK v2 | 2.25.70 | AWS integration |
| Infrastructure | AWS CDK | 2.130.0 | Infrastructure as Code |
| Coverage | JaCoCo | 0.8.12 | 100% coverage enforcement |
| Formatting | Spotless | 2.43.0 | Google Java Format |

---

## Design Principles

### 1. Self-Contained Modules

Each module contains everything needed to understand it:

```
module/
├── src/              # Source code
├── tests/            # Test code
├── docs/
│   ├── overview.md   # What & why
│   ├── api.md        # Contracts
│   └── rules.md      # Business logic
└── README.md         # Quick start
```

### 2. Explicit Boundaries

```
Service modules depend on → Common modules
Common modules depend on → Nothing (or other common)
Infrastructure depends on → Nothing (isolated)
```

### 3. Documentation Close to Code

Documentation lives with the code it describes:
- `docs/overview.md` - Purpose and design
- `docs/api.md` - Contracts and interfaces
- `docs/rules.md` - Business rules and constraints

### 4. AI-Readable Structure

- Predictable paths
- Descriptive names
- Self-contained modules
- Machine-parseable configs

---

## Component Dependency Matrix

| Component | Dependencies |
|-----------|--------------|
| service-rest | shared/exception, shared/env |
| service-batch | shared/exception, shared/utils, shared/env |
| service-soap | shared/exception, shared/utils, shared/env |
| infra | None (standalone CDK) |
| shared/aws | shared/exception, AWS SDK |
| shared/utils | Jackson |
| shared/env | None |
| shared/exception | None |

---

## Data Flow

### REST API Request Flow

```
Client Request
      │
      ▼
┌─────────────┐
│ API Gateway │ (AWS)
└──────┬──────┘
       │
       ▼
┌─────────────┐
│service-rest │
│ Controller  │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ shared/aws  │
│ S3/SQS/DDB  │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ AWS Services│
└─────────────┘
```

### Batch Job Flow

```
Scheduler/Trigger
      │
      ▼
┌─────────────┐
│service-batch│
│  Job Config │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│  Tasklet    │
│ (Step Exec) │
└──────┬──────┘
       │
       ▼
┌─────────────┐
│ shared/aws  │
│ Operations  │
└─────────────┘
```

---

## Exception Handling Architecture

```
┌────────────────────────────────────────────────────┐
│               BaseException (abstract)             │
│  - errorCode: String                               │
│  - message: String                                 │
│  - cause: Throwable                                │
└────────────────────────┬───────────────────────────┘
                         │
          ┌──────────────┴──────────────┐
          │                             │
          ▼                             ▼
┌─────────────────────┐     ┌─────────────────────┐
│  BusinessException  │     │ TechnicalException  │
│                     │     │                     │
│ - Invalid input     │     │ - DB connection     │
│ - Rule violations   │     │ - API failures      │
│ - Business errors   │     │ - Infrastructure    │
└─────────────────────┘     └─────────────────────┘
```

---

## Configuration Hierarchy

```
ConfigurationProvider.get("KEY")
         │
         ├─1→ Explicit Configuration Map
         │
         ├─2→ Environment Variables (System.getenv)
         │
         ├─3→ System Properties (System.getProperty)
         │
         └─4→ null (not found)
```

---

## Infrastructure Resources (CDK)

### NetworkConstruct
- VPC: 10.0.0.0/16, 2 AZs
- Subnets: Public, Private, Isolated
- NAT Gateway for private subnet egress

### StorageConstruct
- S3 Bucket: Object storage
- SQS Queue: Message queue
- DynamoDB Table: NoSQL database

### RestApiConstruct
- API Gateway: HTTP API
- Routes to REST service

### SoapApiConstruct
- API Gateway: HTTP API
- Routes to SOAP service

---

## Quality Gates

| Gate | Tool | Requirement |
|------|------|-------------|
| Code Coverage | JaCoCo | 100% line coverage |
| Code Format | Spotless | Google Java Format |
| Tests | JUnit 5 | All pass |
| Build | Maven | Clean verify |

---

## Version Information

| Component | Version |
|-----------|---------|
| Project | 1.0.0-SNAPSHOT |
| Java | 21 |
| Spring Boot | 3.4.3 |
| AWS SDK | 2.25.70 |
| AWS CDK | 2.130.0 |
