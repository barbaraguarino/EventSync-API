package com.uff.eventsync.application.user.dto;

public record AuthenticationResultDTO(
        String token,
        String email,
        String name
) {}
