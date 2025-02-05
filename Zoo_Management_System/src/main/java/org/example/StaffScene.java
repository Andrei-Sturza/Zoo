package org.example;

import java.util.Scanner;

public class StaffScene {

    public static void showStaffScene(Scanner scanner) {
        System.out.println("Welcome, Staff Member!");
        System.out.println("1. Add Animal");
        System.out.println("2. View All Animals");
        System.out.println("3. Remove Animal");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addAnimal(scanner);
                break;
            case 2:
                viewAllAnimals();
                break;
            case 3:
                removeAnimal(scanner);
                break;
            case 4:
                System.out.println("Exiting Staff Scene.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void addAnimal(Scanner scanner) {
        // Add animal functionality
        System.out.println("Enter animal type (Lion, Parrot, Dolphin):");
        String type = scanner.nextLine().trim().toLowerCase();

        String name;
        System.out.println("Enter animal name:");
        name = scanner.nextLine().trim();

        int age = -1;
        while (age <= 0) {
            System.out.println("Enter animal age (positive integer):");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println("Age must be a positive integer. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer for age.");
            }
        }

        Animal animal;
        switch (type) {
            case "lion" -> animal = new Lion(name, age);
            case "parrot" -> animal = new Parrot(name, age);
            case "dolphin" -> animal = new Dolphin(name, age);
            default -> {
                System.out.println("Unknown animal type. Animal not added.");
                return;
            }
        }

        // Assuming `zoo` is an instance of the Zoo class that stores all the animals
        Zoo zoo = new Zoo(); // Instantiate the Zoo object
        zoo.addAnimal(animal);
        System.out.println("Added " + type + " named " + name + " to the zoo.");
    }

    private static void viewAllAnimals() {
        // Logic for viewing all animals in the zoo
        System.out.println("Viewing all animals in the zoo...");
        Zoo zoo = new Zoo(); // Assuming Zoo object holds animals list
        for (Animal animal : zoo.getAnimals()) {
            System.out.println("Name: " + animal.getName() + ", Age: " + animal.getAge());
        }
    }

    private static void removeAnimal(Scanner scanner) {
        // Logic for removing an animal
        System.out.print("Enter the name of the animal to remove: ");
        String name = scanner.nextLine();

        Zoo zoo = new Zoo(); // Assuming Zoo object holds animals list
        Animal animalToRemove = null;
        for (Animal animal : zoo.getAnimals()) {
            if (animal.getName().equalsIgnoreCase(name)) {
                animalToRemove = animal;
                break;
            }
        }

        if (animalToRemove != null) {
            zoo.removeAnimal(animalToRemove);
            System.out.println("Removed animal: " + name);
        } else {
            System.out.println("Animal not found!");
        }
    }
}
