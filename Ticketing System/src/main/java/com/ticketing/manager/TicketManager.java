package com.ticketing.manager;

import com.ticketing.database.DatabaseManager;
import com.ticketing.model.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private DatabaseManager databaseManager;

    public TicketManager() {
        this.databaseManager = new DatabaseManager();
    }

    // Get all tickets
    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Create a new ticket
    public void createTicket(String title, String description, int createdBy) {
        String query = "INSERT INTO Tickets (title, description, status, created_by) VALUES (?, ?, 'Open', ?)";
        try {
            databaseManager.executeUpdate(query, title, description, createdBy);
            System.out.println("Ticket created: " + title);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Search tickets by title
    public List<Ticket> searchTicketsByTitle(String keyword) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE title LIKE ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, "%" + keyword + "%");
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Filter tickets by status
    public List<Ticket> filterTicketsByStatus(String status) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE status = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, status);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Filter tickets by assigned agent
    public List<Ticket> filterTicketsByAssignedAgent(int agentId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE assigned_to = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, agentId);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Assign a ticket to an agent
    public void assignTicket(int ticketId, int agentId) {
        String query = "UPDATE Tickets SET assigned_to = ?, status = 'In Progress' WHERE id = ?";
        try {
            databaseManager.executeUpdate(query, agentId, ticketId);
            System.out.println("Ticket assigned: " + ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Resolve a ticket
    public void resolveTicket(int ticketId) {
        String query = "UPDATE Tickets SET status = 'Resolved' WHERE id = ?";
        try {
            databaseManager.executeUpdate(query, ticketId);
            System.out.println("Ticket resolved: " + ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a ticket's title and description
    public void updateTicket(int ticketId, String newTitle, String newDescription) {
        String query = "UPDATE Tickets SET title = ?, description = ? WHERE id = ?";
        try {
            databaseManager.executeUpdate(query, newTitle, newDescription, ticketId);
            System.out.println("Ticket updated: " + ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a ticket
    public void deleteTicket(int ticketId) {
        String query = "DELETE FROM Tickets WHERE id = ?";
        try {
            databaseManager.executeUpdate(query, ticketId);
            System.out.println("Ticket deleted: " + ticketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get tickets created by a specific user
    public List<Ticket> getTicketsByUser(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE created_by = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, userId);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Get tickets assigned to a specific agent
    public List<Ticket> getTicketsAssignedToAgent(int agentId) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM Tickets WHERE assigned_to = ?";
        try {
            ResultSet resultSet = databaseManager.executeQuery(query, agentId);
            while (resultSet.next()) {
                tickets.add(new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("created_by"),
                        resultSet.getInt("assigned_to")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}