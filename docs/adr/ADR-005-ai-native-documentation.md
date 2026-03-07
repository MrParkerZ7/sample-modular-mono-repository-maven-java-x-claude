# ADR-005: AI-Native Documentation Structure

## Status
Accepted

## Context

Modern software development increasingly involves AI coding assistants (Claude, GitHub Copilot, Cursor, etc.). These tools:
- Have limited context windows (varying by model)
- Need to understand codebase structure quickly
- Can hallucinate if context is unclear
- Benefit from explicit conventions and patterns

Traditional documentation is optimized for human reading, not machine comprehension. We need a documentation strategy that serves both humans and AI agents effectively.

## Decision

Implement a layered, AI-native documentation structure:

### Layer 1: System Level (Repository Root)
```
README.md          - System overview, architecture diagrams
CLAUDE.md          - AI agent instructions (build, conventions)
ARCHITECTURE.md    - Design decisions, patterns
GLOSSARY.md        - Domain terms, abbreviations
```

### Layer 2: Domain Level (docs/)
```
docs/
├── analysis/      - Deep architectural analysis
├── diagrams/      - Visual architecture (C4, etc.)
└── adr/           - Architectural Decision Records
```

### Layer 3: Module Level (Each module)
```
module/
├── pom.xml        - Dependencies (machine-readable)
├── README.md      - Module-specific docs (if needed)
└── AI_CONTEXT.md  - AI comprehension hints (optional)
```

### AI-Specific Files

**CLAUDE.md** contains:
- Build commands with explanations
- Code quality requirements
- Module structure quick reference
- Key conventions
- Common tasks with examples

**AI_CONTEXT.md** (per module, optional) contains:
- Module purpose (one line)
- Key classes and their roles
- Dependencies explained
- Public API summary
- Usage examples

### Naming Conventions for AI
- Descriptive file names: `BusinessException.java` not `BizEx.java`
- Consistent patterns: Same structure in all modules
- Flat structure: Avoid deep nesting (max 4 levels)
- Clear package names: `com.example.common.exception`

## Consequences

### Positive
- **Faster AI comprehension**: Clear structure reduces context needed
- **Reduced hallucination**: Explicit conventions prevent guessing
- **Self-documenting**: Architecture visible in structure
- **Human benefit**: Clearer docs help new developers too
- **Maintainable**: Documentation lives with code, less likely to go stale
- **IDE integration**: Standard files recognized by tools

### Negative
- **Additional files**: More documentation to maintain
- **Staleness risk**: AI-specific docs may become outdated
- **Learning curve**: Team needs to understand new conventions
- **Overhead**: Initial setup time for new modules

### Neutral
- CLAUDE.md name is specific to Claude but concept is universal
- AI_CONTEXT.md format not standardized across industry
- May need updates as AI capabilities evolve

## Implementation

1. **CLAUDE.md**: Already exists, maintained with each significant change
2. **ARCHITECTURE.md**: Created with ADRs consolidated
3. **GLOSSARY.md**: Created with domain terms
4. **AI_CONTEXT.md**: Add to complex modules only (optional)

### Context Loading Priority for AI Agents
```
1. CLAUDE.md              # Build and conventions
2. README.md              # Architecture overview
3. Target module pom.xml  # Dependencies
4. Target source file     # Code to modify
5. Related test file      # Expected behavior
6. AI_CONTEXT.md          # Module details (if exists)
```

## References
- [Claude Code Documentation](https://docs.anthropic.com/claude/docs)
- [Cursor AI Context](https://cursor.sh/docs)
- [Prompt Engineering Guide](https://www.promptingguide.ai/)
