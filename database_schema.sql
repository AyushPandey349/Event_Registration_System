-- Event Management System Database Schema
-- MySQL Database Setup

-- Create Database
CREATE DATABASE IF NOT EXISTS event_management;
USE event_management;

-- 1. Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    role ENUM('user', 'organizer', 'admin') DEFAULT 'user',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role)
);

-- 2. Events Table
CREATE TABLE IF NOT EXISTS events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    event_name VARCHAR(150) NOT NULL,
    description TEXT,
    event_date DATE NOT NULL,
    event_time TIME NOT NULL,
    location VARCHAR(200) NOT NULL,
    category VARCHAR(50) NOT NULL,
    total_tickets INT NOT NULL,
    tickets_available INT NOT NULL,
    ticket_price DECIMAL(10, 2) NOT NULL,
    organizer_id INT NOT NULL,
    organizer_name VARCHAR(100) NOT NULL,
    status ENUM('active', 'cancelled', 'completed') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (organizer_id) REFERENCES users(user_id),
    INDEX idx_status (status),
    INDEX idx_category (category),
    INDEX idx_event_date (event_date)
);

-- 3. Bookings Table
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    event_id INT NOT NULL,
    event_name VARCHAR(150) NOT NULL,
    tickets_booked INT NOT NULL,
    total_amount DECIMAL(12, 2) NOT NULL,
    booking_date DATE,
    booking_time TIME,
    status ENUM('confirmed', 'cancelled', 'pending') DEFAULT 'confirmed',
    payment_status ENUM('paid', 'unpaid', 'refunded') DEFAULT 'unpaid',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    INDEX idx_user_id (user_id),
    INDEX idx_event_id (event_id),
    INDEX idx_status (status),
    INDEX idx_booking_date (booking_date)
);

-- 4. Payments Table
CREATE TABLE IF NOT EXISTS payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    user_id INT NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    transaction_id VARCHAR(100) UNIQUE,
    payment_date DATE,
    payment_time TIME,
    status ENUM('success', 'failed', 'pending', 'refunded') DEFAULT 'pending',
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    INDEX idx_user_id (user_id),
    INDEX idx_booking_id (booking_id),
    INDEX idx_status (status),
    INDEX idx_payment_date (payment_date)
);

-- 5. Admin Table (for admin-specific information)
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    permissions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Sample Data (Optional - for testing)

-- Insert sample users
INSERT INTO users (username, email, password, full_name, phone, role) VALUES
('admin_user', 'admin@eventflow.com', 'admin123', 'Admin User', '9876543210', 'admin'),
('john_organizer', 'john@eventflow.com', 'john123', 'John Organizer', '9876543211', 'organizer'),
('alice_user', 'alice@gmail.com', 'alice123', 'Alice Johnson', '9876543212', 'user'),
('bob_user', 'bob@gmail.com', 'bob123', 'Bob Smith', '9876543213', 'user');

-- Insert sample events
INSERT INTO events (event_name, description, event_date, event_time, location, category, total_tickets, tickets_available, ticket_price, organizer_id, organizer_name, status) VALUES
('Tech Conference 2024', 'A comprehensive tech conference covering latest trends', '2024-12-15', '09:00:00', 'Convention Center, Delhi', 'Technology', 500, 450, 999.99, 2, 'John Organizer', 'active'),
('Music Festival', 'Annual music festival with famous artists', '2024-12-20', '18:00:00', 'Open Ground, Mumbai', 'Music', 5000, 4200, 500.00, 2, 'John Organizer', 'active'),
('Art Exhibition', 'Contemporary art exhibition', '2024-12-10', '10:00:00', 'Art Gallery, Bangalore', 'Art', 300, 300, 250.00, 2, 'John Organizer', 'active'),
('Business Expo', 'International business networking event', '2024-12-25', '09:00:00', 'Exhibition Hall, Pune', 'Business', 800, 700, 1500.00, 2, 'John Organizer', 'active');

-- Insert sample bookings
INSERT INTO bookings (user_id, user_name, event_id, event_name, tickets_booked, total_amount, booking_date, booking_time, status, payment_status) VALUES
(3, 'Alice Johnson', 1, 'Tech Conference 2024', 2, 1999.98, '2024-11-20', '10:30:00', 'confirmed', 'unpaid'),
(3, 'Alice Johnson', 2, 'Music Festival', 4, 2000.00, '2024-11-18', '14:15:00', 'confirmed', 'unpaid'),
(4, 'Bob Smith', 1, 'Tech Conference 2024', 1, 999.99, '2024-11-19', '11:45:00', 'confirmed', 'unpaid');

-- Insert sample payments
INSERT INTO payments (booking_id, user_id, amount, payment_method, transaction_id, payment_date, payment_time, status) VALUES
(1, 3, 1999.98, 'credit_card', 'TXN001', '2024-11-20', '10:35:00', 'success'),
(2, 3, 2000.00, 'debit_card', 'TXN002', '2024-11-18', '14:20:00', 'success'),
(3, 4, 999.99, 'net_banking', 'TXN003', '2024-11-19', '11:50:00', 'success');

-- Create indexes for better query performance
CREATE INDEX idx_users_created ON users(created_at);
CREATE INDEX idx_events_created ON events(created_at);
CREATE INDEX idx_bookings_created ON bookings(created_at);
CREATE INDEX idx_payments_created ON payments(created_at);

COMMIT;
