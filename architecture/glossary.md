# Glossary

> Domain terms, abbreviations, and technical terminology.

---

## Project Terms

| Term | Definition |
|------|------------|
| **Component** | Self-contained module with src, tests, and docs |
| **Shared** | Reusable library used by multiple components |
| **Construct** | AWS CDK building block for infrastructure |

---

## Exception Types

| Term | When to Use |
|------|-------------|
| **BusinessException** | Invalid input, rule violations, client errors |
| **TechnicalException** | Database errors, API failures, system errors |

---

## Abbreviations

### General
| Abbrev | Full Form |
|--------|-----------|
| API | Application Programming Interface |
| REST | Representational State Transfer |
| SOAP | Simple Object Access Protocol |
| JSON | JavaScript Object Notation |
| CRUD | Create, Read, Update, Delete |
| DTO | Data Transfer Object |

### AWS
| Abbrev | Full Form |
|--------|-----------|
| CDK | Cloud Development Kit |
| S3 | Simple Storage Service |
| SQS | Simple Queue Service |
| DDB | DynamoDB |
| VPC | Virtual Private Cloud |
| IAM | Identity and Access Management |

### Build
| Abbrev | Full Form |
|--------|-----------|
| POM | Project Object Model |
| BOM | Bill of Materials |
| JAR | Java Archive |

### Testing
| Abbrev | Full Form |
|--------|-----------|
| TDD | Test-Driven Development |
| AAA | Arrange, Act, Assert |

---

## Maven Phases

| Phase | Purpose |
|-------|---------|
| validate | Check formatting (Spotless) |
| compile | Compile source code |
| test | Run unit tests |
| verify | Coverage check (JaCoCo) |
| install | Install to local repo |

---

## Spring Annotations

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Application entry point |
| `@RestController` | REST endpoint class |
| `@Service` | Business logic |
| `@Component` | Spring bean |
| `@Mock` | Mockito test double |
| `@InjectMocks` | Inject mocks into target |

---

## Error Code Patterns

| Prefix | Category |
|--------|----------|
| `ERR_` | Generic errors |
| `S3_` | S3 operations |
| `SQS_` | SQS operations |
| `DDB_` | DynamoDB operations |
| `DB_` | Database errors |

---

## Ports

| Port | Service |
|------|---------|
| 8080 | REST API |
| 8081 | SOAP Service |
