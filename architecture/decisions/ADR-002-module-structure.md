# ADR-002: Self-Contained Module Structure

## Status
Accepted

## Context
Modules need to be understandable in isolation by both humans and AI agents.

## Decision
Each module is self-contained with code, tests, and documentation:

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

## Consequences

### Positive
- Everything needed is in one place
- AI agents can load minimal context
- Easy to onboard new developers
- Documentation stays current with code

### Negative
- More files to maintain
- Potential duplication across modules
