package model;

/**
 * Payment Model Class
 * Represents a payment transaction in the system
 */
public class Payment {
    
    private int paymentId;
    private int bookingId;
    private int userId;
    private double amount;
    private String paymentMethod; // "credit_card", "debit_card", "online_banking", "wallet"
    private String transactionId;
    private String paymentDate;
    private String paymentTime;
    private String status; // "success", "failed", "pending", "refunded"
    private String remarks;
    
    // Constructor with all fields
    public Payment(int paymentId, int bookingId, int userId, double amount,
                   String paymentMethod, String transactionId, String paymentDate,
                   String paymentTime, String status, String remarks) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.status = status;
        this.remarks = remarks;
    }
    
    // Constructor for creating new payment (without paymentId and dates)
    public Payment(int bookingId, int userId, double amount, String paymentMethod,
                   String transactionId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = "pending";
    }
    
    // Getters and Setters
    
    public int getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    
    public String getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentTime() {
        return paymentTime;
    }
    
    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", bookingId=" + bookingId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
