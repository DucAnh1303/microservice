package com.service.microservice.batch.excute.job;

import com.service.microservice.batch.response.ListBatch;
import com.service.microservice.batch.excute.step.BatchProcess;
import com.service.microservice.batch.excute.step.BatchWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
public class BatchConfig {

    @Autowired
    private BatchWriter batchWriter;

    @Autowired
    private BatchProcess batchProcess;

    @Bean
    Job createGetPartnerTransactionJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("batchJob", jobRepository)
                .flow(createGetPartnerTransactionStep(jobRepository, transactionManager))
                .end().build();
    }

    @Bean
    Step createGetPartnerTransactionStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("batchStep", jobRepository)
                .<String, ListBatch>chunk(5, transactionManager)
                .allowStartIfComplete(true)
                .reader(read())
                .processor(batchProcess)
                .writer(batchWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<String> read() {
        log.info("=== reading ===");
        try {
            log.debug("Reading from delegate: {}", "ok");
            return new ListItemReader<>(List.of("ducanh1"));

        } catch (Exception e) {
            log.error("Error reading from delegate: {}", e.getMessage());
            return null;
        }
    }
}
