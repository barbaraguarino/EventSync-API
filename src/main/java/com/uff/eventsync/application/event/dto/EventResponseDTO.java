package com.uff.eventsync.application.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDTO extends RepresentationModel<EventResponseDTO> {
    private UUID id;
    private String name;
    private LocalDate date;
    private String categoryName;
}