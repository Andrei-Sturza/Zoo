package org.example;

public class Lion extends Animal{
    public Lion(String name, int age) {
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
        return "Meat";
    }

    @Override
    public String getFeedingSchedule() {
        return "Daily at 2 PM";
    }
    @Override
    public String getType() {
        return "Lion";
    }


}
