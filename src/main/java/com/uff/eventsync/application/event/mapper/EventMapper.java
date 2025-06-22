package com.uff.eventsync.application.event.mapper;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;
import com.uff.eventsync.application.event.dto.*;
import com.uff.eventsync.application.user.dto.UserSummaryDTO;
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

    public static EventDetailResponseDTO toDetailResponseDTO(Event event) {
        CategoryResponseDTO categoryDTO = new CategoryResponseDTO(
                event.getCategory().getId(),
                event.getCategory().getName()
        );

        UserSummaryDTO organizerDTO = new UserSummaryDTO(
                event.getOrganizer().getId(),
                event.getOrganizer().getName()
        );

        return new EventDetailResponseDTO(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getLocation(),
                event.getDate(),
                event.getStartTime(),
                event.getEndTime(),
                event.getEventType(),
                event.getTicketUrl(),
                event.getOfficialSiteUrl(),
                event.getOnlineUrl(),
                categoryDTO,
                organizerDTO
        );
    }

    public static EventSummaryResponseDTO toSummaryResponseDTO(Event event) {
        return new EventSummaryResponseDTO(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getStartTime(),
                event.getLocation(),
                event.getCategory().getName()
        );
    }

    public static void updateEntityFromDTO(Event eventToUpdate, EventUpdateRequestDTO dto, Category category) {
        eventToUpdate.setName(dto.name());
        eventToUpdate.setDescription(dto.description());
        eventToUpdate.setLocation(dto.location());
        eventToUpdate.setDate(dto.date());
        eventToUpdate.setStartTime(dto.startTime());
        eventToUpdate.setEndTime(dto.endTime());
        eventToUpdate.setEventType(dto.eventType());
        eventToUpdate.setTicketUrl(dto.ticketUrl());
        eventToUpdate.setOfficialSiteUrl(dto.officialSiteUrl());
        eventToUpdate.setOnlineUrl(dto.onlineUrl());
        eventToUpdate.setCategory(category);
    }


}
