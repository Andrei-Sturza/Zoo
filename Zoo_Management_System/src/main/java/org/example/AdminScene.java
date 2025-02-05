package org.example;

import java.util.Scanner;

public class AdminScene {

    public static void showAdminScene(Scanner scanner) {
        System.out.println("Welcome, Admin!");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Animals");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                manageUsers(scanner);
                break;
            case 2:
                manageAnimals(scanner);
                break;
            case 3:
                System.out.println("Exiting Admin Scene.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void manageUsers(Scanner scanner) {
        System.out.println("Managing Users...");
        // Logic for user management, e.g., view users, add new users, etc.
    }

    private static void manageAnimals(Scanner scanner) {
        System.out.println("Managing Animals...");
        // Logic for managing animals (e.g., add new animals, remove animals, etc.)
    }
}
