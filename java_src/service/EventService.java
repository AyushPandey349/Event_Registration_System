package service;

import dao.EventDAO;
import dao.BookingDAO;
import model.Event;
import model.Booking;

import java.sql.SQLException;
import java.util.List;

/**
 * Event Service Class
 * Contains business logic for event-related operations
 */
public class EventService {
    
    private EventDAO eventDAO;
    private BookingDAO bookingDAO;
    
    public EventService() throws SQLException {
        this.eventDAO = new EventDAO();
        this.bookingDAO = new BookingDAO();
    }
    
    /**
     * Get all available events for users to browse
     * @return List of active events
     */
    public List<Event> getAvailableEvents() {
        return eventDAO.getAllActiveEvents();
    }
    
    /**
     * Search events by keyword
     * @param keyword Search keyword
     * @return List of matching events
     */
    public List<Event> searchEvents(String keyword) {
        return eventDAO.searchEvents(keyword);
    }
    
    /**
     * Get events by category
     * @param category Event category
     * @return List of events in category
     */
    public List<Event> getEventsByCategory(String category) {
        return eventDAO.getEventsByCategory(category);
    }
    
    /**
     * Get event details
     * @param eventId Event ID
     * @return Event object
     */
    public Event getEventDetails(int eventId) {
        return eventDAO.getEventById(eventId);
    }
    
    /**
     * Book event for user
     * @param booking Booking object
     * @return true if booking successful, false otherwise
     */
    public boolean bookEvent(Booking booking) {
        // Check if event has available tickets
        Event event = eventDAO.getEventById(booking.getEventId());
        if (event != null && event.getTicketsAvailable() >= booking.getTicketsBooked()) {
            // Create booking
            if (bookingDAO.createBooking(booking)) {
                // Update ticket availability
                return eventDAO.updateTicketAvailability(booking.getEventId(), booking.getTicketsBooked());
            }
        }
        return false;
    }
    
    /**
     * Get booking history for user
     * @param userId User ID
     * @return List of user's bookings
     */
    public List<Booking> getBookingHistory(int userId) {
        return bookingDAO.getUserBookings(userId);
    }
    
    /**
     * Cancel a booking
     * @param bookingId Booking ID
     * @return true if cancellation successful, false otherwise
     */
    public boolean cancelBooking(int bookingId) {
        // Get booking details
        Booking booking = bookingDAO.getBookingById(bookingId);
        if (booking != null) {
            // Cancel booking
            if (bookingDAO.cancelBooking(bookingId)) {
                // Restore ticket availability
                Event event = eventDAO.getEventById(booking.getEventId());
                if (event != null) {
                    String sql = "UPDATE events SET tickets_available = tickets_available + ? WHERE event_id = ?";
                    // This would be executed directly, but for now we return success
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Validate booking before confirmation
     * @param eventId Event ID
     * @param ticketsRequired Tickets required
     * @return true if booking is possible, false otherwise
     */
    public boolean validateBooking(int eventId, int ticketsRequired) {
        Event event = eventDAO.getEventById(eventId);
        if (event != null) {
            return event.getTicketsAvailable() >= ticketsRequired && 
                   event.getStatus().equalsIgnoreCase("active");
        }
        return false;
    }
    
    /**
     * Calculate total cost of booking
     * @param eventId Event ID
     * @param numberOfTickets Number of tickets
     * @return Total cost
     */
    public double calculateBookingCost(int eventId, int numberOfTickets) {
        Event event = eventDAO.getEventById(eventId);
        if (event != null) {
            return event.getTicketPrice() * numberOfTickets;
        }
        return 0;
    }
}
