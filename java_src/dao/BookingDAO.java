package dao;

import model.Booking;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Booking DAO Class
 * Handles all database operations for Booking entity
 */
public class BookingDAO {
    
    private Connection connection;
    
    public BookingDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Create a new booking
     * @param booking Booking object to create
     * @return true if creation successful, false otherwise
     */
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, user_name, event_id, event_name, " +
                     "tickets_booked, total_amount, status, payment_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, booking.getUserId());
            pst.setString(2, booking.getUserName());
            pst.setInt(3, booking.getEventId());
            pst.setString(4, booking.getEventName());
            pst.setInt(5, booking.getTicketsBooked());
            pst.setDouble(6, booking.getTotalAmount());
            pst.setString(7, booking.getStatus());
            pst.setString(8, booking.getPaymentStatus());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get booking by ID
     * @param bookingId Booking ID
     * @return Booking object if found, null otherwise
     */
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, bookingId);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToBooking(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving booking: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Get all bookings for a user
     * @param userId User ID
     * @return List of user's bookings
     */
    public List<Booking> getUserBookings(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ? ORDER BY booking_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, userId);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving user bookings: " + e.getMessage());
        }
        return bookings;
    }
    
    /**
     * Get all bookings for an event
     * @param eventId Event ID
     * @return List of event bookings
     */
    public List<Booking> getEventBookings(int eventId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE event_id = ? ORDER BY booking_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, eventId);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving event bookings: " + e.getMessage());
        }
        return bookings;
    }
    
    /**
     * Get all bookings
     * @return List of all bookings
     */
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings ORDER BY booking_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                bookings.add(mapResultSetToBooking(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving bookings: " + e.getMessage());
        }
        return bookings;
    }
    
    /**
     * Update booking
     * @param booking Booking object with updated data
     * @return true if update successful, false otherwise
     */
    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET status = ?, payment_status = ? WHERE booking_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, booking.getStatus());
            pst.setString(2, booking.getPaymentStatus());
            pst.setInt(3, booking.getBookingId());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating booking: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Cancel booking
     * @param bookingId Booking ID to cancel
     * @return true if cancellation successful, false otherwise
     */
    public boolean cancelBooking(int bookingId) {
        String sql = "UPDATE bookings SET status = 'cancelled', payment_status = 'refunded' WHERE booking_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, bookingId);
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error cancelling booking: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get total bookings count
     * @return Total number of bookings
     */
    public int getTotalBookingsCount() {
        String sql = "SELECT COUNT(*) FROM bookings";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting bookings count: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Get total revenue from all bookings
     * @return Total revenue
     */
    public double getTotalRevenue() {
        String sql = "SELECT SUM(total_amount) FROM bookings WHERE payment_status = 'paid'";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting total revenue: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Helper method to map ResultSet to Booking object
     */
    private Booking mapResultSetToBooking(ResultSet rs) throws SQLException {
        return new Booking(
            rs.getInt("booking_id"),
            rs.getInt("user_id"),
            rs.getString("user_name"),
            rs.getInt("event_id"),
            rs.getString("event_name"),
            rs.getInt("tickets_booked"),
            rs.getDouble("total_amount"),
            rs.getString("booking_date"),
            rs.getString("booking_time"),
            rs.getString("status"),
            rs.getString("payment_status")
        );
    }
}
