package com.uff.eventsync.application.event.service;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventDetailResponseDTO;
import com.uff.eventsync.application.event.dto.EventResponseDTO;
import com.uff.eventsync.domain.user.entity.User;

import java.util.UUID;

public interface EventService {
    EventResponseDTO createEvent(EventCreateRequestDTO eventData, User organizer);
    EventDetailResponseDTO findEventById(UUID id);
}