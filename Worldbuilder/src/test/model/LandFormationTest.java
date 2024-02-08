package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LandFormationTest {
    private LandFormation testLandFormation;
    private Settlement testS1;
    private Settlement testS2;
    private Settlement testS3;
    private Location testL1;
    private Location testL2;
    private Location testL3;

    @BeforeEach
    void runBefore() {
        testLandFormation = new LandFormation("Test", "A test Land Formation.");
        testS1 = new Settlement("TestS1", "A test settlement");
        testS2 = new Settlement("TestS2", "A test settlement");
        testS3 = new Settlement("TestS3", "A test settlement");
        testL1 = new Location("TestL1", "A test location.");
        testL2 = new Location("TestL2", "A test location.");
        testL3 = new Location("TestL3", "A test location.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", testLandFormation.getName());
        assertEquals("A test Land Formation.", testLandFormation.getDescription());
        assertEquals(0, testLandFormation.getSettlements().size());
        assertEquals(0, testLandFormation.getLocations().size());
    }

    // Tests for Settlement -- START
    @Test
    void testAddSettlement() {
        testLandFormation.addSettlement(testS1);
        assertEquals(testS1, testLandFormation.getSettlements().get(0));
    }

    @Test
    void testAddMultipleSettlement() {
        testLandFormation.addSettlement(testS1);
        testLandFormation.addSettlement(testS2);
        testLandFormation.addSettlement(testS3);

        assertEquals(3, testLandFormation.getSettlements().size());
        assertEquals(testS1, testLandFormation.getSettlements().get(0));
        assertEquals(testS2, testLandFormation.getSettlements().get(1));
        assertEquals(testS3, testLandFormation.getSettlements().get(2));
    }

    @Test
    void testRemoveSettlement() {
        testLandFormation.addSettlement(testS1);
        testLandFormation.removeSettlement(1);

        assertEquals(0, testLandFormation.getSettlements().size());
    }

    @Test
    void testRemoveMultipleSettlement() {
        testLandFormation.addSettlement(testS1);
        testLandFormation.addSettlement(testS2);
        testLandFormation.addSettlement(testS3);

        testLandFormation.removeSettlement(2);
        assertEquals(2, testLandFormation.getSettlements().size());
        assertEquals(testS1, testLandFormation.getSettlements().get(0));
        assertEquals(testS3, testLandFormation.getSettlements().get(1));

        testLandFormation.removeSettlement(1);
        assertEquals(1, testLandFormation.getSettlements().size());
        assertEquals(testS3, testLandFormation.getSettlements().get(0));
    }
    // Tests for Settlement -- END

    // Test for Location -- START
    @Test
    void testAddLocation() {
        testLandFormation.addLocation(testL1);
        assertEquals(1, testLandFormation.getLocations().size());
    }

    @Test
    void testAddMultipleLocation() {
        testLandFormation.addLocation(testL1);
        testLandFormation.addLocation(testL2);
        testLandFormation.addLocation(testL3);

        assertEquals(3, testLandFormation.getLocations().size());
        assertEquals(testL1, testLandFormation.getLocations().get(0));
        assertEquals(testL2, testLandFormation.getLocations().get(1));
        assertEquals(testL3, testLandFormation.getLocations().get(2));
    }

    @Test
    void testRemoveLocation() {
        testLandFormation.addLocation(testL1);
        testLandFormation.removeLocation(1);

        assertEquals(0, testLandFormation.getLocations().size());
    }

    @Test
    void testRemoveMultipleLocation() {
        testLandFormation.addLocation(testL1);
        testLandFormation.addLocation(testL2);
        testLandFormation.addLocation(testL3);

        testLandFormation.removeLocation(2);
        assertEquals(2, testLandFormation.getLocations().size());
        assertEquals(testL1, testLandFormation.getLocations().get(0));
        assertEquals(testL3, testLandFormation.getLocations().get(1));

        testLandFormation.removeLocation(1);
        assertEquals(1, testLandFormation.getLocations().size());
    }

    @Test
    void testSetName() {
        testLandFormation.setName("New Name");
        assertEquals("New Name", testLandFormation.getName());
    }

    @Test
    void testSetDescription() {
        testLandFormation.setDescription("New Description");
        assertEquals("New Description", testLandFormation.getDescription());
    }

}
