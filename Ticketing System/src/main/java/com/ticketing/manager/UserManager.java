package com.ticketing.manager;

import com.ticketing.database.DatabaseManager;
import com.ticketing.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    private DatabaseManager databaseManager;

    public UserManager() {
        this.databaseManager = new DatabaseManager();
    }

    // Add a new user
    public void addUser(String username, String password, String role) {
        String query = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        try {
            databaseManager.executeUpdate(query, username, password, role);
            System.out.println("User added: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Authenticate a user
    public User authenticate(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, username, password);
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Change user password
    public void changePassword(int userId, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE id = ?";
        try {
            int rowsAffected = databaseManager.executeUpdate(query, newPassword, userId);
            if (rowsAffected > 0) {
                System.out.println("Password changed for user ID: " + userId);
            } else {
                System.out.println("Failed to change password. User ID not found: " + userId);
            }
        } catch (SQLException e) {
            System.err.println("Error changing password: " + e.getMessage());
        }
    }
}