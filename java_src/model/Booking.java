package model;

/**
 * Booking Model Class
 * Represents a booking made by a user for an event
 */
public class Booking {
    
    private int bookingId;
    private int userId;
    private String userName;
    private int eventId;
    private String eventName;
    private int ticketsBooked;
    private double totalAmount;
    private String bookingDate;
    private String bookingTime;
    private String status; // "confirmed", "cancelled", "pending"
    private String paymentStatus; // "paid", "unpaid", "refunded"
    
    // Constructor with all fields
    public Booking(int bookingId, int userId, String userName, int eventId, 
                   String eventName, int ticketsBooked, double totalAmount,
                   String bookingDate, String bookingTime, String status, String paymentStatus) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.userName = userName;
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketsBooked = ticketsBooked;
        this.totalAmount = totalAmount;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.status = status;
        this.paymentStatus = paymentStatus;
    }
    
    // Constructor for creating new booking (without bookingId and dates)
    public Booking(int userId, String userName, int eventId, String eventName,
                   int ticketsBooked, double totalAmount) {
        this.userId = userId;
        this.userName = userName;
        this.eventId = eventId;
        this.eventName = eventName;
        this.ticketsBooked = ticketsBooked;
        this.totalAmount = totalAmount;
        this.status = "confirmed";
        this.paymentStatus = "unpaid";
    }
    
    // Getters and Setters
    
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
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
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
    
    public int getTicketsBooked() {
        return ticketsBooked;
    }
    
    public void setTicketsBooked(int ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getBookingTime() {
        return bookingTime;
    }
    
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", eventName='" + eventName + '\'' +
                ", ticketsBooked=" + ticketsBooked +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }
}
