package com.uff.eventsync.application.event.service;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventDetailResponseDTO;
import com.uff.eventsync.application.event.dto.EventResponseDTO;
import com.uff.eventsync.application.event.mapper.EventMapper;
import com.uff.eventsync.domain.categories.entity.Category;
import com.uff.eventsync.domain.categories.repository.CategoryRepository;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.event.repository.EventRepository;
import com.uff.eventsync.domain.user.entity.User;
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
    public EventResponseDTO createEvent(EventCreateRequestDTO eventData, User organizer) {
        Category category = categoryRepository.findById(eventData.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + eventData.categoryId()));

        Event newEvent = EventMapper.toEntity(eventData, category, organizer);

        Event savedEvent = eventRepository.save(newEvent);

        return EventMapper.toResponseDTO(savedEvent);
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
}
