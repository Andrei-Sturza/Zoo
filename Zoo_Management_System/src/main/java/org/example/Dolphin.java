package org.example;

public class Dolphin extends Animal{

    public Dolphin(String name, int age) {
        super(name, age);
    }

    @Override
    public void feed() {
        System.out.println(getName() + " is being fed.");
        // Logic to reflect that the lion is no longer hungry after being fed
        setHungry(false); // Assuming the lion is fed and is now not hungry
    }

    @Override
    public String getDiet() {
        return "Fish";
    }

    @Override
    public String getFeedingSchedule() {
        return "Twice Daily";
    }
    @Override
    public String getType() {
        return "Dolphin";
    }
}

