package org.example;

public class AnimalTypeNotFoundException extends Exception {
    public AnimalTypeNotFoundException(String type) {
        super("Animal type '" + type + "' is not recognized or supported.");
    }
}
