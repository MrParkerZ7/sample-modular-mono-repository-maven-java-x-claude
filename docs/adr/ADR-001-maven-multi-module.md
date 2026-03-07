# ADR-001: Use Maven Multi-Module Structure

## Status
Accepted

## Context

We need to organize a large Java codebase with:
- Multiple services (REST, SOAP, Batch)
- Shared libraries (exceptions, utilities, AWS wrappers)
- Infrastructure code (AWS CDK)

Options considered:
1. **Separate repositories** - One repo per module
2. **Single flat module** - All code in one Maven module
3. **Maven multi-module** - Single repo with parent POM and child modules
4. **Gradle multi-project** - Similar to Maven but with Gradle

## Decision

Use Maven multi-module project structure with:
- Parent POM for centralized dependency and plugin management
- Aggregator POMs for module groups (common, service)
- Child POMs inheriting from parent

```
root/
├── pom.xml (parent)
├── common/
│   ├── pom.xml (aggregator)
│   ├── common-exception/
│   ├── common-utils/
│   └── ...
├── service/
│   ├── pom.xml (aggregator)
│   ├── service-rest/
│   └── ...
└── infra/
    └── pom.xml
```

## Consequences

### Positive
- **Single versioning**: All modules share same version (1.0.0-SNAPSHOT)
- **Consistent dependencies**: Parent POM ensures same library versions across modules
- **Atomic commits**: Related changes across modules in single commit
- **Easy local development**: `mvn install` makes all modules available
- **Simplified CI/CD**: Single build pipeline for all modules
- **IDE support**: IntelliJ and Eclipse handle multi-module projects well

### Negative
- **Full build required**: Release process builds all modules
- **Larger checkout**: Developers get all code even if working on one module
- **Build time**: Full build takes longer than single module
- **Potential coupling**: Easy to accidentally introduce tight coupling

### Neutral
- Requires Maven 3.9+ for optimal performance
- Team must learn multi-module POM inheritance

## References
- [Maven Multi-Module Projects](https://maven.apache.org/guides/mini/guide-multiple-modules.html)
- [Google's Monorepo](https://research.google/pubs/pub45424/)
