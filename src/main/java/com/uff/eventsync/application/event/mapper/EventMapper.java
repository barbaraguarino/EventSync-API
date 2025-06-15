package com.uff.eventsync.application.event.mapper;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventResponseDTO;
import com.uff.eventsync.domain.categories.entity.Category;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;

public class EventMapper {

    public static Event toEntity(EventCreateRequestDTO dto, Category category, User organizer) {
        Event newEvent = new Event();
        newEvent.setName(dto.name());
        newEvent.setDescription(dto.description());
        newEvent.setLocation(dto.location());
        newEvent.setDate(dto.date());
        newEvent.setStartTime(dto.startTime());
        newEvent.setEndTime(dto.endTime());
        newEvent.setEventType(dto.eventType());
        newEvent.setTicketUrl(dto.ticketUrl());
        newEvent.setOfficialSiteUrl(dto.officialSiteUrl());
        newEvent.setOnlineUrl(dto.onlineUrl());

        newEvent.setCategory(category);
        newEvent.setOrganizer(organizer);

        return newEvent;
    }

    public static EventResponseDTO toResponseDTO(Event event) {
        return new EventResponseDTO(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getCategory().getName()
        );
    }
}
