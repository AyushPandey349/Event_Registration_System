📌 Online Event Management System – Java Project

A Java-based application designed to help users register, browse events, book tickets, and manage event operations. The project follows core Java and OOP concepts, uses MySQL for data storage, and integrates JDBC for database connectivity.

🚀 Project Overview

The Online Event Management System is a mini-application that allows users to view events, book them, and manage bookings. Event organizers can create events, while the admin manages users, events, and bookings.

This project demonstrates:

Java fundamentals

Object-Oriented Programming

JDBC database connectivity

Database operations through DAO

Modular project structure

MVC-inspired architecture

🏗️ Features
👤 User Module

User Registration

User Login

Browse Events

Search & Filter Events

Book Events / Tickets

View Booking History

🎟️ Event Organizer Module

Create new events

Edit or remove existing events

🛠️ Admin Module

Manage Users

Manage Events

Manage Bookings

Generate activity reports

📚 Core Java & OOP Concepts Used

Classes & Objects

Inheritance (e.g., User → Admin, Organizer)

Polymorphism

Abstraction using interfaces

Encapsulation

Packages for structured code

Exception handling

File handling (optional enhancements)

🏛️ Project Structure
/src
 ├── model
 │    ├── User.java
 │    ├── Event.java
 │    ├── Booking.java
 │    └── Payment.java
 ├── dao
 │    ├── UserDAO.java
 │    ├── EventDAO.java
 │    ├── BookingDAO.java
 │    └── AdminDAO.java
 ├── utils
 │    └── DBConnection.java
 ├── service
 │    └── EventService.java
 ├── gui or main
 │    └── Main.java

🗄️ Database Design (MySQL)
Tables:

users

events

bookings

payments

admin

Key Entities

Users: store login & role details

Events: store event information

Bookings: track user-event bookings

Payments: optional simulation

🔌 JDBC Connectivity

The project uses:

java.sql.Connection

PreparedStatement

ResultSet

DriverManager

DBConnection.java handles:

Establishing MySQL connection

Reusable connection object

Exception management

🧩 DAO Layer (Database Operations)

Each DAO performs CRUD operations:

Insert new records

Update existing records

Delete entries

Fetch records with SELECT queries

Login verification

Search & filter queries

🖥️ GUI / User Interface

You may use:

Java Swing
OR

JavaFX

Screens:

Login Screen

Registration Form

Event List Page

Booking Page

Admin Dashboard

🧪 Testing

Include:

Input/output test cases

Login tests

Event booking tests

Database CRUD tests

🔮 Future Enhancements

Online payment gateway integration

Email/SMS notifications

Mobile app version

QR-based ticket generation

Rest API backend

📌 How to Run the Project

Install Java JDK 8+

Install MySQL Server

Create the database using the given SQL script

Update DB credentials in DBConnection.java

Compile the project:

javac *.java


Run the main file:

java Main

📄 Conclusion

This project provides a complete overview of applying Java, OOP, JDBC, and MySQL in real-world applications. It offers a modular structure and can be expanded into a full web or mobile-based event management platform.