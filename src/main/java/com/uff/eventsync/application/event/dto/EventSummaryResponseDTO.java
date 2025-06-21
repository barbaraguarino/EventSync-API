package com.uff.eventsync.application.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventSummaryResponseDTO extends RepresentationModel<EventSummaryResponseDTO> {
    private UUID id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private String location;
    private String categoryName;
}
