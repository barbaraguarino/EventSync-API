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
    public ResponseEntity<EventDetailResponseDTO> createEvent(
            @Valid @RequestBody EventCreateRequestDTO eventData,
            Authentication authentication) {

        User organizer = (User) authentication.getPrincipal();
        Event createdEvent = eventService.createEvent(eventData, organizer);
        EventDetailResponseDTO responseDTO = EventMapper.toDetailResponseDTO(createdEvent);
        responseDTO.add(linkTo(methodOn(EventController.class).getEventById(responseDTO.getId())).withSelfRel());
        responseDTO.add(linkTo(methodOn(EventController.class).deleteEvent(responseDTO.getId(), null)).withRel("delete"));
        responseDTO.add(linkTo(methodOn(EventController.class).getAllEvents()).withRel("all-events"));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailResponseDTO> getEventById(@PathVariable UUID id) {
        EventDetailResponseDTO event = eventService.findEventById(id);
        event.add(linkTo(methodOn(EventController.class).getEventById(id)).withSelfRel());
        event.add(linkTo(methodOn(EventController.class).deleteEvent(id, null)).withRel("delete"));
        event.add(linkTo(methodOn(EventController.class).getAllEvents()).withRel("all-events"));
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
        collectionModel.add(linkTo(methodOn(EventController.class).createEvent(null, null)).withRel("create-event"));
        return ResponseEntity.ok(collectionModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        eventService.deleteEvent(id, currentUser);
        return ResponseEntity.noContent().build();
    }

}
