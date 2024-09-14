package com.service.assync.controller;

import com.service.assync.dto.EventDto;
import com.service.assync.response.HandleAssyncEvent;
import com.service.assync.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("hande-assync")
    public ResponseEntity<?> handle() {
        List<EventDto> eventDtoList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            EventDto eventDto = EventDto.builder().name("AidenPhung: " + i).address("address" + i).description("description " + i).build();
            eventDtoList.add(eventDto);
        }
        HandleAssyncEvent handleAssyncEvent = new HandleAssyncEvent(this,eventDtoList);
        applicationEventPublisher.publishEvent(handleAssyncEvent);
        return ResponseEntity.ok(Response.builder().code(200).message("success").data(eventDtoList).build());
    }


}