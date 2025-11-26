# Event Registration System - Java Project

A comprehensive Java-based Event Management System with JDBC-MySQL integration, demonstrating Core Java, OOP concepts, and database connectivity.

## 📋 Project Overview

This project implements a complete event management platform where users can:
- Register and login
- Browse and search events
- Book event tickets
- View booking history
- Manage payments

Event organizers can create and manage events, while administrators have full control over the system.

## 🏗️ Project Architecture

### Packages and Classes

```
java_src/
├── model/
│   ├── User.java           # User entity with encapsulation
│   ├── Event.java          # Event entity with business logic
│   ├── Booking.java        # Booking entity
│   └── Payment.java        # Payment transaction entity
├── dao/
│   ├── UserDAO.java        # User database operations (CRUD)
│   ├── EventDAO.java       # Event database operations
│   ├── BookingDAO.java     # Booking database operations
│   ├── PaymentDAO.java     # Payment database operations
│   └── AdminDAO.java       # Admin-specific operations
├── utils/
│   └── DBConnection.java   # JDBC connection management
├── service/
│   └── EventService.java   # Business logic layer
├── gui/
│   └── MainFrame.java      # Swing-based GUI
└── Main.java              # Application entry point
```

## 🎯 Core Java & OOP Concepts

- **Classes & Objects**: User, Event, Booking, Payment models
- **Encapsulation**: Private fields with public getters/setters
- **Inheritance**: User → Admin, User → Organizer (extensible)
- **Polymorphism**: DAO polymorphism for different entities
- **Abstraction**: DAO layer abstracts database complexity
- **Interfaces**: Can be extended with service interfaces
- **Exception Handling**: Try-catch blocks for SQL exceptions
- **Collections**: ArrayList for dynamic data storage

## 🔌 JDBC Database Connectivity

- **Driver**: MySQL JDBC Driver (com.mysql.cj.jdbc.Driver)
- **Connection Management**: Singleton pattern in DBConnection.java
- **Prepared Statements**: Prevents SQL injection
- **ResultSet Mapping**: Converts database records to objects
- **Connection Pooling**: Reusable connection instance

## 🗄️ Database Schema

### Tables Structure

1. **users** - User accounts with roles (user, organizer, admin)
2. **events** - Event information with ticket management
3. **bookings** - User event bookings with status tracking
4. **payments** - Payment transactions and history
5. **admin** - Admin-specific metadata

## 🛠️ Prerequisites

- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL JDBC Driver (mysql-connector-java-8.0.33.jar)
- Any IDE (Eclipse, IntelliJ, NetBeans) or terminal

## 📥 Installation & Setup

### Step 1: Install Java and MySQL

```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-11-jdk
sudo apt install mysql-server

# Or use your system's package manager
```

### Step 2: Setup MySQL Database

```bash
# Start MySQL service
sudo service mysql start

# Login to MySQL
mysql -u root -p

# Create database and tables (run database_schema.sql)
```

```sql
-- Inside MySQL console
source /path/to/database_schema.sql;
```

### Step 3: Download MySQL JDBC Driver

```bash
# Download and place in java_src/ directory
# wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.33.jar
```

### Step 4: Configure Database Connection

Edit `java_src/utils/DBConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/event_management";
private static final String DB_USER = "root";
private static final String DB_PASSWORD = "your_password";
```

## 🔨 Compilation & Execution

### Navigate to Java Source Directory

```bash
cd java_src
```

### Compile All Java Files

```bash
# Single command compilation
javac -cp .:../mysql-connector-java-8.0.33.jar model/*.java dao/*.java utils/*.java service/*.java gui/*.java Main.java

# Or compile individually
javac model/*.java
javac utils/DBConnection.java
javac dao/*.java
javac service/*.java
javac gui/*.java
javac Main.java
```

### Run the Application

```bash
# Run main application
java -cp .:../mysql-connector-java-8.0.33.jar Main

# The GUI window will open with login screen
```

## 📚 Usage Guide

### Login Screen
1. **Username**: admin_user
2. **Password**: admin123
3. Click "Login" to enter dashboard

### Features
- **Browse Events**: View all available events
- **Book Event**: Select event and number of tickets
- **View Bookings**: Check your booking history
- **Search Events**: Filter by name, category, or location
- **Admin Panel**: Manage users, events, and generate reports

## 🧪 Testing the System

### Sample Test Cases

```java
// Test User Registration
UserDAO userDAO = new UserDAO();
User newUser = new User("testuser", "test@gmail.com", "pass123", 
                        "Test User", "9999999999", "user");
userDAO.registerUser(newUser);

// Test Event Booking
EventService eventService = new EventService();
List<Event> events = eventService.getAvailableEvents();
Booking booking = new Booking(1, "Test User", 1, "Tech Conference", 2, 1999.98);
eventService.bookEvent(booking);

// Test Admin Reports
AdminDAO adminDAO = new AdminDAO();
String report = adminDAO.generateActivityReport();
System.out.println(report);
```

## 🔮 Key Features Implemented

✅ User Authentication (Login/Register)
✅ Event Creation & Management
✅ Event Browsing & Searching
✅ Event Booking System
✅ Payment Processing
✅ Booking History
✅ Admin Dashboard
✅ Activity Reports
✅ Ticket Management
✅ Database CRUD Operations

## 📈 Future Enhancements

- Email notifications using JavaMail
- SMS notifications via SMS API
- PDF ticket generation using iText
- QR code generation for tickets
- Advanced search with filters
- User reviews and ratings
- Event recommendations
- RESTful API using Spring Boot
- Mobile application

## 📁 File Descriptions

| File | Purpose |
|------|---------|
| `User.java` | User model with encapsulation |
| `DBConnection.java` | MySQL connection management |
| `UserDAO.java` | User CRUD operations |
| `EventDAO.java` | Event CRUD operations |
| `BookingDAO.java` | Booking CRUD operations |
| `EventService.java` | Business logic layer |
| `MainFrame.java` | Swing GUI implementation |
| `Main.java` | Application entry point |
| `database_schema.sql` | MySQL schema and sample data |

## 🔗 Database Relationships

```
users (1) ──── (many) events (organizer)
users (1) ──── (many) bookings
events (1) ──── (many) bookings
bookings (1) ──── (many) payments
```

## 🛡️ Security Features

- Password storage (consider hashing in production)
- Prepared statements to prevent SQL injection
- Role-based access control (user, organizer, admin)
- Soft delete for data retention
- Input validation

## 💾 Database Connection String

```
jdbc:mysql://localhost:3306/event_management?useSSL=false&serverTimezone=UTC
```

## ⚙️ Configuration

**Database Credentials** (in `DBConnection.java`):
- **URL**: jdbc:mysql://localhost:3306/event_management
- **User**: root
- **Password**: root
- **Driver**: com.mysql.cj.jdbc.Driver

## 🚀 Quick Start

```bash
# 1. Navigate to project directory
cd /path/to/Event_Registration_System

# 2. Setup database
mysql -u root -p < database_schema.sql

# 3. Compile
cd java_src
javac -cp .:../mysql-connector-java-8.0.33.jar **/*.java

# 4. Run
java -cp .:../mysql-connector-java-8.0.33.jar Main
```

## 📝 Notes

- Ensure MySQL service is running before starting the application
- Update database credentials if different from defaults
- JDBC driver must be in classpath during compilation and execution
- GUI uses Java Swing - no additional dependencies needed beyond JDBC

## 📞 Support

For issues or questions:
1. Check database connectivity
2. Verify MySQL is running
3. Ensure all files are compiled
4. Check classpath includes JDBC driver

## 📄 License

This project is for educational purposes.

---

**Happy Coding! 🚀**
