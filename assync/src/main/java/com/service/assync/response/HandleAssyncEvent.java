package com.service.assync.response;

import com.service.assync.dto.EventDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class HandleAssyncEvent extends ApplicationEvent {
    private List<EventDto> eventDtoList = new ArrayList<EventDto>();

    public HandleAssyncEvent(Object source, List<EventDto> eventDtoList) {
        super(source);
        this.eventDtoList = eventDtoList;
    }
}
