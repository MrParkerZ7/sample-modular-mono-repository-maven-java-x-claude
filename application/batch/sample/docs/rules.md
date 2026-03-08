# Batch Sample Service - Rules

## Error Handling

- Exceptions in tasklet cause step failure
- Job repository tracks execution status
- Failed jobs can be restarted from last checkpoint

## Usage Rules

1. Jobs should be idempotent when possible
2. Use chunk-based processing for large data sets
3. Configure appropriate commit intervals
4. Log progress for monitoring

## Job Execution

- Jobs run on application startup by default
- Use `spring.batch.job.enabled=false` to disable auto-run
- Schedule jobs externally for production use

## Transaction Management

- Each step runs in its own transaction
- Tasklet commits after successful execution
- Rollback on exception

## Thread Safety

- Tasklets should be stateless
- Use StepContext for step-scoped data
- JobRepository handles concurrent access
