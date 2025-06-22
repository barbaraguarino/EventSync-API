package com.uff.eventsync.application.registration.service;

import com.uff.eventsync.application.registration.exception.BusinessRuleException;
import com.uff.eventsync.application.registration.exception.UserAlreadyCheckedInException;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.event.repository.EventRepository;
import com.uff.eventsync.domain.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EventRepository eventRepository;

    public RegistrationServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void checkInToEvent(UUID eventId, User currentUser) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));
        LocalDateTime eventStartDateTime = event.getDate().atTime(event.getStartTime());
        if (eventStartDateTime.isBefore(LocalDateTime.now())) {
            throw new BusinessRuleException("Cannot check in to an event that has already occurred.");
        }
        if (event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new BusinessRuleException("Cannot check in to your own event.");
        }
        if (event.getAttendees().contains(currentUser)) {
            throw new UserAlreadyCheckedInException("User has already checked in to this event.");
        }
        event.getAttendees().add(currentUser);
        eventRepository.save(event);
    }
}
