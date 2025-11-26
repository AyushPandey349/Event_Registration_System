# Java Event Registration System - File Structure & Compilation Guide

## 📂 Complete Project Directory Tree

```
Event_Registration_System/
│
├── 📁 java_src/                          # Java source code directory
│   │
│   ├── 📁 model/                         # Data Model Classes (OOP)
│   │   ├── User.java                     # User entity with encapsulation
│   │   ├── Event.java                    # Event entity
│   │   ├── Booking.java                  # Booking entity
│   │   └── Payment.java                  # Payment entity
│   │
│   ├── 📁 dao/                           # Data Access Object (CRUD)
│   │   ├── UserDAO.java                  # User database operations
│   │   ├── EventDAO.java                 # Event database operations
│   │   ├── BookingDAO.java               # Booking database operations
│   │   ├── PaymentDAO.java               # Payment database operations
│   │   └── AdminDAO.java                 # Admin-specific operations
│   │
│   ├── 📁 utils/                         # Utility Classes
│   │   └── DBConnection.java             # JDBC MySQL connection manager
│   │
│   ├── 📁 service/                       # Business Logic Layer
│   │   └── EventService.java             # Event business logic
│   │
│   ├── 📁 gui/                           # GUI Components (Swing)
│   │   └── MainFrame.java                # Main GUI window
│   │
│   └── Main.java                         # Application entry point
│
├── database_schema.sql                   # MySQL database creation script
├── JAVA_PROJECT_README.md                # Complete Java project documentation
├── SETUP_INSTRUCTIONS.md                 # Step-by-step setup guide
├── PROJECT_REBUILD_SUMMARY.md            # Project rebuild details
├── setup_and_compile.sh                  # Linux/Mac automation script
├── setup_and_compile.bat                 # Windows automation script
└── [Other Next.js files]                 # Original web app files
```

---

## 📊 Class Hierarchy & Relationships

```
┌─────────────────────────────────────────────────────────────┐
│                         Main.java                           │
│                    (Application Entry Point)                │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                    MainFrame.java                           │
│                   (Swing GUI Layer)                         │
└────────────────────────┬────────────────────────────────────┘
                         │
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                 EventService.java                           │
│            (Business Logic Layer)                           │
└────────────────────────┬────────────────────────────────────┘
                         │
              ┌──────────┴──────────┐
              │                     │
              ▼                     ▼
    ┌──────────────────┐  ┌──────────────────┐
    │ UserDAO.java     │  │ EventDAO.java    │
    │ BookingDAO.java  │  │ PaymentDAO.java  │
    │ AdminDAO.java    │  │                  │
    └────────┬─────────┘  └────────┬─────────┘
             │                     │
             └──────────┬──────────┘
                        │
                        ▼
    ┌──────────────────────────────────────────┐
    │      DBConnection.java (JDBC)            │
    │     (MySQL Connection Manager)           │
    └──────────────────┬───────────────────────┘
                       │
                       ▼
    ┌──────────────────────────────────────────┐
    │         MySQL Database                   │
    │     event_management (5 tables)          │
    └──────────────────────────────────────────┘
```

---

## 🔄 Data Flow Architecture

```
User GUI Input
      │
      ▼
   MainFrame (GUI Layer)
      │
      ▼
   EventService (Business Logic)
      │
      ├─► EventDAO
      ├─► UserDAO
      ├─► BookingDAO
      ├─► PaymentDAO
      └─► AdminDAO
           │
           ▼
      DBConnection (JDBC)
           │
           ▼
      MySQL Database
           │
           ▼
      ResultSet
           │
           ▼
      DAO Mapping (ResultSet → Objects)
           │
           ▼
      Service Layer Processing
           │
           ▼
      GUI Display Update
```

---

## 📈 Class Relationships & Dependencies

### Model Classes
```
User
├── Properties: userId, username, email, password, role, etc.
├── Methods: Getters, Setters, toString()
└── Used By: UserDAO, EventService

Event
├── Properties: eventId, name, date, time, tickets, price
├── Methods: Getters, Setters, toString()
└── Used By: EventDAO, EventService, Booking

Booking
├── Properties: bookingId, userId, eventId, tickets, amount
├── Methods: Getters, Setters, toString()
└── Used By: BookingDAO, EventService

Payment
├── Properties: paymentId, bookingId, amount, method, status
├── Methods: Getters, Setters, toString()
└── Used By: PaymentDAO, AdminDAO
```

### DAO Classes (Database Access)
```
UserDAO
├── Methods: registerUser(), loginUser(), getUserById()
├── Methods: getAllUsers(), updateUser(), deleteUser()
├── Methods: isUsernameExists(), isEmailExists()
└── Connection: DBConnection.getConnection()

EventDAO
├── Methods: createEvent(), getEventById(), getAllActiveEvents()
├── Methods: searchEvents(), getEventsByCategory()
├── Methods: updateEvent(), deleteEvent()
└── Methods: updateTicketAvailability()

BookingDAO
├── Methods: createBooking(), getBookingById()
├── Methods: getUserBookings(), getEventBookings()
├── Methods: updateBooking(), cancelBooking()

PaymentDAO
├── Methods: recordPayment(), getPaymentById()
├── Methods: getUserPayments(), getBookingPayments()
└── Methods: updatePaymentStatus()

AdminDAO
├── Methods: getTotalUsersCount(), getTotalEventsCount()
├── Methods: getTotalBookingsCount(), getTotalRevenue()
└── Methods: generateActivityReport(), isAdmin()
```

---

## 🔌 Database Schema Relationships

```
users (1)
   │
   ├──── (1 to many) ──► events (organizer_id)
   │
   ├──── (1 to many) ──► bookings (user_id)
   │
   └──── (1 to many) ──► payments (user_id)

events (1)
   │
   └──── (1 to many) ──► bookings (event_id)

bookings (1)
   │
   └──── (1 to many) ──► payments (booking_id)
```

---

## 📋 File Statistics

| Component | Files | Lines | Purpose |
|-----------|-------|-------|---------|
| Model | 4 | ~620 | Data entities with encapsulation |
| DAO | 5 | ~1100 | Database CRUD operations |
| Utils | 1 | ~80 | JDBC connection management |
| Service | 1 | ~120 | Business logic layer |
| GUI | 1 | ~250 | Swing-based user interface |
| Main | 1 | ~15 | Application entry point |
| **Total Java** | **13** | **~2185** | Complete Java application |
| SQL | 1 | ~150 | Database schema + sample data |

---

## 🛠️ Compilation Sequence

### Manual Compilation Order:

```bash
cd java_src

# Step 1: Compile Model Classes (no dependencies)
javac model/User.java
javac model/Event.java
javac model/Booking.java
javac model/Payment.java

# Step 2: Compile Utils (no Java dependencies)
javac utils/DBConnection.java

# Step 3: Compile DAO (depends on model + utils)
javac -cp . dao/UserDAO.java
javac -cp . dao/EventDAO.java
javac -cp . dao/BookingDAO.java
javac -cp . dao/PaymentDAO.java
javac -cp . dao/AdminDAO.java

# Step 4: Compile Service (depends on model + dao)
javac -cp . service/EventService.java

# Step 5: Compile GUI (depends on model + service)
javac -cp . gui/MainFrame.java

# Step 6: Compile Main (depends on all)
javac -cp . Main.java
```

### One-Command Compilation:

```bash
# Linux/Mac
javac -cp .:mysql-connector-java-8.0.33.jar model/*.java dao/*.java utils/*.java service/*.java gui/*.java Main.java

# Windows
javac -cp .;mysql-connector-java-8.0.33.jar model\*.java dao\*.java utils\*.java service\*.java gui\*.java Main.java
```

---

## 🚀 Execution Flow

```
Terminal
   │
   ▼
java -cp .:mysql-connector-java-8.0.33.jar Main
   │
   ▼
Main.main() invoked
   │
   ▼
SwingUtilities.invokeLater(MainFrame)
   │
   ▼
MainFrame initialized
   │
   ├─► DBConnection.getConnection()
   │   └─► MySQL Connection Established
   │
   ├─► CardLayout setup
   │
   ├─► Login Panel created
   │
   ├─► Dashboard Panel created
   │
   └─► GUI Window visible
        │
        ▼
   User interacts with GUI
        │
        ▼
   MainFrame event handlers
        │
        ▼
   Service layer calls
        │
        ▼
   DAO operations
        │
        ▼
   Database queries
        │
        ▼
   Results returned
        │
        ▼
   GUI updated
```

---

## 🎯 Key Design Patterns Used

### 1. **MVC Pattern**
- **Model**: User, Event, Booking, Payment classes
- **View**: MainFrame (Swing GUI)
- **Controller**: EventService (business logic)

### 2. **DAO Pattern**
- Abstracts database operations
- Each entity has its own DAO
- Centralizes database access logic

### 3. **Singleton Pattern**
- DBConnection ensures single database connection
- `getConnection()` returns same instance

### 4. **Service Layer Pattern**
- EventService encapsulates business logic
- Separates business rules from UI and data access

---

## 📦 Dependencies & Imports

### Core Java Packages
```
java.sql.* - JDBC connectivity
java.util.* - Collections (ArrayList, List)
java.awt.* - GUI components
javax.swing.* - Swing components
```

### External Dependencies
```
mysql-connector-java-8.0.33.jar - MySQL JDBC Driver
```

---

## ✅ Compilation Checklist

- [ ] Java JDK 8+ installed
- [ ] All .java files created in correct directories
- [ ] MySQL JDBC driver in java_src/
- [ ] No syntax errors in any file
- [ ] Import statements are correct
- [ ] Package names match file locations
- [ ] Classes are public
- [ ] No circular dependencies
- [ ] All .class files generated
- [ ] Classpath includes JDBC driver

---

## 🔍 Verification Commands

```bash
# Check Java compilation
cd java_src
ls -la model/*.class          # Should show compiled classes

# Count Java files
find . -name "*.java" | wc -l # Should be 13

# Verify directory structure
tree                           # Visual tree view
or
find . -type d                # List all directories

# Check JDBC driver
ls -la mysql-connector-java-8.0.33.jar

# Test compilation
javac -cp .:mysql-connector-java-8.0.33.jar Main.java -verbose
```

---

## 📱 GUI Components Hierarchy

```
MainFrame (JFrame)
   │
   ├─── mainPanel (JPanel with CardLayout)
   │    │
   │    ├─── loginPanel
   │    │    ├── titleLabel (JLabel)
   │    │    ├── centerPanel (JPanel with GridLayout)
   │    │    │  ├── usernameField (JTextField)
   │    │    │  └── passwordField (JPasswordField)
   │    │    └── buttonPanel
   │    │       ├── loginBtn (JButton)
   │    │       └── registerBtn (JButton)
   │    │
   │    └─── dashboardPanel
   │         ├── topBar (JPanel)
   │         │  ├── welcomeLabel (JLabel)
   │         │  └── logoutBtn (JButton)
   │         └── contentPanel (JPanel with GridLayout 2x2)
   │            ├── dashboardCard
   │            ├── dashboardCard
   │            ├── dashboardCard
   │            └── dashboardCard
   │
   └─── Menu Options
        ├── Browse Events
        ├── My Bookings
        ├── Create Event
        └── Admin Panel
```

---

## 🎓 How to Extend the Project

### Adding a New Feature

1. **Create Model Class** (if needed)
   ```
   Create in: java_src/model/NewEntity.java
   ```

2. **Create DAO Class**
   ```
   Create in: java_src/dao/NewEntityDAO.java
   ```

3. **Update Service** (if needed)
   ```
   Add methods to EventService.java
   ```

4. **Update GUI**
   ```
   Add new panel to MainFrame.java
   ```

5. **Update Database**
   ```
   Modify database_schema.sql
   ```

### Example: Adding Review Feature

```java
// 1. model/Review.java
public class Review {
    private int reviewId;
    private int eventId;
    private int userId;
    private int rating;
    private String comment;
    // ... getters/setters
}

// 2. dao/ReviewDAO.java
public class ReviewDAO {
    public boolean addReview(Review review) { ... }
    public List<Review> getEventReviews(int eventId) { ... }
    // ... other methods
}

// 3. Update EventService.java
public List<Review> getEventReviews(int eventId) { ... }

// 4. Update database_schema.sql
CREATE TABLE reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT NOT NULL,
    user_id INT NOT NULL,
    rating INT CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(event_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
```

---

## 🎉 Project Complete!

All files created and ready for:
✅ Compilation
✅ Execution
✅ Testing
✅ Extension
✅ Deployment

**Happy Learning & Coding!** 🚀
