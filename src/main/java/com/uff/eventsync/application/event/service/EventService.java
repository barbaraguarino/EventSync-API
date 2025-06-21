package com.uff.eventsync.application.event.service;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventDetailResponseDTO;
import com.uff.eventsync.application.event.dto.EventResponseDTO;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface EventService {
    EventResponseDTO createEvent(EventCreateRequestDTO eventData, User organizer);
    EventDetailResponseDTO findEventById(UUID id);
    List<Event> findAllEventsSorted();
    void deleteEvent(UUID eventId, User currentUser);
}