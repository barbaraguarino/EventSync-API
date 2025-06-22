package com.uff.eventsync.application.event.service;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventDetailResponseDTO;
import com.uff.eventsync.application.event.mapper.EventMapper;
import com.uff.eventsync.domain.categories.entity.Category;
import com.uff.eventsync.domain.categories.repository.CategoryRepository;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.event.repository.EventRepository;
import com.uff.eventsync.domain.user.entity.User;
import com.uff.eventsync.shared.exception.UnauthorizedActionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Event createEvent(EventCreateRequestDTO eventData, User organizer) {
        Category category = categoryRepository.findById(eventData.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + eventData.categoryId()));
        Event newEvent = EventMapper.toEntity(eventData, category, organizer);
        return eventRepository.save(newEvent);
    }

    @Override
    public EventDetailResponseDTO findEventById(UUID id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));

        return EventMapper.toDetailResponseDTO(event);
    }

    @Override
    public List<Event> findAllEventsSorted() {
        return eventRepository.findAllByOrderByDateAscStartTimeAsc();
    }

    @Override
    public void deleteEvent(UUID eventId, User currentUser) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));
        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new UnauthorizedActionException("User is not authorized to delete this event.");
        }
        eventRepository.delete(event);
    }
}
