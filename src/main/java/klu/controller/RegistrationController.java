package klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import klu.controller.RegistrationRequest;
import klu.model.Event;
import klu.service.RegistrationService;

@CrossOrigin(origins = "https://frontend-git-main-swami-ayyappas-projects.vercel.app/")
@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> registerStudentForEvent(@RequestBody RegistrationRequest registrationRequest) {
        try {
            registrationService.registerStudentForEvent(
                registrationRequest.getStudentUsername(),
                registrationRequest.getEventId()
            );
            return ResponseEntity.ok("Successfully registered for the event!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<Event>> getRegisteredEvents(@PathVariable String username) {
        List<Event> registeredEvents = registrationService.getRegisteredEvents(username);
        return ResponseEntity.ok(registeredEvents);
    }

    // Cancel a specific registration
    @DeleteMapping("/{username}/{eventId}")
    public ResponseEntity<String> cancelRegistration(
            @PathVariable String username,
            @PathVariable Long eventId) {
        boolean isCancelled = registrationService.cancelRegistration(username, eventId);
        if (isCancelled) {
            return ResponseEntity.ok("Registration canceled successfully.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to cancel registration.");
    }
}
