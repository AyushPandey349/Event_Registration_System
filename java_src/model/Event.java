package model;

/**
 * Event Model Class
 * Represents an event in the system
 */
public class Event {
    
    private int eventId;
    private String eventName;
    private String description;
    private String eventDate;
    private String eventTime;
    private String location;
    private String category;
    private int totalTickets;
    private int ticketsAvailable;
    private double ticketPrice;
    private int organizerId;
    private String organizerName;
    private String status; // "active", "cancelled", "completed"
    private String createdAt;
    
    // Constructor with all fields
    public Event(int eventId, String eventName, String description, String eventDate,
                 String eventTime, String location, String category, int totalTickets,
                 int ticketsAvailable, double ticketPrice, int organizerId, 
                 String organizerName, String status, String createdAt) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.category = category;
        this.totalTickets = totalTickets;
        this.ticketsAvailable = ticketsAvailable;
        this.ticketPrice = ticketPrice;
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.status = status;
        this.createdAt = createdAt;
    }
    
    // Constructor for creation (without eventId and createdAt)
    public Event(String eventName, String description, String eventDate,
                 String eventTime, String location, String category, int totalTickets,
                 double ticketPrice, int organizerId, String organizerName) {
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.category = category;
        this.totalTickets = totalTickets;
        this.ticketsAvailable = totalTickets;
        this.ticketPrice = ticketPrice;
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.status = "active";
    }
    
    // Getters and Setters
    
    public int getEventId() {
        return eventId;
    }
    
    public void setEventId(int eventId) {
        this.eventId = eventId;
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getTotalTickets() {
        return totalTickets;
    }
    
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
    
    public int getTicketsAvailable() {
        return ticketsAvailable;
    }
    
    public void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }
    
    public double getTicketPrice() {
        return ticketPrice;
    }
    
    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public int getOrganizerId() {
        return organizerId;
    }
    
    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }
    
    public String getOrganizerName() {
        return organizerName;
    }
    
    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", ticketsAvailable=" + ticketsAvailable +
                ", ticketPrice=" + ticketPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
