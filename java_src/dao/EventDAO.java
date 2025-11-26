package dao;

import model.Event;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Event DAO Class
 * Handles all database operations for Event entity
 */
public class EventDAO {
    
    private Connection connection;
    
    public EventDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Insert a new event
     * @param event Event object to insert
     * @return true if insertion successful, false otherwise
     */
    public boolean createEvent(Event event) {
        String sql = "INSERT INTO events (event_name, description, event_date, event_time, location, category, " +
                     "total_tickets, tickets_available, ticket_price, organizer_id, organizer_name, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, event.getEventName());
            pst.setString(2, event.getDescription());
            pst.setString(3, event.getEventDate());
            pst.setString(4, event.getEventTime());
            pst.setString(5, event.getLocation());
            pst.setString(6, event.getCategory());
            pst.setInt(7, event.getTotalTickets());
            pst.setInt(8, event.getTicketsAvailable());
            pst.setDouble(9, event.getTicketPrice());
            pst.setInt(10, event.getOrganizerId());
            pst.setString(11, event.getOrganizerName());
            pst.setString(12, event.getStatus());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating event: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get event by ID
     * @param eventId Event ID
     * @return Event object if found, null otherwise
     */
    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM events WHERE event_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToEvent(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving event: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Get all active events
     * @return List of all active events
     */
    public List<Event> getAllActiveEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status = 'active' ORDER BY event_date ASC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        return events;
    }
    
    /**
     * Get all events
     * @return List of all events
     */
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events ORDER BY event_date ASC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving events: " + e.getMessage());
        }
        return events;
    }
    
    /**
     * Search events by name or category
     * @param keyword Search keyword
     * @return List of matching events
     */
    public List<Event> searchEvents(String keyword) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status = 'active' AND " +
                     "(event_name LIKE ? OR category LIKE ? OR location LIKE ?) " +
                     "ORDER BY event_date ASC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            String searchTerm = "%" + keyword + "%";
            pst.setString(1, searchTerm);
            pst.setString(2, searchTerm);
            pst.setString(3, searchTerm);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching events: " + e.getMessage());
        }
        return events;
    }
    
    /**
     * Get events by category
     * @param category Event category
     * @return List of events in category
     */
    public List<Event> getEventsByCategory(String category) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events WHERE status = 'active' AND category = ? ORDER BY event_date ASC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, category);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                events.add(mapResultSetToEvent(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving events by category: " + e.getMessage());
        }
        return events;
    }
    
    /**
     * Update event
     * @param event Event object with updated data
     * @return true if update successful, false otherwise
     */
    public boolean updateEvent(Event event) {
        String sql = "UPDATE events SET event_name = ?, description = ?, event_date = ?, event_time = ?, " +
                     "location = ?, category = ?, total_tickets = ?, tickets_available = ?, " +
                     "ticket_price = ?, status = ? WHERE event_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, event.getEventName());
            pst.setString(2, event.getDescription());
            pst.setString(3, event.getEventDate());
            pst.setString(4, event.getEventTime());
            pst.setString(5, event.getLocation());
            pst.setString(6, event.getCategory());
            pst.setInt(7, event.getTotalTickets());
            pst.setInt(8, event.getTicketsAvailable());
            pst.setDouble(9, event.getTicketPrice());
            pst.setString(10, event.getStatus());
            pst.setInt(11, event.getEventId());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating event: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete event (soft delete - set status to cancelled)
     * @param eventId Event ID to delete
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteEvent(int eventId) {
        String sql = "UPDATE events SET status = 'cancelled' WHERE event_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Update available tickets for an event
     * @param eventId Event ID
     * @param ticketsToReduce Number of tickets to reduce
     * @return true if update successful, false otherwise
     */
    public boolean updateTicketAvailability(int eventId, int ticketsToReduce) {
        String sql = "UPDATE events SET tickets_available = tickets_available - ? WHERE event_id = ? AND tickets_available >= ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, ticketsToReduce);
            pst.setInt(2, eventId);
            pst.setInt(3, ticketsToReduce);
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating ticket availability: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Helper method to map ResultSet to Event object
     */
    private Event mapResultSetToEvent(ResultSet rs) throws SQLException {
        return new Event(
            rs.getInt("event_id"),
            rs.getString("event_name"),
            rs.getString("description"),
            rs.getString("event_date"),
            rs.getString("event_time"),
            rs.getString("location"),
            rs.getString("category"),
            rs.getInt("total_tickets"),
            rs.getInt("tickets_available"),
            rs.getDouble("ticket_price"),
            rs.getInt("organizer_id"),
            rs.getString("organizer_name"),
            rs.getString("status"),
            rs.getString("created_at")
        );
    }
}
