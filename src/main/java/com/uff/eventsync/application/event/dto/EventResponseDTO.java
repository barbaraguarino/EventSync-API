package com.uff.eventsync.application.event.dto;

import java.time.LocalDate;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String name,
        LocalDate date,
        String categoryName
) {}