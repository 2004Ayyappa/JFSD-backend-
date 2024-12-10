package klu.controller;

public class RegistrationRequest {
	 private String studentUsername;
	    private Long eventId;

	    // Getters and setters
	    public String getStudentUsername() {
	        return studentUsername;
	    }

	    public void setStudentUsername(String studentUsername) {
	        this.studentUsername = studentUsername;
	    }

	    public Long getEventId() {
	        return eventId;
	    }

	    public void setEventId(Long eventId) {
	        this.eventId = eventId;
	    }
}
