package com.ticketing;

import com.ticketing.manager.TicketManager;
import com.ticketing.manager.UserManager;
import com.ticketing.model.Ticket;
import com.ticketing.model.User;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private UserManager userManager;
    private TicketManager ticketManager;
    private Scanner scanner;

    public Menu(UserManager userManager, TicketManager ticketManager) {
        this.userManager = userManager;
        this.ticketManager = ticketManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Ticketing System Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userManager.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            showUserMenu(user);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void showUserMenu(User user) {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Search Tickets by Title");
            System.out.println("4. Filter Tickets by Status");
            System.out.println("5. Filter Tickets by Assigned Agent");
            System.out.println("6. Assign Ticket to Agent");
            System.out.println("7. Resolve Ticket");
            System.out.println("8. Update Ticket");
            System.out.println("9. Delete Ticket");
            System.out.println("10. View My Tickets");
            System.out.println("11. View Assigned Tickets");
            System.out.println("12. Change Password");
            System.out.println("13. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createTicket(user);
                    break;
                case 2:
                    viewAllTickets();
                    break;
                case 3:
                    searchTicketsByTitle();
                    break;
                case 4:
                    filterTicketsByStatus();
                    break;
                case 5:
                    filterTicketsByAssignedAgent();
                    break;
                case 6:
                    assignTicketToAgent();
                    break;
                case 7:
                    resolveTicket();
                    break;
                case 8:
                    updateTicket();
                    break;
                case 9:
                    deleteTicket();
                    break;
                case 10:
                    viewTicketsByUser(user);
                    break;
                case 11:
                    viewAssignedTickets(user);
                    break;
                case 12:
                    changePassword(user);
                    break;
                case 13:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createTicket(User user) {
        System.out.print("Enter ticket title: ");
        String title = scanner.nextLine();
        System.out.print("Enter ticket description: ");
        String description = scanner.nextLine();

        int ticketId = ticketManager.getTickets().size() + 1; // Generate a simple ID
        ticketManager.createTicket(title, description, user.getId());
    }

    private void viewAllTickets() {
        List<Ticket> tickets = ticketManager.getTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            System.out.println("\n--- All Tickets ---");
            tickets.forEach(System.out::println);
        }
    }

    private void searchTicketsByTitle() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();
        List<Ticket> searchResults = ticketManager.searchTicketsByTitle(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("No tickets found with title containing '" + keyword + "'.");
        } else {
            System.out.println("\n--- Search Results ---");
            searchResults.forEach(System.out::println);
        }
    }

    private void filterTicketsByStatus() {
        System.out.print("Enter status to filter by (Open, In Progress, Resolved): ");
        String status = scanner.nextLine();
        List<Ticket> filteredTickets = ticketManager.filterTicketsByStatus(status);
        if (filteredTickets.isEmpty()) {
            System.out.println("No tickets found with status '" + status + "'.");
        } else {
            System.out.println("\n--- Filtered Tickets by Status ---");
            filteredTickets.forEach(System.out::println);
        }
    }

    private void filterTicketsByAssignedAgent() {
        System.out.print("Enter agent ID to filter by: ");
        int agentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        List<Ticket> filteredTickets = ticketManager.filterTicketsByAssignedAgent(agentId);
        if (filteredTickets.isEmpty()) {
            System.out.println("No tickets found assigned to agent ID " + agentId + ".");
        } else {
            System.out.println("\n--- Filtered Tickets by Assigned Agent ---");
            filteredTickets.forEach(System.out::println);
        }
    }

    private void assignTicketToAgent() {
        System.out.print("Enter ticket ID to assign: ");
        int ticketId = scanner.nextInt();
        System.out.print("Enter agent ID to assign to: ");
        int agentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ticketManager.assignTicket(ticketId, agentId);
    }

    private void resolveTicket() {
        System.out.print("Enter ticket ID to resolve: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ticketManager.resolveTicket(ticketId);
    }

    private void updateTicket() {
        System.out.print("Enter ticket ID to update: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        ticketManager.updateTicket(ticketId, newTitle, newDescription);
    }

    private void deleteTicket() {
        System.out.print("Enter ticket ID to delete: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        ticketManager.deleteTicket(ticketId);
    }

    private void viewTicketsByUser(User user) {
        List<Ticket> tickets = ticketManager.getTicketsByUser(user.getId());
        if (tickets.isEmpty()) {
            System.out.println("No tickets found for user: " + user.getUsername());
        } else {
            System.out.println("\n--- Tickets Created by " + user.getUsername() + " ---");
            tickets.forEach(System.out::println);
        }
    }

    private void viewAssignedTickets(User user) {
        if (!user.getRole().equals("Agent")) {
            System.out.println("Only agents can view assigned tickets.");
            return;
        }
        List<Ticket> tickets = ticketManager.getTicketsAssignedToAgent(user.getId());
        if (tickets.isEmpty()) {
            System.out.println("No tickets assigned to you.");
        } else {
            System.out.println("\n--- Tickets Assigned to " + user.getUsername() + " ---");
            tickets.forEach(System.out::println);
        }
    }

    private void changePassword(User user) {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userManager.changePassword(user.getId(), newPassword);
        System.out.println("Password changed successfully.");
    }
}