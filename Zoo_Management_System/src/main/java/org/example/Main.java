package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Zoo zoo = new Zoo();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String filename = "animals.json";
        boolean running = true;

        // Display the Welcome Panel
        displayWelcomePanel();

        while (running) {
            // Main Menu Scene
            System.out.println(YELLOW + "\nZoo Management System" + YELLOW);
            System.out.println("1. Visitor Scene" + YELLOW);
            System.out.println("2. Staff Scene" + YELLOW);
            System.out.println("3. Admin Scene" + YELLOW);
            System.out.println("4. Exit" + YELLOW);
            System.out.print("Choose an option: " + YELLOW);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> visitorScene(zoo, scanner);
                case 2 -> staffScene(zoo, dbHandler, scanner);
                case 3 -> adminScene(dbHandler, scanner);
                case 4 -> {
                    running = false;
                    System.out.println(GREEN + "Exiting the system. Goodbye!" + RESET);
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }

        scanner.close();
    }

    private static void displayWelcomePanel() {
        System.out.println(BLUE + "---------------------------------------------------------");
        System.out.println("      Welcome to the Zoo Management System!");
        System.out.println("---------------------------------------------------------");
        System.out.println("This system allows you to manage the zoo's animals,");
        System.out.println("interact with staff and visitors, and manage user data.");
        System.out.println("---------------------------------------------------------");
        System.out.println("Please choose an option from the main menu.");
        System.out.println("---------------------------------------------------------" + RESET);
    }

    // Visitor Scene
    private static void visitorScene(Zoo zoo, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println(MAGENTA + "\nVisitor Scene" + MAGENTA);
            System.out.println("1. See the whole zoo!" + MAGENTA);
            System.out.println("2. Search for a specific animal by name." + MAGENTA);
            System.out.println("3. Exit to Main Menu" + MAGENTA);

            System.out.print("Choose an option: " + MAGENTA);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> loadAnimalsFromFile(zoo, "animals.json");
                case 2 -> zoo.searchAnimalsByPattern("animals.json");
                case 3 -> {
                    System.out.println(GREEN + "Returning to main menu." + RESET);
                    running = false;  // Exit the loop, returning to main menu
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }
    }

    // Staff Scene
    private static void staffScene(Zoo zoo, DatabaseHandler dbHandler, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println(CYAN + "\nStaff Scene" + CYAN);
            System.out.println("1. Add Animal" + CYAN);
            System.out.println("2. Remove Animal" + CYAN);
            System.out.println("3. Sort Animals" + CYAN);
            System.out.println("4. Save Animals" + CYAN);
            System.out.println("5. List Animals" + CYAN);
            System.out.println("6. Exit to Main Menu" + CYAN);

            System.out.print("Choose an option: " + CYAN);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addAnimal(zoo, scanner);
                case 2 -> removeAnimal(zoo, scanner);
                case 3 -> zoo.parallelSortAnimalsByAge();
                case 4 -> FileUtils.saveAnimalsToFile(zoo.getAnimals(), "animals.json");
                case 5 -> loadAnimalsFromFile(zoo, "animals.json");
                case 6 -> {
                    System.out.println(GREEN + "Returning to main menu." + RESET);
                    running = false;  // Exit the loop, returning to main menu
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }
    }

    // Admin Scene (User Management with Login)
    private static void adminScene(DatabaseHandler dbHandler, Scanner scanner) {
        boolean running = true;
        while (running) {
            // Admin Login
            if (!adminLogin(scanner)) {
                System.out.println(RED + "Login failed. Returning to main menu." + RESET);
                return; // Exit if login fails
            }

            System.out.println(BRIGHT_YELLOW + "Welcome to the Admin Scene!" + BRIGHT_YELLOW);
            System.out.println("1. Create User" + BRIGHT_YELLOW);
            System.out.println("2. See All Users" + BRIGHT_YELLOW);
            System.out.println("3. Exit to Main Menu" + BRIGHT_YELLOW);

            System.out.print("Choose an option: " + BRIGHT_YELLOW);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {

                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    dbHandler.insertUser(name, email, age);
                }
                case 2 -> dbHandler.fetchAllUsers();
                case 3 -> {
                    System.out.println(GREEN + "Returning to main menu." + RESET);
                    running = false;  // Exit the loop, returning to main menu
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        }
    }

    // Admin Login
    private static boolean adminLogin(Scanner scanner) {
        System.out.println(YELLOW + "Admin Login" + RESET);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // In a real system, you might fetch these credentials from a secure database
        // or an encrypted file
        if (username.equals("admin") && password.equals("admin123")) {
            System.out.println(GREEN + "Login successful. Welcome, Admin!" + RESET);
            return true;
        } else {
            System.out.println(RED + "Invalid username or password." + RESET);
            return false;
        }
    }

    private static void addAnimal(Zoo zoo, Scanner scanner) {
        String type;
        while (true) {
            System.out.println("Enter animal type (Lion, Parrot, Dolphin):");
            type = scanner.nextLine().trim().toLowerCase();
            if (type.equals("lion") || type.equals("parrot") || type.equals("dolphin")) {
                break;  // Valid input, exit the loop
            } else {
                System.out.println(RED + "Invalid animal type. Please enter one of the following: lion, parrot, or dolphin." + RESET);
            }
        }

        String name;
        while (true) {
            System.out.println("Enter animal name:");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println(RED + "Name cannot be empty. Please enter a valid name." + RESET);
            }
        }

        int age = -1;
        while (age <= 0) {
            System.out.println("Enter animal age (positive integer):");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age <= 0) {
                    System.out.println(RED + "Age must be a positive integer. Please try again." + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please enter a valid positive integer for age." + RESET);
            }
        }

        Animal animal;
        switch (type) {
            case "lion" -> animal = new Lion(name, age);
            case "parrot" -> animal = new Parrot(name, age);
            case "dolphin" -> animal = new Dolphin(name, age);
            default -> {
                System.out.println(RED + "Unknown animal type. Animal not added." + RESET);
                return;
            }
        }

        zoo.addAnimal(animal);
        System.out.println(GREEN + "Added " + type + " named " + name + " to the zoo." + RESET);
    }

    private static void removeAnimal(Zoo zoo, Scanner scanner) {
        System.out.println("Enter the name of the animal to remove:");
        String name = scanner.nextLine().trim();
        boolean removed = zoo.removeAnimalByName(name);
        if (removed) {
            System.out.println(GREEN + "Animal with name " + name + " removed." + RESET);
        } else System.out.println(RED + "No animal found with the name " + name + "." + RESET);
    }

    private static void loadAnimalsFromFile(Zoo zoo, String filename) {
        List<Animal> loadedAnimals = FileUtils.loadAnimalsFromFile(filename);
        if (loadedAnimals.isEmpty()) {
            System.out.println(RED + "No animals loaded from file, or file is empty." + RESET);
        } else {
            zoo.getAnimals().addAll(loadedAnimals);
            System.out.println(GREEN + "Loaded animals from file:" + RESET);
            loadedAnimals.forEach(animal -> {
                System.out.println("Name: " + animal.getName());
                System.out.println("Age: " + animal.getAge());
                System.out.println("Is Hungry: " + animal.isHungry());
                System.out.println("---------------------------");
            });
        }
    }
}
