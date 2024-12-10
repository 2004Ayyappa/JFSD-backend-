package klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
@Table(name="events")
@Entity

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String eventName;
    private String description;
    private String eventDate;
    private String eventTime;

    @Lob // This annotation indicates that the field should be treated as a large object
    private byte[] image;
    // Default constructor (required by Hibernate)
    public Event() {
    }
    // Constructor for initializing specific fields
    public Event(Long id) {
        this.id = id;
    }

    public Event(String eventName, String description, String eventDate, String eventTime, byte[] image) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.image = image;
    }

   
	// Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
