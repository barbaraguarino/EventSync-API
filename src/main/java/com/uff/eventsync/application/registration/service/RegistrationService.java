package com.uff.eventsync.application.registration.service;

import com.uff.eventsync.domain.user.entity.User;

import java.util.UUID;

public interface RegistrationService {
    void checkInToEvent(UUID eventId, User currentUser);
}
