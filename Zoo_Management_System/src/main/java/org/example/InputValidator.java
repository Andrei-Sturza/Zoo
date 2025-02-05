package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {

    // Validate names (alphabetic, max length 50)
    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]{1,50}");
    }

    // Validate email format
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // Validate numeric input (e.g., age between 0 and 120)
    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 120;
    }

    // Validate date (format: yyyy-MM-dd)
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
