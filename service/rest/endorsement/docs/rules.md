# Endorsement Service - Rules

## Workflow

Endorsements follow a linear approval workflow:

```
PENDING -> APPROVED -> APPLIED
PENDING -> REJECTED
```

## Status Transitions

| From | To | Action |
|------|-----|--------|
| PENDING | APPROVED | approve |
| PENDING | REJECTED | reject (requires reason) |
| APPROVED | APPLIED | apply |

## Endorsement Numbers

- Format: `END-{year}-{sequence}` (e.g., END-2024-00001)
- Auto-generated on creation
- Unique and sequential

## Constraints

- Rejection requires a reason
- All endorsements start as PENDING
- effectiveDate is optional (set by requester)
- Each endorsement references one order, one user, and one product
