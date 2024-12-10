package klu.service;

import klu.model.Event;
import klu.repository.EventRepository;
import klu.repository.RegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {


    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    public Event getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);  // Return the event if found, otherwise return null
    }
    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            // Update the fields of the existing event
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setEventDate(updatedEvent.getEventDate());
            existingEvent.setEventTime(updatedEvent.getEventTime());
            existingEvent.setImage(updatedEvent.getImage());

            // Save the updated event to the repository
            return eventRepository.save(existingEvent);
        } else {
            return null; // Return null if the event doesn't exist
        }
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    
   
}
