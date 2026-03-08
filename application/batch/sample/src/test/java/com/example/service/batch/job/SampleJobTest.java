package com.example.service.batch.job;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBatchTest
class SampleJobTest {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired private Job sampleJob;

  @Test
  void testSampleJob() throws Exception {
    jobLauncherTestUtils.setJob(sampleJob);
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
  }
}
