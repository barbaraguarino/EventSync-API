package com.uff.eventsync.application.event.service;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventUpdateRequestDTO;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface EventService {
    Event createEvent(EventCreateRequestDTO eventData, User organizer);
    Event findEventById(UUID id);
    List<Event> findAllEventsSorted();
    void deleteEvent(UUID eventId, User currentUser);
    Event updateEvent(UUID eventId, EventUpdateRequestDTO eventData, User currentUser);
}