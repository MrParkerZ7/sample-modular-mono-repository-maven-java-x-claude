# Batch Sample Service - API

## SampleJobConfig

Configuration class defining the batch job structure.

### Beans

| Bean | Type | Description |
|------|------|-------------|
| `sampleJob` | Job | Main batch job |
| `sampleStep` | Step | Single step within the job |

### Job Definition

```java
@Bean
public Job sampleJob(JobRepository jobRepository, Step sampleStep)
```

### Step Definition

```java
@Bean
public Step sampleStep(JobRepository jobRepository,
                       PlatformTransactionManager transactionManager,
                       SampleTasklet sampleTasklet)
```

## SampleTasklet

Tasklet implementation for batch processing.

### Interface

```java
public class SampleTasklet implements Tasklet
```

### Methods

| Method | Parameters | Returns | Description |
|--------|------------|---------|-------------|
| `execute` | StepContribution, ChunkContext | RepeatStatus | Executes batch logic |

### Execution Flow

1. Increments read count on StepContribution
2. Increments write count on StepContribution
3. Logs execution details
4. Returns `RepeatStatus.FINISHED`

## Job Parameters

| Parameter | Type | Description |
|-----------|------|-------------|
| None | - | No parameters required |
