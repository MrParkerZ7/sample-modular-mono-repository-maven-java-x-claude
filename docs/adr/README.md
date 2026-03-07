# Architectural Decision Records (ADR)

This directory contains Architectural Decision Records for the sample-mono-repository project.

## What is an ADR?

An ADR is a document that captures an important architectural decision made along with its context and consequences.

## ADR Format

Each ADR follows this template:

```markdown
# ADR-NNN: Title

## Status
[Proposed | Accepted | Deprecated | Superseded by ADR-XXX]

## Context
What is the issue that we're seeing that is motivating this decision?

## Decision
What is the change that we're proposing and/or doing?

## Consequences
What becomes easier or more difficult to do because of this change?
```

## Index

| ADR | Title | Status |
|-----|-------|--------|
| [ADR-001](ADR-001-maven-multi-module.md) | Use Maven Multi-Module Structure | Accepted |
| [ADR-002](ADR-002-100-percent-coverage.md) | Enforce 100% Code Coverage | Accepted |
| [ADR-003](ADR-003-google-java-format.md) | Use Google Java Format | Accepted |
| [ADR-004](ADR-004-aws-sdk-wrappers.md) | AWS SDK Wrappers | Accepted |
| [ADR-005](ADR-005-ai-native-documentation.md) | AI-Native Documentation Structure | Accepted |

## Creating a New ADR

1. Copy the template below
2. Name it `ADR-NNN-short-title.md` (use next number in sequence)
3. Fill in all sections
4. Add to index above
5. Submit for review

## Template

```markdown
# ADR-NNN: [Title]

## Status
Proposed

## Context
[Describe the context and problem]

## Decision
[Describe the decision]

## Consequences
### Positive
- [List positive consequences]

### Negative
- [List negative consequences]

### Neutral
- [List neutral observations]

## References
- [Links to relevant resources]
```
