package klu.controller;

import klu.model.Event;

import klu.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestParam("eventName") String eventName,
                                         @RequestParam("description") String description,
                                         @RequestParam("eventDate") String eventDate,
                                         @RequestParam("eventTime") String eventTime,
                                         @RequestParam("image") MultipartFile image) {
        try {
            Event event = new Event();
            event.setEventName(eventName);
            event.setDescription(description);
            event.setEventDate(eventDate);
            event.setEventTime(eventTime);
            event.setImage(image.getBytes());
            return ResponseEntity.ok(eventService.createEvent(event));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to save image: " + e.getMessage());
        }
    }
     // Similar update for PUT mapping
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(
        @PathVariable Long id,
        @RequestParam("eventName") String eventName,
        @RequestParam("description") String description,
        @RequestParam("eventDate") String eventDate,
        @RequestParam("eventTime") String eventTime,
        @RequestParam(value = "image", required = false) MultipartFile image
    ) {

        try {
            Event existingEvent = eventService.getEventById(id);

            if (existingEvent == null) {
                return ResponseEntity.notFound().build();
            }

            existingEvent.setEventName(eventName);
            existingEvent.setDescription(description);
            existingEvent.setEventDate(eventDate);
            existingEvent.setEventTime(eventTime);

            if (image != null) {
                existingEvent.setImage(image.getBytes());
            }

            eventService.updateEvent(id, existingEvent);

            return ResponseEntity.ok("Event updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating event: " + e.getMessage());
        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
            try {
                eventService.deleteEvent(id);
                return ResponseEntity.ok("Event deleted successfully");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error deleting event: " + e.getMessage());
            }
        }

}
        
