package org.example;

import java.util.List;
import java.util.Scanner;

public class LoginSystem {

    public static User login() {
        Scanner scanner = new Scanner(System.in);
        List<User> users = UserUtils.loadUsers();

        System.out.println("=== Zoo Management Login ===");

        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.getUsername().equals(username) && user.validatePassword(password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    return user; // Return the authenticated user
                }
            }

            System.out.println("Invalid credentials. You have " + (attempts - 1) + " attempt(s) left.");
        }

        System.out.println("Too many failed attempts. Exiting system.");
        System.exit(1); // Exit after too many failed attempts
        return null;
    }
}
