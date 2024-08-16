package com.service.assync.config;

import com.service.assync.dto.EventDto;
import com.service.assync.response.HandleAssyncEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AsSyncListener {

    @Async
    @EventListener
    public void handleEvent(HandleAssyncEvent handleAssyncEvent) throws InterruptedException {
        Thread.sleep(10000);
        log.info("Received event size: {}", handleAssyncEvent.getEventDtoList().size());
        for (EventDto eventDto : handleAssyncEvent.getEventDtoList()) {
            log.info("Finished handling event: {}", eventDto);
        }
    }
}
