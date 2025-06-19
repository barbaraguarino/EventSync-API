package com.uff.eventsync.application.event.dto;

import com.uff.eventsync.application.categories.dto.CategoryResponseDTO;
import com.uff.eventsync.application.user.dto.UserSummaryDTO;
import com.uff.eventsync.domain.event.enums.EventType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record EventDetailResponseDTO(
        UUID id,
        String name,
        String description,
        String location,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        EventType eventType,
        String ticketUrl,
        String officialSiteUrl,
        String onlineUrl,
        CategoryResponseDTO category,
        UserSummaryDTO organizer
) {}
