package org.example;

import java.util.ArrayList;
import java.util.List;

public class Enclosure implements Maintainable {
    private String type;
    private int capacity;
    private List<Animal> animals;
    private String maintenanceSchedule;

    public Enclosure(String type, int capacity, String maintenanceSchedule) {
        this.type = type;
        this.capacity = capacity;
        this.maintenanceSchedule = maintenanceSchedule;
        animals = new ArrayList<>();
    }

//    public boolean addAnimal(Animal animal) {
//        if (animals.size() < capacity) {
//            animals.add(animal);
//            animal.setEnclosure(this);
//            return true;
//        }
//        return false;
//    }

    @Override
    public void performMaintenance() {
        System.out.println("Performing maintenance on the " + type + " enclosure.");
    }

    @Override
    public String getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    // Getters and other methods
    public String getType() {
        return type;
    }

    public int getCapacity() { // Add this method to return the capacity
        return capacity;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
