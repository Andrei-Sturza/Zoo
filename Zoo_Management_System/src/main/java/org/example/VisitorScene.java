package org.example;

import java.util.Scanner;

public class VisitorScene {

    public static void showVisitorScene(Scanner scanner) {
        System.out.println("Welcome, Visitor!");
        System.out.println("1. Browse Animals");
        System.out.println("2. Inquire About Animal");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                browseAnimals();
                break;
            case 2:
                inquireAboutAnimal(scanner);
                break;
            case 3:
                System.out.println("Exiting Visitor Scene.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void browseAnimals() {
        System.out.println("Browsing animals...");
        // Logic for browsing animals in the zoo, showing their details.
        // For instance, show animal names, age, type, etc.
        // You can iterate over a list of animals and print out relevant details.
    }

    private static void inquireAboutAnimal(Scanner scanner) {
        System.out.println("Inquiring about an animal...");
        System.out.print("Enter the animal's name to inquire: ");
        String name = scanner.nextLine();
        // Logic to look up the animal and provide details
        // For example, check if the animal exists and print its information
        System.out.println("Details for " + name + ": [Animal Details]");
    }
}
