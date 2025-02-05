package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ZooManagementTest {

    @Test
    public void testZooConstructor() {
        Zoo zoo = new Zoo();
        assertNotNull(zoo);
        assertTrue(zoo.getAnimals().isEmpty()); // Assuming Zoo initializes with an empty list of animals
    }

    @Test
    public void testLionConstructor() {
        Lion lion = new Lion("Simba", 5);
        assertNotNull(lion);
        assertEquals("Simba", lion.getName());
        assertEquals(5, lion.getAge());
        assertTrue(lion.isHungry());  // Change this to true
    }


    @Test
    public void testParrotConstructor() {
        Parrot parrot = new Parrot("Polly", 3);
        assertNotNull(parrot);
        assertEquals("Polly", parrot.getName());
        assertEquals(3, parrot.getAge());
    }

    @Test
    public void testDolphinConstructor() {
        Dolphin dolphin = new Dolphin("Flipper", 8);
        assertNotNull(dolphin);
        assertEquals("Flipper", dolphin.getName());
        assertEquals(8, dolphin.getAge());
    }

    @Test
    public void testDatabaseHandlerConstructor() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        assertNotNull(dbHandler);  // Verify that the DatabaseHandler object is created successfully
    }
}
