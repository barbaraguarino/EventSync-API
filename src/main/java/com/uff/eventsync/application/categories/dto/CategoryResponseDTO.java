package com.uff.eventsync.application.categories.dto;

import java.util.UUID;

public record CategoryResponseDTO(
        UUID id,
        String name
){}
