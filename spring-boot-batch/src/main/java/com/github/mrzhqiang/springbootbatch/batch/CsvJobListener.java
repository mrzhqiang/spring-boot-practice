package com.github.mrzhqiang.springbootbatch.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CsvJobListener implements JobExecutionListener {
  long startTime;

  @Override public void beforeJob(JobExecution jobExecution) {
    startTime = System.currentTimeMillis();
    System.out.println("任务处理开始");
  }

  @Override public void afterJob(JobExecution jobExecution) {
    long interval = System.currentTimeMillis() - startTime;
    System.out.println("任务处理结束");
    System.out.println("耗时：" + interval + " ms");
  }
}
