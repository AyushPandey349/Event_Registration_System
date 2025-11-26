package dao;

import model.Payment;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Payment DAO Class
 * Handles all database operations for Payment entity
 */
public class PaymentDAO {
    
    private Connection connection;
    
    public PaymentDAO() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    
    /**
     * Record a new payment
     * @param payment Payment object to record
     * @return true if recording successful, false otherwise
     */
    public boolean recordPayment(Payment payment) {
        String sql = "INSERT INTO payments (booking_id, user_id, amount, payment_method, transaction_id, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, payment.getBookingId());
            pst.setInt(2, payment.getUserId());
            pst.setDouble(3, payment.getAmount());
            pst.setString(4, payment.getPaymentMethod());
            pst.setString(5, payment.getTransactionId());
            pst.setString(6, payment.getStatus());
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error recording payment: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get payment by ID
     * @param paymentId Payment ID
     * @return Payment object if found, null otherwise
     */
    public Payment getPaymentById(int paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, paymentId);
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return mapResultSetToPayment(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving payment: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Get all payments for a user
     * @param userId User ID
     * @return List of user's payments
     */
    public List<Payment> getUserPayments(int userId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE user_id = ? ORDER BY payment_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, userId);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving user payments: " + e.getMessage());
        }
        return payments;
    }
    
    /**
     * Get all payments for a booking
     * @param bookingId Booking ID
     * @return List of booking payments
     */
    public List<Payment> getBookingPayments(int bookingId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE booking_id = ? ORDER BY payment_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, bookingId);
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving booking payments: " + e.getMessage());
        }
        return payments;
    }
    
    /**
     * Get all payments
     * @return List of all payments
     */
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments ORDER BY payment_date DESC";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                payments.add(mapResultSetToPayment(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving payments: " + e.getMessage());
        }
        return payments;
    }
    
    /**
     * Update payment status
     * @param paymentId Payment ID
     * @param status New status
     * @return true if update successful, false otherwise
     */
    public boolean updatePaymentStatus(int paymentId, String status) {
        String sql = "UPDATE payments SET status = ? WHERE payment_id = ?";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, status);
            pst.setInt(2, paymentId);
            
            int result = pst.executeUpdate();
            return result > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating payment status: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get total payments
     * @return Total payments count
     */
    public int getTotalPaymentsCount() {
        String sql = "SELECT COUNT(*) FROM payments";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting payments count: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Get total successful payments amount
     * @return Total amount from successful payments
     */
    public double getTotalPaymentsAmount() {
        String sql = "SELECT SUM(amount) FROM payments WHERE status = 'success'";
        
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting total payments amount: " + e.getMessage());
        }
        return 0;
    }
    
    /**
     * Helper method to map ResultSet to Payment object
     */
    private Payment mapResultSetToPayment(ResultSet rs) throws SQLException {
        return new Payment(
            rs.getInt("payment_id"),
            rs.getInt("booking_id"),
            rs.getInt("user_id"),
            rs.getDouble("amount"),
            rs.getString("payment_method"),
            rs.getString("transaction_id"),
            rs.getString("payment_date"),
            rs.getString("payment_time"),
            rs.getString("status"),
            rs.getString("remarks")
        );
    }
}
