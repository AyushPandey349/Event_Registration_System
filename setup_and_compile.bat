@echo off
REM Event Registration System - Windows Compilation Script

echo ======================================================
echo Event Management System - Windows Setup
echo ======================================================
echo.

REM Check Java
echo [1/5] Checking Java installation...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo X Java is not installed. Please install JDK 8 or higher
    pause
    exit /b 1
)
echo OK Java is installed
echo.

REM Check MySQL
echo [2/5] Checking MySQL installation...
mysql --version >nul 2>&1
if %errorlevel% neq 0 (
    echo X MySQL is not installed. Please install MySQL Server
    pause
    exit /b 1
)
echo OK MySQL is installed
echo.

REM Navigate to java_src
echo [3/5] Navigating to java_src directory...
cd java_src
echo OK Current directory: %CD%
echo.

REM Check JDBC Driver
echo [4/5] Checking MySQL JDBC Driver...
if not exist "mysql-connector-java-8.0.33.jar" (
    echo ! MySQL JDBC Driver not found
    echo   Please download from: https://dev.mysql.com/downloads/connector/j/
    echo   and place it in the java_src directory
    echo.
)

REM Compile
echo [5/5] Compiling Java files...

echo Compiling model classes...
javac model\*.java 2>nul
echo OK Model classes compiled

echo Compiling utility classes...
javac -cp . utils\DBConnection.java 2>nul
echo OK Utility classes compiled

echo Compiling DAO classes...
javac -cp . dao\*.java 2>nul
echo OK DAO classes compiled

echo Compiling service classes...
javac -cp . service\*.java 2>nul
echo OK Service classes compiled

echo Compiling GUI classes...
javac -cp . gui\*.java 2>nul
echo OK GUI classes compiled

echo Compiling Main application...
javac -cp . Main.java 2>nul
echo OK Main application compiled

echo.
echo ======================================================
echo OK Compilation completed successfully!
echo ======================================================
echo.
echo Next Steps:
echo 1. Setup MySQL database:
echo    mysql -u root -p < ..\database_schema.sql
echo.
echo 2. Run the application:
echo    java -cp .;mysql-connector-java-8.0.33.jar Main
echo.
echo 3. Login with:
echo    Username: admin_user
echo    Password: admin123
echo.
echo ======================================================
pause
