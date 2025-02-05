package org.example;

import java.util.*;

public class Zoo {
    private List<Animal> animals;
    private List<Staff> staffMembers;

    public Zoo() {
        animals = new ArrayList<>();
        staffMembers = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addStaff(Staff staff) {
        staffMembers.add(staff);
    }

    // Method to feed all animals in the zoo
    public void feedAllAnimals() {
        System.out.println("Feeding all animals...");
        Animal.feedAnimals(animals); // Call the static method from Animal class
    }

//    public void sortAnimalsByAge() {
//        // Record the start time
//        long startTime = System.nanoTime();
//
//        // Load animals from the JSON file
//        List<Animal> animals = FileUtils.loadAnimalsFromFile("animals.json");
//
//        if (animals.isEmpty()) {
//            System.out.println("No animals found to sort.");
//            return;
//        }
//
//        // Sort the animals by age in ascending order
//        animals.sort(Comparator.comparingInt(Animal::getAge));
//
//        // Save the sorted list back to the file
//        FileUtils.saveAnimalsToFile(animals, "animals.json");
//
//        // Record the end time
//        long endTime = System.nanoTime();
//
//        // Calculate elapsed time in milliseconds
//        long elapsedTime = (endTime - startTime) / 1_000_000;
//
//        // Print the sorted animals and the elapsed time
//        System.out.println("Animals sorted by age (sequential):");
//        for (Animal animal : animals) {
//            System.out.println(animal.getName() + " - Age: " + animal.getAge());
//        }
//        System.out.println("Time taken: " + elapsedTime + " ms");
//    }


    public void parallelSortAnimalsByAge() {
        // Record the start time
        long startTime = System.nanoTime();

        // Load animals from the JSON file
        List<Animal> animals = FileUtils.loadAnimalsFromFile("animals.json");

        if (animals.isEmpty()) {
            System.out.println("No animals found to sort.");
            return;
        }

        // Parallel sort the animals by age
        List<Animal> sortedAnimals = animals.parallelStream()
                .sorted(Comparator.comparingInt(Animal::getAge))
                .toList();

        // Record the end time
        long endTime = System.nanoTime();

        // Calculate elapsed time in milliseconds
        long elapsedTime = (endTime - startTime) / 1_000_000;

        // Print the sorted animals and the elapsed time
        System.out.println("Animals sorted by age (parallel):");
        for (Animal animal : sortedAnimals) {
            System.out.println(animal.getName() + " - Age: " + animal.getAge());
        }
        System.out.println("Time taken: " + elapsedTime + " ms");
    }

    public void searchAnimalsByPattern(String filename) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to input the pattern
        System.out.print("Enter the pattern to search for: ");
        String pattern = scanner.nextLine();

        // Validate the pattern
        if (pattern == null || pattern.trim().isEmpty()) {
            System.out.println("Error: Pattern cannot be null or empty.");
            return;
        }

        // Load animals from the file
        List<Animal> fileAnimals = FileUtils.loadAnimalsFromFile(filename);
        if (fileAnimals.isEmpty()) {
            System.out.println("No animals found in the file.");
            return;
        }

        // Create Aho-Corasick object
        AhoCorasick ahoCorasick = new AhoCorasick();

        // Add the input pattern
        ahoCorasick.addPattern(pattern.toLowerCase());

        // Build failure links
        ahoCorasick.buildFailureLinks();

        // Search for the pattern in each animal's name
        boolean foundMatch = false;
        for (Animal animal : fileAnimals) {
            String nameToSearch = animal.getName().toLowerCase(); // Normalize for case-insensitive search

            Map<String, List<Integer>> matches = ahoCorasick.search(nameToSearch);

            if (!matches.isEmpty()) {
                foundMatch = true;
                System.out.println("Matches found for pattern '" + pattern + "' in animal: " + animal.getName());
                System.out.println("Animal type: " + animal.getType()); // Output animal type
                //matches.forEach((key, value) -> {
                    //System.out.println("Pattern: " + key + " found at positions: " + value);
                //});
            }
        }
        if (!foundMatch) {
            System.out.println("No matches found for the pattern '" + pattern + "' in any animal names.");
        }
    }




    public void sortStaffByName() {
        Collections.sort(staffMembers); // Assuming Staff implements Comparable<Staff>
        for (Staff staff : staffMembers) {
            System.out.println(staff);
        }
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public boolean removeAnimal(Animal animal) {
        if (animals.contains(animal)) {
            animals.remove(animal);
            System.out.println("Removed animal: " + animal.getName());
            return true; // Animal was removed
        } else {
            System.out.println("Animal not found: " + animal.getName());
            return false; // Animal not found
        }
    }

    public boolean removeAnimalByName(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                animals.remove(animal);
                System.out.println("Removed animal: " + animalName);
                return true;
            }
        }
        System.out.println("Animal not found: " + animalName);
        return false;
    }
}
