package klu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.model.Event;
import klu.model.Registration;
import klu.model.Student;
import klu.repository.EventRepository;
import klu.repository.RegistrationRepository;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    public void registerStudentForEvent(String studentUsername, Long eventId) {
        if (registrationRepository.existsByStudentUsernameAndEventId(studentUsername, eventId)) {
            throw new RuntimeException("Student already registered for this event!");
        }

        Registration registration = new Registration();
        registration.setStudentUsername(studentUsername);
        registration.setEventId(eventId);
        registration.setRegistrationDate(LocalDateTime.now());
        registrationRepository.save(registration);
    }
    public List<Event> getRegisteredEvents(String username) {
        List<Registration> registrations = registrationRepository.findByStudentUsername(username);
        return registrations.stream()
                .map(registration -> {
                    Optional<Event> event = eventRepository.findById(registration.getEventId());
                    return event.orElse(null);
                })
                .filter(Objects::nonNull) // Filter out null events
                .collect(Collectors.toList());
    }

    // Cancel a registration
    public boolean cancelRegistration(String username, Long eventId) {
        Optional<Registration> registration = registrationRepository.findByStudentUsernameAndEventId(username, eventId);
        if (registration.isPresent()) {
            registrationRepository.delete(registration.get());
            return true;
        }
        return false;
    }
}