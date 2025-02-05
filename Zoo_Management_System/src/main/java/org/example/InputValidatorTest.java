package org.example;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {

    @Test
    public void testValidName() {
        assertTrue(InputValidator.isValidName("John Doe"));
        assertFalse(InputValidator.isValidName("John123"));
        assertFalse(InputValidator.isValidName(""));
    }

    @Test
    public void testValidEmail() {
        assertTrue(InputValidator.isValidEmail("john.doe@example.com"));
        assertFalse(InputValidator.isValidEmail("john.doe"));
        assertFalse(InputValidator.isValidEmail("john@.com"));
    }

    @Test
    public void testValidAge() {
        assertTrue(InputValidator.isValidAge(25));
        assertFalse(InputValidator.isValidAge(-5));
        assertFalse(InputValidator.isValidAge(150));
    }

    @Test
    public void testValidDate() {
        assertTrue(InputValidator.isValidDate("2023-12-25"));
        assertFalse(InputValidator.isValidDate("12/25/2023"));
    }
}

