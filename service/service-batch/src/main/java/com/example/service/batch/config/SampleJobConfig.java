package com.example.service.batch.config;

import com.example.service.batch.job.SampleTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/** Configuration for sample batch job. */
@Configuration
public class SampleJobConfig {

  @Bean
  public Job sampleJob(JobRepository jobRepository, Step sampleStep) {
    return new JobBuilder("sampleJob", jobRepository).start(sampleStep).build();
  }

  @Bean
  public Step sampleStep(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      SampleTasklet sampleTasklet) {
    return new StepBuilder("sampleStep", jobRepository)
        .tasklet(sampleTasklet, transactionManager)
        .build();
  }
}
