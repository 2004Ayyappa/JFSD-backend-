package klu.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import klu.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
	 List<Event> findByEventNameContainingIgnoreCase(String eventName);
	  Optional<Event> findByEventName(String eventName);
	 
}
