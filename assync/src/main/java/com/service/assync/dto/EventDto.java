package com.service.assync.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDto  {
    private String name;
    private String address;
    private String description;

}
