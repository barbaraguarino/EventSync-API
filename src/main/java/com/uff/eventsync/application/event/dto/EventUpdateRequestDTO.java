package com.uff.eventsync.application.event.dto;

import com.uff.eventsync.domain.event.enums.EventType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record EventUpdateRequestDTO(
        @NotBlank String name,
        @NotBlank String description,
        String location,
        @NotNull @FutureOrPresent LocalDate date,
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime,
        @NotNull EventType eventType,
        @URL String ticketUrl,
        @URL String officialSiteUrl,
        @URL String onlineUrl,
        @NotNull UUID categoryId
) {}
