package com.example.service.batch.job;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;

@ExtendWith(MockitoExtension.class)
class SampleTaskletTest {

  @Mock private StepContribution stepContribution;

  @Mock private ChunkContext chunkContext;

  private SampleTasklet tasklet;

  @BeforeEach
  void setUp() {
    tasklet = new SampleTasklet();
  }

  @Test
  void testExecute() {
    RepeatStatus status = tasklet.execute(stepContribution, chunkContext);

    assertEquals(RepeatStatus.FINISHED, status);
    verify(stepContribution).incrementReadCount();
    verify(stepContribution).incrementWriteCount(1);
  }
}
