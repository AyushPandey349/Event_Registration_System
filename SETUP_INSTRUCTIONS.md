# Java Event Registration System - Complete Setup Guide

## 📋 Step-by-Step Installation & Running Guide

### ✅ Prerequisites Check

Before starting, ensure you have:
- ✓ Java JDK 8 or higher installed
- ✓ MySQL Server 5.7 or higher running
- ✓ Terminal/Command Prompt access
- ✓ Around 100MB free disk space

---

## 🔧 Step 1: Download MySQL JDBC Driver

**Option A: Command Line (Linux/Mac)**
```bash
cd /path/to/Event_Registration_System/java_src
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.33.jar
```

**Option B: Manual Download**
1. Visit: https://dev.mysql.com/downloads/connector/j/
2. Download version 8.0.33
3. Place `mysql-connector-java-8.0.33.jar` in `java_src/` directory

---

## 🗄️ Step 2: Create Database

### On Linux/Mac:
```bash
# Start MySQL
sudo service mysql start

# Login and run schema
mysql -u root -p < database_schema.sql
```

### On Windows:
```cmd
# Start MySQL (if installed as service)
net start MySQL80

# Run schema
mysql -u root -p < database_schema.sql
```

**When prompted for password, enter:** `root` (or your MySQL password)

---

## 🔌 Step 3: Verify Database Connection

Update database credentials in `java_src/utils/DBConnection.java`:

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/event_management";
private static final String DB_USER = "root";        // Change if different
private static final String DB_PASSWORD = "root";    // Change if different
```

---

## 🛠️ Step 4: Compile Java Files

### Option A: Using Automated Script

**Linux/Mac:**
```bash
chmod +x setup_and_compile.sh
./setup_and_compile.sh
```

**Windows:**
```cmd
setup_and_compile.bat
```

### Option B: Manual Compilation

```bash
# Navigate to java_src
cd java_src

# Compile (Linux/Mac)
javac -cp .:mysql-connector-java-8.0.33.jar model/*.java dao/*.java utils/*.java service/*.java gui/*.java Main.java

# Compile (Windows)
javac -cp .;mysql-connector-java-8.0.33.jar model\*.java dao\*.java utils\*.java service\*.java gui\*.java Main.java
```

**Expected Output:** No errors, all `.class` files created

---

## 🚀 Step 5: Run the Application

### Linux/Mac:
```bash
java -cp .:mysql-connector-java-8.0.33.jar Main
```

### Windows:
```cmd
java -cp .;mysql-connector-java-8.0.33.jar Main
```

**Expected Output:**
```
✓ Database connection established successfully!
[GUI Window Opens]
```

---

## 🔑 Step 6: Login to Application

**Test Accounts:**

| Role | Username | Password |
|------|----------|----------|
| Admin | admin_user | admin123 |
| Organizer | john_organizer | john123 |
| User | alice_user | alice123 |
| User | bob_user | bob123 |

---

## 📱 Application Features Overview

### Dashboard Menu
1. **Browse Events** - View all upcoming events
2. **My Bookings** - Check booking history
3. **Create Event** - Organize new event (Organizer)
4. **Admin Panel** - Manage system (Admin)

### Available Events (Preloaded)
- Tech Conference 2024 (₹999.99/ticket)
- Music Festival (₹500/ticket)
- Art Exhibition (₹250/ticket)
- Business Expo (₹1500/ticket)

---

## ⚠️ Troubleshooting

### Error: "No MySQL JDBC Driver in classpath"
**Solution:**
```bash
# Ensure JDBC jar is in java_src directory
ls java_src/mysql-connector-java-8.0.33.jar

# Or download it
cd java_src
wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.33.jar
```

### Error: "Connection refused"
**Solution:**
```bash
# Start MySQL service
sudo service mysql start          # Linux
brew services start mysql         # Mac
net start MySQL80                 # Windows

# Verify connection
mysql -u root -p -e "SELECT 1;"
```

### Error: "Access denied for user 'root'@'localhost'"
**Solution:**
1. Update password in `DBConnection.java`
2. Or reset MySQL root password

### Error: "No database 'event_management'"
**Solution:**
```bash
mysql -u root -p < database_schema.sql
```

---

## 📁 Project Structure After Compilation

```
Event_Registration_System/
├── java_src/
│   ├── model/
│   │   ├── User.java
│   │   ├── User.class ✓
│   │   ├── Event.java
│   │   ├── Event.class ✓
│   │   └── ...
│   ├── dao/
│   │   ├── UserDAO.java
│   │   ├── UserDAO.class ✓
│   │   └── ...
│   ├── Main.java
│   ├── Main.class ✓
│   ├── mysql-connector-java-8.0.33.jar ✓
│   └── ... (other compiled classes)
├── database_schema.sql
├── JAVA_PROJECT_README.md
└── ...
```

---

## ✅ Verification Checklist

- [ ] Java JDK installed (`java -version` shows 8+)
- [ ] MySQL Server running (`mysql -u root -p` works)
- [ ] Database created (`event_management` exists)
- [ ] JDBC Driver downloaded and placed
- [ ] All Java files compiled successfully
- [ ] No compilation errors
- [ ] Application runs without "Connection refused"
- [ ] Can login with test accounts

---

## 🔄 Continuous Development

### Modifying the Application

```bash
# Edit any .java file
# Recompile
javac -cp .:mysql-connector-java-8.0.33.jar Main.java

# Run again
java -cp .:mysql-connector-java-8.0.33.jar Main
```

### Adding New Features

1. Create new class in appropriate package
2. Compile: `javac -cp .:mysql-connector-java-8.0.33.jar yourfile.java`
3. Update Main or service class
4. Recompile and run

---

## 📊 Database Queries (for Testing)

```sql
-- Check created database
SHOW DATABASES;
USE event_management;

-- View all tables
SHOW TABLES;

-- Check users
SELECT * FROM users;

-- Check events
SELECT * FROM events;

-- Check bookings
SELECT * FROM bookings;

-- Check payments
SELECT * FROM payments;

-- Generate report
SELECT COUNT(*) as total_users FROM users;
SELECT COUNT(*) as total_events FROM events WHERE status='active';
SELECT SUM(amount) as total_revenue FROM payments WHERE status='success';
```

---

## 🎓 Learning Outcomes

After completing this project, you will have learned:

✓ **Core Java**: Classes, objects, methods, access modifiers
✓ **OOP**: Encapsulation, inheritance, polymorphism, abstraction
✓ **JDBC**: Database connectivity, PreparedStatements, ResultSets
✓ **Database Design**: Schema design, relationships, constraints
✓ **DAO Pattern**: Database abstraction layer
✓ **Service Layer**: Business logic separation
✓ **GUI Development**: Java Swing components
✓ **Exception Handling**: Try-catch, SQL exceptions
✓ **Collections**: ArrayList, List interface

---

## 📞 Quick Reference Commands

```bash
# Setup
mysql -u root -p < database_schema.sql

# Compile (from java_src directory)
javac -cp .:mysql-connector-java-8.0.33.jar model/*.java dao/*.java utils/*.java service/*.java gui/*.java Main.java

# Run
java -cp .:mysql-connector-java-8.0.33.jar Main

# Check database
mysql -u root -p event_management -e "SELECT COUNT(*) FROM users;"
```

---

## 🎉 Success!

If you see the GUI window with login screen, your setup is complete!

**Happy Coding! 🚀**
