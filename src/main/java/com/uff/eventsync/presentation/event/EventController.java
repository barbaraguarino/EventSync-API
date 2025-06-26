package com.uff.eventsync.presentation.event;

import com.uff.eventsync.application.event.dto.*;
import com.uff.eventsync.application.event.mapper.EventMapper;
import com.uff.eventsync.application.event.service.EventService;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;
import com.uff.eventsync.presentation.registration.RegistrationController;
import jakarta.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
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
        addLinksToEvent(responseDTO, organizer, createdEvent);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDetailResponseDTO> getEventById(
            @PathVariable UUID id,
            @AuthenticationPrincipal User currentUser) {
        Event eventEntity = eventService.findEventById(id);
        EventDetailResponseDTO responseDTO = EventMapper.toDetailResponseDTO(eventEntity);
        if (currentUser != null) {
            boolean isCheckedIn = eventEntity.getAttendees().contains(currentUser);
            responseDTO.setUserIsCheckedIn(isCheckedIn);
        }
        addLinksToEvent(responseDTO, currentUser, eventEntity);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EventSummaryResponseDTO>> getAllEvents() {
        List<Event> events = eventService.findAllEventsSorted();
        List<EventSummaryResponseDTO> eventDTOs = events.stream().map(event -> {
            EventSummaryResponseDTO dto = EventMapper.toSummaryResponseDTO(event);
            dto.add(linkTo(methodOn(EventController.class).getEventById(event.getId(), null)).withSelfRel());
            return dto;
        }).collect(Collectors.toList());
        var selfLink = linkTo(methodOn(EventController.class).getAllEvents()).withSelfRel();
        CollectionModel<EventSummaryResponseDTO> collectionModel = CollectionModel.of(eventDTOs, selfLink);
        collectionModel.add(linkTo(EventController.class).withRel("create-event"));
        return ResponseEntity.ok(collectionModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDetailResponseDTO> updateEvent(
            @PathVariable UUID id,
            @Valid @RequestBody EventUpdateRequestDTO eventData,
            Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Event updatedEvent = eventService.updateEvent(id, eventData, currentUser);
        EventDetailResponseDTO responseDTO = EventMapper.toDetailResponseDTO(updatedEvent);
        addLinksToEvent(responseDTO, currentUser, updatedEvent);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        eventService.deleteEvent(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    private void addLinksToEvent(EventDetailResponseDTO eventDTO, User currentUser, Event eventEntity) {
        UUID eventId = eventDTO.getId();
        eventDTO.add(linkTo(EventController.class).slash(eventId).withSelfRel());
        eventDTO.add(linkTo(EventController.class).withRel("all-events"));
        if (currentUser != null) {
            if (currentUser.getEmail().equals(eventDTO.getOrganizer().email())) {
                eventDTO.add(linkTo(EventController.class).slash(eventId).withRel("update"));
                eventDTO.add(linkTo(EventController.class).slash(eventId).withRel("delete"));
            } else {
                boolean isAlreadyCheckedIn = eventEntity.getAttendees().contains(currentUser);
                LocalDateTime eventStartDateTime = eventEntity.getDate().atTime(eventEntity.getStartTime());
                boolean hasOccurred = eventStartDateTime.isBefore(LocalDateTime.now());
                if (!isAlreadyCheckedIn && !hasOccurred) {
                    eventDTO.add(linkTo(RegistrationController.class, eventId).withRel("check-in"));
                }
            }
        }
    }
}
