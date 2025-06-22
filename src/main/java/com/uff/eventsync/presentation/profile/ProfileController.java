package com.uff.eventsync.presentation.profile;

import com.uff.eventsync.application.event.dto.EventSummaryResponseDTO;
import com.uff.eventsync.application.event.mapper.EventMapper;
import com.uff.eventsync.application.registration.service.RegistrationService;
import com.uff.eventsync.domain.event.entity.Event;
import com.uff.eventsync.domain.user.entity.User;
import com.uff.eventsync.presentation.event.EventController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/me")
public class ProfileController {

    private final RegistrationService registrationService;

    public ProfileController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/events")
    public ResponseEntity<CollectionModel<EventSummaryResponseDTO>> getMyAttendedEvents(
            @AuthenticationPrincipal User currentUser) {
        List<Event> attendedEvents = registrationService.findAttendedEventsForUser(currentUser);
        List<EventSummaryResponseDTO> eventDTOs = attendedEvents.stream().map(event -> {
            EventSummaryResponseDTO dto = EventMapper.toSummaryResponseDTO(event);
            dto.add(linkTo(methodOn(EventController.class).getEventById(event.getId(), null)).withSelfRel());
            return dto;
        }).collect(Collectors.toList());
        var selfLink = linkTo(methodOn(ProfileController.class).getMyAttendedEvents(currentUser)).withSelfRel();
        CollectionModel<EventSummaryResponseDTO> collectionModel = CollectionModel.of(eventDTOs, selfLink);
        return ResponseEntity.ok(collectionModel);
    }
}
