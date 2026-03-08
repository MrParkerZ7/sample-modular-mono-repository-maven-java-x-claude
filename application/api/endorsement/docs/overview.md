# Endorsement Service - Overview

## Purpose

Manages insurance policy endorsements (amendments/modifications to existing policies). Endorsements follow an approval workflow before being applied to the policy.

## Design

- **Endorsement** entity extends `AuditableEntity` from model-base
- References existing entities: `userId`, `orderId`, `productId`
- Generates sequential endorsement numbers (END-YYYY-NNNNN)
- In-memory storage with ConcurrentHashMap

## Endorsement Types

| Type | Description |
|------|-------------|
| ADD_COVERAGE | Add new coverage to policy |
| REMOVE_COVERAGE | Remove existing coverage |
| CHANGE_BENEFICIARY | Change policy beneficiary |
| CHANGE_SUM_INSURED | Modify insured amount |
| EXTEND_TERM | Extend policy term |
| CANCEL_POLICY | Cancel entire policy |
