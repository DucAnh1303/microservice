package com.service.microservice.batch.excute.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class SchedulerConfiguration {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @Scheduled(cron = "${batch.cron}")
    public void scheduleJob() throws Exception {
        log.info("PartnerTransaction Job scheduler started to work");
        jobLauncher.run(job, new JobParametersBuilder()
                .addLong("uniqueness", System.nanoTime()).toJobParameters());
        log.info("PartnerTransaction Job scheduler finished to work");
    }
}
