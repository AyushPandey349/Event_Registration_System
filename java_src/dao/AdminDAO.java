package dao;

import model.User;
import model.Event;
import model.Booking;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Admin DAO Class
 * Handles admin-specific database operations
 */
public class AdminDAO {
    
    private Connection connection;
    
    public AdminDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Get total count of users
     * @return Total users count
     */
    public int getTotalUsersCount() {
        String sql = "SELECT COUNT(*) FROM users WHERE is_active = true";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting users count: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Get total count of active events
     * @return Total events count
     */
    public int getTotalEventsCount() {
        String sql = "SELECT COUNT(*) FROM events WHERE status = 'active'";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting events count: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Get total count of bookings
     * @return Total bookings count
     */
    public int getTotalBookingsCount() {
        String sql = "SELECT COUNT(*) FROM bookings WHERE status = 'confirmed'";
        
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
     * Get total revenue
     * @return Total revenue from all payments
     */
    public double getTotalRevenue() {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM payments WHERE status = 'success'";
        
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
     * Get user statistics by role
     * @return Count of users by role
     */
    public String getUserStatisticsByRole() {
        String sql = "SELECT role, COUNT(*) as count FROM users WHERE is_active = true GROUP BY role";
        StringBuilder stats = new StringBuilder();
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                stats.append(rs.getString("role")).append(": ")
                     .append(rs.getInt("count")).append(", ");
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting user statistics: " + e.getMessage());
        }
        return stats.toString();
    }
    
    /**
     * Get top events by bookings
     * @param limit Number of top events to fetch
     * @return List of top events
     */
    public List<String> getTopEventsByBookings(int limit) {
        List<String> topEvents = new ArrayList<>();
        String sql = "SELECT e.event_name, COUNT(b.booking_id) as booking_count " +
                     "FROM events e LEFT JOIN bookings b ON e.event_id = b.event_id " +
                     "GROUP BY e.event_id, e.event_name " +
                     "ORDER BY booking_count DESC LIMIT ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, limit);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                topEvents.add(rs.getString("event_name") + " (" + rs.getInt("booking_count") + " bookings)");
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting top events: " + e.getMessage());
        }
        return topEvents;
    }
    
    /**
     * Generate activity report
     * @return Activity report as string
     */
    public String generateActivityReport() {
        StringBuilder report = new StringBuilder();
        
        report.append("===== ACTIVITY REPORT =====\n");
        report.append("Total Users: ").append(getTotalUsersCount()).append("\n");
        report.append("Total Events: ").append(getTotalEventsCount()).append("\n");
        report.append("Total Bookings: ").append(getTotalBookingsCount()).append("\n");
        report.append("Total Revenue: ₹").append(String.format("%.2f", getTotalRevenue())).append("\n");
        report.append("User Statistics: ").append(getUserStatisticsByRole()).append("\n");
        report.append("Top Events: ").append(getTopEventsByBookings(5)).append("\n");
        
        return report.toString();
    }
    
    /**
     * Check if user is admin
     * @param userId User ID
     * @return true if user is admin, false otherwise
     */
    public boolean isAdmin(int userId) {
        String sql = "SELECT role FROM users WHERE user_id = ? AND is_active = true";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, userId);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return "admin".equalsIgnoreCase(rs.getString("role"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking admin role: " + e.getMessage());
        }
        return false;
    }
}
