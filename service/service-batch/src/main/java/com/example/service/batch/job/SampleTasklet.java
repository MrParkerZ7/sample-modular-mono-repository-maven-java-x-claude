package com.example.service.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/** Sample tasklet that demonstrates batch processing. */
@Component
public class SampleTasklet implements Tasklet {

  private static final Logger logger = LoggerFactory.getLogger(SampleTasklet.class);

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    logger.info("Executing sample tasklet");
    contribution.incrementReadCount();
    contribution.incrementWriteCount(1);
    return RepeatStatus.FINISHED;
  }
}
