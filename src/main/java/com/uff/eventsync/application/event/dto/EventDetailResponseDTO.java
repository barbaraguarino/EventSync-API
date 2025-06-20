package com.uff.eventsync.application.event.dto;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;
import com.uff.eventsync.application.user.dto.UserSummaryDTO;
import com.uff.eventsync.domain.event.enums.EventType;
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
public class EventDetailResponseDTO extends RepresentationModel<EventDetailResponseDTO> {
    private UUID id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private EventType eventType;
    private String ticketUrl;
    private String officialSiteUrl;
    private String onlineUrl;
    private CategoryResponseDTO category;
    private UserSummaryDTO organizer;
}
