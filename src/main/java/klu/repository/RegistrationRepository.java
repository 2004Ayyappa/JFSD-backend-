package klu.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import klu.model.Registration;


@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
	  boolean existsByStudentUsernameAndEventId(String studentUsername, Long eventId);
	  List<Registration> findByStudentUsername(String username);
	    Optional<Registration> findByStudentUsernameAndEventId(String username, Long eventId);
	    
	 // Count registrations for a specific event with additional filtering options
	    @Query("SELECT COUNT(r) FROM Registration r WHERE r.eventId = :eventId")
	    long countByEventId(Long eventId);
	  

}