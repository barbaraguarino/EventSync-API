package com.uff.eventsync.presentation.registration;

import com.uff.eventsync.application.registration.service.RegistrationService;
import com.uff.eventsync.domain.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events/{eventId}/checkin")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Void> checkIn(@PathVariable UUID eventId, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        registrationService.checkInToEvent(eventId, currentUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCheckIn(@PathVariable UUID eventId, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        registrationService.deleteCheckInToEvent(eventId, currentUser);
        return ResponseEntity.noContent().build();
    }
}
