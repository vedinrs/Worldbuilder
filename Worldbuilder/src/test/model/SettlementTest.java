package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettlementTest {
    private Settlement testSettlement;
    private Location testL1;
    private Location testL2;
    private Location testL3;

    @BeforeEach
    void runBefore() {
        testSettlement = new Settlement("Test Settlement", "A test settlement...");
        testL1 = new Location("Test", "Test Location.");
        testL2 = new Location("Test", "Test Location.");
        testL3 = new Location("Test", "Test Location.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test Settlement", testSettlement.getName());
        assertEquals("A test settlement...", testSettlement.getDescription());
        assertEquals(0, testSettlement.getLocations().size());
    }

    @Test
    void testAddLocation() {
        testSettlement.addLocation(testL1);
        assertEquals(1, testSettlement.getLocations().size());
        assertEquals(testL1, testSettlement.getLocations().get(0));
    }

    @Test
    void testAddMultipleLocation() {
        testSettlement.addLocation(testL1);
        testSettlement.addLocation(testL2);
        testSettlement.addLocation(testL3);

        assertEquals(3, testSettlement.getLocations().size());
        assertEquals(testL1, testSettlement.getLocations().get(0));
        assertEquals(testL2, testSettlement.getLocations().get(1));
        assertEquals(testL3, testSettlement.getLocations().get(2));
    }

    @Test
    void testRemoveLocation() {
        testSettlement.addLocation(testL1);
        testSettlement.removeLocation(1);
        assertEquals(0, testSettlement.getLocations().size());
    }

    @Test
    void testRemoveMultipleLocation() {
        testSettlement.addLocation(testL1);
        testSettlement.addLocation(testL2);
        testSettlement.addLocation(testL3);
        testSettlement.removeLocation(1);

        assertEquals(2, testSettlement.getLocations().size());
        assertEquals(testL2, testSettlement.getLocations().get(0));
        assertEquals(testL3, testSettlement.getLocations().get(1));

        testSettlement.removeLocation(2);
        assertEquals(1, testSettlement.getLocations().size());
        assertEquals(testL2, testSettlement.getLocations().get(0));
    }

    @Test
    void testSetName() {
        testSettlement.setName("New Name");
        assertEquals("New Name", testSettlement.getName());
    }

    @Test
    void testSetDescription() {
        testSettlement.setDescription("New Description");
        assertEquals("New Description", testSettlement.getDescription());
    }

}
