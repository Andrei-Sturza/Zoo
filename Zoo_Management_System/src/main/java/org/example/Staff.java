package org.example;
public class Staff implements Comparable<Staff> {
    private String name;
    private String role;
    private int experienceYears;

    public Staff(String name, String role, int experienceYears) {
        this.name = name;
        this.role = role;
        this.experienceYears = experienceYears;
    }

    // Implement Comparable<Staff> to define a natural ordering by name
    @Override
    public int compareTo(Staff other) {
        return this.name.compareTo(other.name);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    // Optional: ToString for easy display
    @Override
    public String toString() {
        return name + " (" + role + ", " + experienceYears + " years)";
    }
}

