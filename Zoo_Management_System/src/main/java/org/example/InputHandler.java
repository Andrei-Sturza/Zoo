package org.example;

import java.util.Scanner;

public class InputHandler {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Collect and validate name
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        while (!InputValidator.isValidName(name)) {
            System.out.print("Invalid name. Please enter a valid name: ");
            name = scanner.nextLine();
        }

        // Collect and validate email
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        while (!InputValidator.isValidEmail(email)) {
            System.out.print("Invalid email. Please enter a valid email: ");
            email = scanner.nextLine();
        }

        // Collect and validate age
        System.out.print("Enter your age: ");
        int age;
        while (true) {
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (InputValidator.isValidAge(age)) {
                    break;
                } else {
                    System.out.print("Invalid age. Please enter a valid age (0-120): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a numeric value: ");
            }
        }

        // Output collected data
        System.out.println("Collected Data:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);

        scanner.close();
    }
}
