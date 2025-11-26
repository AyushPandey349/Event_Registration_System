package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main GUI Application Frame
 * Entry point for the GUI application
 */
public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel currentPanel;
    
    public MainFrame() {
        setTitle("Event Management System - EventFlow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Initialize card layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Add panels
        addLoginPanel();
        addDashboardPanel();
        
        add(mainPanel);
        
        // Show login panel initially
        cardLayout.show(mainPanel, "login");
        setVisible(true);
    }
    
    /**
     * Add login panel
     */
    private void addLoginPanel() {
        JPanel loginPanel = new JPanel(new BorderLayout(10, 10));
        loginPanel.setBackground(new Color(240, 240, 240));
        
        // Title
        JLabel titleLabel = new JLabel("Welcome to EventFlow");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Center panel with login fields
        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setPreferredSize(new Dimension(100, 40));
        loginBtn.setBackground(new Color(0, 102, 204));
        loginBtn.setForeground(Color.WHITE);
        
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setPreferredSize(new Dimension(100, 40));
        registerBtn.setBackground(new Color(0, 153, 0));
        registerBtn.setForeground(Color.WHITE);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                        "Please enter username and password!", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                        "Login successful! Welcome, " + username, 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "dashboard");
                }
            }
        });
        
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, 
                    "Registration feature coming soon!", 
                    "Info", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        
        loginPanel.add(titleLabel, BorderLayout.NORTH);
        loginPanel.add(centerPanel, BorderLayout.CENTER);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        mainPanel.add(loginPanel, "login");
    }
    
    /**
     * Add dashboard panel
     */
    private void addDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout(10, 10));
        dashboardPanel.setBackground(new Color(245, 245, 245));
        
        // Top bar
        JPanel topBar = new JPanel(new BorderLayout(10, 10));
        topBar.setBackground(new Color(0, 102, 204));
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel welcomeLabel = new JLabel("Dashboard - Welcome to EventFlow");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.WHITE);
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 12));
        logoutBtn.setPreferredSize(new Dimension(100, 30));
        logoutBtn.setBackground(new Color(200, 50, 50));
        logoutBtn.setForeground(Color.WHITE);
        
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });
        
        topBar.add(welcomeLabel, BorderLayout.WEST);
        topBar.add(logoutBtn, BorderLayout.EAST);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Dashboard cards
        contentPanel.add(createDashboardCard("Browse Events", "Browse and search for upcoming events"));
        contentPanel.add(createDashboardCard("My Bookings", "View and manage your event bookings"));
        contentPanel.add(createDashboardCard("Create Event", "Organize and create new events"));
        contentPanel.add(createDashboardCard("Admin Panel", "Manage users, events, and payments"));
        
        dashboardPanel.add(topBar, BorderLayout.NORTH);
        dashboardPanel.add(contentPanel, BorderLayout.CENTER);
        
        mainPanel.add(dashboardPanel, "dashboard");
    }
    
    /**
     * Create a dashboard card
     */
    private JPanel createDashboardCard(String title, String description) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 102, 204));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(100, 100, 100));
        
        JButton actionBtn = new JButton("Open");
        actionBtn.setFont(new Font("Arial", Font.BOLD, 12));
        actionBtn.setBackground(new Color(0, 153, 0));
        actionBtn.setForeground(Color.WHITE);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(descLabel, BorderLayout.CENTER);
        card.add(actionBtn, BorderLayout.SOUTH);
        
        return card;
    }
}
