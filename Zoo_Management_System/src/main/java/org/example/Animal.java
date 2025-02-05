package org.example;

import java.util.List; // Importing List for use in the feedAnimals method

public abstract class Animal implements Feedable, Comparable<Animal>,AnimalType {
    protected String name;
    protected int age;
    protected Enclosure enclosure; // Field to store the enclosure of the animal
    protected boolean hungry; // Assume a hungry flag for simplicity

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.hungry = true; // Animals start as hungry by default
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure; // Assign the provided enclosure to the animal
    }

    @Override
    public int compareTo(Animal other) {
        return Integer.compare(this.age, other.age);
    }

    // Method to feed a list of animals
    public static void feedAnimals(List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.isHungry()) {
                System.out.println("Feeding " + animal.getDiet() + " to " + animal.getName() + ".");
                animal.feed(); // Call the feed method defined in the subclass
                System.out.println("Next feeding scheduled: " + animal.getFeedingSchedule());
                animal.setHungry(false); // Set the animal to not hungry after feeding
            } else {
                System.out.println(animal.getName() + " is not hungry.");
            }
        }
    }

    // Abstract methods that must be implemented by subclasses
    public abstract void feed();
    public abstract String getDiet();
    public abstract String getFeedingSchedule();
    public abstract String getType();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
