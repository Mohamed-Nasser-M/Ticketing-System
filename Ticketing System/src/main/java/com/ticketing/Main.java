package com.ticketing;

import com.ticketing.manager.TicketManager;
import com.ticketing.manager.UserManager;
import com.ticketing.model.User;

public class Main {
    public static void main(String[] args) {
        // Initialize managers
        UserManager userManager = new UserManager();
        TicketManager ticketManager = new TicketManager();

        // Add some users
        userManager.addUser("admin", "admin123", "Admin");
        userManager.addUser("agent1", "agent123", "Agent");
        userManager.addUser("customer1", "customer123", "Customer");

        // Start the menu
        Menu menu = new Menu(userManager, ticketManager);
        menu.start();
    }
}