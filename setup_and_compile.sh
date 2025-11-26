#!/bin/bash

# Event Registration System - Java Setup & Compilation Script
# This script helps setup and compile the Java project

echo "======================================================"
echo "Event Management System - Setup Script"
echo "======================================================"
echo ""

# Check Java installation
echo "[1/5] Checking Java installation..."
if command -v java &> /dev/null; then
    java -version
    echo "✓ Java is installed"
else
    echo "✗ Java is not installed. Please install JDK 8 or higher"
    exit 1
fi

echo ""

# Check MySQL installation
echo "[2/5] Checking MySQL installation..."
if command -v mysql &> /dev/null; then
    mysql --version
    echo "✓ MySQL is installed"
else
    echo "✗ MySQL is not installed. Please install MySQL Server"
    exit 1
fi

echo ""

# Navigate to java_src
echo "[3/5] Navigating to java_src directory..."
cd java_src || exit 1
echo "✓ Current directory: $(pwd)"

echo ""

# Download MySQL JDBC Driver if not present
echo "[4/5] Checking MySQL JDBC Driver..."
if [ ! -f "mysql-connector-java-8.0.33.jar" ]; then
    echo "⚠ MySQL JDBC Driver not found. Downloading..."
    echo "Note: Manual download may be required from:"
    echo "https://dev.mysql.com/downloads/connector/j/"
    echo ""
    echo "For now, please download and place mysql-connector-java-8.0.33.jar in java_src/"
else
    echo "✓ MySQL JDBC Driver found"
fi

echo ""

# Compile Java files
echo "[5/5] Compiling Java files..."
echo "Compiling model classes..."
javac model/*.java 2>/dev/null && echo "✓ Model classes compiled"

echo "Compiling utility classes..."
javac -cp . utils/DBConnection.java 2>/dev/null && echo "✓ Utility classes compiled"

echo "Compiling DAO classes..."
javac -cp . dao/*.java 2>/dev/null && echo "✓ DAO classes compiled"

echo "Compiling service classes..."
javac -cp . service/*.java 2>/dev/null && echo "✓ Service classes compiled"

echo "Compiling GUI classes..."
javac -cp . gui/*.java 2>/dev/null && echo "✓ GUI classes compiled"

echo "Compiling Main application..."
javac -cp . Main.java 2>/dev/null && echo "✓ Main application compiled"

echo ""
echo "======================================================"
echo "✓ Compilation completed successfully!"
echo "======================================================"
echo ""
echo "Next Steps:"
echo "1. Setup MySQL database:"
echo "   mysql -u root -p < ../database_schema.sql"
echo ""
echo "2. Run the application:"
echo "   java -cp .:mysql-connector-java-8.0.33.jar Main"
echo ""
echo "3. Login with:"
echo "   Username: admin_user"
echo "   Password: admin123"
echo ""
echo "======================================================"
