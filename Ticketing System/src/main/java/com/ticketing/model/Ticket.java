package com.ticketing.model;

public class Ticket {
    private int id;
    private String title;
    private String description;
    private String status; // Open, In Progress, Resolved
    private int createdBy; // User ID of the creator
    private int assignedTo; // User ID of the assigned agent

    public Ticket(int id, String title, String description, String status, int createdBy, int assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                '}';
    }
}