# ADR-001: Maven Multi-Module Structure

## Status
Accepted

## Context
Need to organize a large Java codebase with multiple services and shared libraries.

## Decision
Use Maven multi-module with:
- Parent POM for dependency management
- `components/` for application services
- `shared/` for reusable libraries

## Consequences

### Positive
- Single versioning across modules
- Consistent dependencies
- Atomic commits across modules

### Negative
- Full build required for releases
- Larger repository checkout
