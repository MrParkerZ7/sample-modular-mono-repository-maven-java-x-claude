# Glossary & Domain Definitions

> A reference guide for terminology, abbreviations, and domain concepts used in this repository.

---

## Table of Contents

- [Technical Terms](#technical-terms)
- [Project-Specific Terms](#project-specific-terms)
- [Abbreviations](#abbreviations)
- [AWS Services](#aws-services)
- [Spring Framework](#spring-framework)
- [Build & Tooling](#build--tooling)
- [Domain Concepts](#domain-concepts)

---

## Technical Terms

### Architecture Terms

| Term | Definition |
|------|------------|
| **Modular Monolith** | Single deployable application composed of loosely coupled modules with clear boundaries |
| **Monorepo** | Single repository containing multiple projects or modules |
| **Multi-module** | Maven/Gradle project structure with parent and child modules |
| **BOM** | Bill of Materials - centralized dependency version management |
| **ADR** | Architectural Decision Record - documented design decision |
| **C4 Model** | Architecture visualization: Context, Container, Component, Code |

### Software Patterns

| Term | Definition |
|------|------------|
| **Wrapper Pattern** | Class that wraps another class to simplify or modify its interface |
| **Exception Hierarchy** | Structured inheritance of exception types for categorized error handling |
| **Dependency Injection** | Design pattern where dependencies are provided rather than created |
| **Configuration Provider** | Pattern for abstracting configuration source (env vars, props, etc.) |

### Testing Terms

| Term | Definition |
|------|------------|
| **Unit Test** | Test of a single class or method in isolation |
| **Integration Test** | Test of multiple components working together |
| **Code Coverage** | Percentage of code executed during tests |
| **Line Coverage** | Percentage of code lines executed during tests |
| **Branch Coverage** | Percentage of conditional branches tested |
| **Mock** | Test double that simulates behavior of real objects |

---

## Project-Specific Terms

### Modules

| Term | Definition |
|------|------------|
| **common-exception** | Module providing base exception classes |
| **common-utils** | Module providing utility functions (JSON, Date, String) |
| **common-env** | Module providing environment/configuration access |
| **common-aws** | Module providing AWS SDK wrappers |
| **service-rest** | REST API service module (Spring Web) |
| **service-batch** | Batch processing module (Spring Batch) |
| **service-soap** | SOAP web service module (Spring WS) |
| **infra** | Infrastructure as Code module (AWS CDK) |

### Exception Types

| Term | Definition | When to Use |
|------|------------|-------------|
| **BaseException** | Abstract parent of all custom exceptions | Never directly |
| **BusinessException** | Exception for business rule violations | Invalid input, business logic errors |
| **TechnicalException** | Exception for infrastructure failures | Database errors, API failures |

### Constructs (CDK)

| Term | Definition |
|------|------------|
| **NetworkConstruct** | CDK construct defining VPC, subnets, routing |
| **StorageConstruct** | CDK construct defining S3, SQS, DynamoDB |
| **RestApiConstruct** | CDK construct defining API Gateway for REST |
| **SoapApiConstruct** | CDK construct defining API Gateway for SOAP |

---

## Abbreviations

### General

| Abbrev | Full Form |
|--------|-----------|
| API | Application Programming Interface |
| REST | Representational State Transfer |
| SOAP | Simple Object Access Protocol |
| WSDL | Web Services Description Language |
| JSON | JavaScript Object Notation |
| XML | eXtensible Markup Language |
| HTTP | HyperText Transfer Protocol |
| HTTPS | HTTP Secure |
| URL | Uniform Resource Locator |
| URI | Uniform Resource Identifier |
| CRUD | Create, Read, Update, Delete |
| DTO | Data Transfer Object |
| POJO | Plain Old Java Object |

### Build & Development

| Abbrev | Full Form |
|--------|-----------|
| POM | Project Object Model (Maven) |
| JAR | Java Archive |
| WAR | Web Application Archive |
| JDK | Java Development Kit |
| JRE | Java Runtime Environment |
| JVM | Java Virtual Machine |
| IDE | Integrated Development Environment |
| CI/CD | Continuous Integration / Continuous Deployment |
| VCS | Version Control System |

### AWS

| Abbrev | Full Form |
|--------|-----------|
| AWS | Amazon Web Services |
| CDK | Cloud Development Kit |
| S3 | Simple Storage Service |
| SQS | Simple Queue Service |
| DDB | DynamoDB |
| VPC | Virtual Private Cloud |
| EC2 | Elastic Compute Cloud |
| IAM | Identity and Access Management |
| ARN | Amazon Resource Name |
| AZ | Availability Zone |

### Testing

| Abbrev | Full Form |
|--------|-----------|
| TDD | Test-Driven Development |
| BDD | Behavior-Driven Development |
| SUT | System Under Test |
| AAA | Arrange, Act, Assert (test pattern) |

---

## AWS Services

### Storage Services

| Service | Purpose | Used In |
|---------|---------|---------|
| **S3** | Object storage for files and data | StorageConstruct, S3ClientWrapper |
| **DynamoDB** | NoSQL key-value database | StorageConstruct, DynamoDbClientWrapper |

### Messaging Services

| Service | Purpose | Used In |
|---------|---------|---------|
| **SQS** | Message queue for async processing | StorageConstruct, SqsClientWrapper |

### Networking Services

| Service | Purpose | Used In |
|---------|---------|---------|
| **VPC** | Isolated virtual network | NetworkConstruct |
| **API Gateway** | HTTP API management | RestApiConstruct, SoapApiConstruct |
| **Subnet** | Network segment within VPC | NetworkConstruct |

### Subnet Types

| Type | Internet Access | Use Case |
|------|-----------------|----------|
| **Public** | Direct | Load balancers, bastion hosts |
| **Private** | Via NAT | Application servers |
| **Isolated** | None | Databases, sensitive resources |

---

## Spring Framework

### Core Annotations

| Annotation | Purpose |
|------------|---------|
| `@SpringBootApplication` | Main application entry point |
| `@RestController` | REST API controller |
| `@Service` | Business logic service |
| `@Repository` | Data access component |
| `@Component` | Generic Spring-managed bean |
| `@Configuration` | Configuration class |
| `@Bean` | Method that produces a bean |

### Web Annotations

| Annotation | Purpose |
|------------|---------|
| `@GetMapping` | HTTP GET endpoint |
| `@PostMapping` | HTTP POST endpoint |
| `@PathVariable` | URL path parameter |
| `@RequestBody` | Request body binding |
| `@RequestParam` | Query parameter |

### Testing Annotations

| Annotation | Purpose |
|------------|---------|
| `@SpringBootTest` | Full application context test |
| `@WebMvcTest` | Controller layer test |
| `@DataJpaTest` | Repository layer test |
| `@MockBean` | Mock Spring bean |
| `@ExtendWith(MockitoExtension.class)` | Enable Mockito |

---

## Build & Tooling

### Maven Phases

| Phase | Description |
|-------|-------------|
| `validate` | Validate project (Spotless check runs here) |
| `compile` | Compile source code |
| `test` | Run unit tests |
| `package` | Create JAR/WAR |
| `verify` | Run integration tests, coverage check |
| `install` | Install to local repository |
| `deploy` | Deploy to remote repository |

### Maven Commands

| Command | Purpose |
|---------|---------|
| `mvn clean` | Delete target directories |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn verify` | Full build with coverage |
| `mvn install` | Install to local repo |
| `mvn spotless:apply` | Fix code formatting |
| `mvn spotless:check` | Check code formatting |
| `mvn -pl module -am` | Build module with dependencies |

### Quality Tools

| Tool | Purpose | Configuration |
|------|---------|---------------|
| **JaCoCo** | Code coverage | 100% line coverage required |
| **Spotless** | Code formatting | Google Java Format |
| **Surefire** | Test execution | JUnit 5 |

### CDK Commands

| Command | Purpose |
|---------|---------|
| `cdk synth` | Generate CloudFormation template |
| `cdk deploy` | Deploy to AWS |
| `cdk diff` | Compare with deployed stack |
| `cdk destroy` | Delete stack |

---

## Domain Concepts

### Sample Domain (Demo)

This repository uses a simplified "Sample" domain for demonstration:

| Concept | Description |
|---------|-------------|
| **Sample** | A generic entity with ID and data |
| **GetSampleRequest** | Request to retrieve a sample |
| **GetSampleResponse** | Response containing sample data |

### Error Codes

| Pattern | Meaning | Example |
|---------|---------|---------|
| `ERR_*` | Generic error | `ERR_INVALID_INPUT` |
| `S3_*` | S3 operation error | `S3_PUT_ERROR` |
| `SQS_*` | SQS operation error | `SQS_SEND_ERROR` |
| `DDB_*` | DynamoDB error | `DDB_GET_ERROR` |

---

## AI-Specific Terms

| Term | Definition |
|------|------------|
| **LLM** | Large Language Model (e.g., GPT, Claude) |
| **Context Window** | Maximum text an LLM can process at once |
| **Hallucination** | LLM generating incorrect but confident information |
| **AI-Native** | Architecture designed for AI agent comprehension |
| **CLAUDE.md** | Instruction file for Claude Code AI agent |
| **AI_CONTEXT.md** | Module-level AI guidance file |

---

## Quick Reference

### File Extensions

| Extension | Type |
|-----------|------|
| `.java` | Java source code |
| `.xml` | XML configuration (pom.xml) |
| `.yml` / `.yaml` | YAML configuration |
| `.md` | Markdown documentation |
| `.json` | JSON data |
| `.properties` | Java properties |

### Port Assignments

| Port | Service |
|------|---------|
| 8080 | REST API (service-rest) |
| 8081 | SOAP API (service-soap) |

### Package Structure

```
com.example
├── common
│   ├── exception    # Exception classes
│   ├── utils        # Utility classes
│   ├── env          # Environment config
│   └── aws          # AWS wrappers
├── service
│   ├── rest         # REST service
│   ├── batch        # Batch service
│   └── soap         # SOAP service
└── infra
    └── construct    # CDK constructs
```

---

*This glossary should be updated when new terms or concepts are introduced.*
