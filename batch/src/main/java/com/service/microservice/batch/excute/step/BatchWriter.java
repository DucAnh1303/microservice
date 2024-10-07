package com.service.microservice.batch.excute.step;

import com.service.microservice.batch.response.BatchResponse;
import com.service.microservice.batch.response.ListBatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BatchWriter implements ItemWriter<ListBatch>, StepExecutionListener {


    @Value("${batch.max-record}")
    private int recordSize;

    @Override
    public void write(Chunk<? extends ListBatch> chunk) {
        chunk.forEach(data -> {
            update(data.getResponseList());
            // TODO, Data storage
//            data.getFileHistory().setStatus(StatusFileHistory.MIGRATED);
//            fileHistoryRepository.save(data.getFileHistory());
            log.info("file {} is migrated", "success");
        });
    }

    private void update(List<BatchResponse> batchResponses) {
        for (int i = 0; i < batchResponses.size(); i += recordSize) {
            List<BatchResponse> batch =
                    batchResponses.subList(i, Math.min(i + recordSize, batchResponses.size()));
            log.info("size of records that saved: {}", batch.size());
        }
    }
}
