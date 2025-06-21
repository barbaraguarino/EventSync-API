package com.uff.eventsync.presentation.event;

import com.uff.eventsync.application.event.dto.EventCreateRequestDTO;
import com.uff.eventsync.application.event.dto.EventDetailResponseDTO;
import com.uff.eventsync.application.event.dto.EventResponseDTO;
import com.uff.eventsync.application.event.dto.EventSummaryResponseDTO;
import com.uff.eventsync.application.event.mapper.EventMapper;
import com.uff.eventsync.application.event.service.EventService;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @Valid @RequestBody EventCreateRequestDTO eventData,
            Authentication authentication) {

        User organizer = (User) authentication.getPrincipal();

        EventResponseDTO createdEvent = eventService.createEvent(eventData, organizer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdEvent.id())
                .toUri();

        return ResponseEntity.created(location).body(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailResponseDTO> getEventById(@PathVariable UUID id) {
        EventDetailResponseDTO event = eventService.findEventById(id);

        event.add(linkTo(methodOn(EventController.class).getEventById(id)).withSelfRel());

        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EventSummaryResponseDTO>> getAllEvents() {
        List<Event> events = eventService.findAllEventsSorted();
        List<EventSummaryResponseDTO> eventDTOs = events.stream().map(event -> {
            EventSummaryResponseDTO dto = EventMapper.toSummaryResponseDTO(event);
            dto.add(linkTo(methodOn(EventController.class).getEventById(event.getId())).withSelfRel());
            return dto;
        }).collect(Collectors.toList());
        var selfLink = linkTo(methodOn(EventController.class).getAllEvents()).withSelfRel();
        CollectionModel<EventSummaryResponseDTO> collectionModel = CollectionModel.of(eventDTOs, selfLink);
        return ResponseEntity.ok(collectionModel);
    }

}
