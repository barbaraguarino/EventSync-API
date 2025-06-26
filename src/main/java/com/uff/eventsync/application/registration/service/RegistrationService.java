package com.uff.eventsync.application.registration.service;

import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;

import java.util.List;
import java.util.UUID;

public interface RegistrationService {
    void checkInToEvent(UUID eventId, User currentUser);
    List<Event> findAttendedEventsForUser(User currentUser);
    void deleteCheckInToEvent(UUID eventId, User currentUser);
}
