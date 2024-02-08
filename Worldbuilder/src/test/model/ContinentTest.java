package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContinentTest {
    private Continent testContinent;
    private LandFormation testLF1;
    private LandFormation testLF2;
    private LandFormation testLF3;

    @BeforeEach
    void runBefore() {
        testContinent = new Continent("Name", "Description");
        testLF1 = new LandFormation("Mountain", "A big one");
        testLF2 = new LandFormation("LF2", "A second one.");
        testLF3 = new LandFormation("LF3", "A second one.");
    }

    @Test
    void testConstructor() {
        assertEquals("Name", testContinent.getName());
        assertEquals("Description", testContinent.getDescription());
        assertEquals(0, testContinent.getLandFormations().size());
    }

    @Test
    void testAddLandFormation() {
        testContinent.addLandFormation(testLF1);
        assertEquals(1, testContinent.getLandFormations().size());
        assertEquals(testLF1, testContinent.getLandFormations().get(0));
    }

    @Test
    void testAddMultipleLandFormation() {
        testContinent.addLandFormation(testLF1);
        testContinent.addLandFormation(testLF2);
        testContinent.addLandFormation(testLF3);

        assertEquals(3, testContinent.getLandFormations().size());
        assertEquals(testLF1, testContinent.getLandFormations().get(0));
        assertEquals(testLF2, testContinent.getLandFormations().get(1));
        assertEquals(testLF3, testContinent.getLandFormations().get(2));
    }

    @Test
    void testRemoveLandFormation() {
        testContinent.addLandFormation(testLF1);
        testContinent.removeLandFormation(1);

        assertEquals(0, testContinent.getLandFormations().size());
    }

    @Test
    void testRemoveMultipleLandFormation() {
        testContinent.addLandFormation(testLF1);
        testContinent.addLandFormation(testLF2);
        testContinent.addLandFormation(testLF3);

        testContinent.removeLandFormation(2);
        assertEquals(2, testContinent.getLandFormations().size());
        assertEquals(testLF1, testContinent.getLandFormations().get(0));
        assertEquals(testLF3, testContinent.getLandFormations().get(1));

        testContinent.removeLandFormation(1);
        assertEquals(testLF3, testContinent.getLandFormations().get(0));
    }

    @Test
    void testSearchForLandFormationSuccess() {
        testContinent.addLandFormation(testLF1);

        assertEquals(testLF1, testContinent.searchLandFormation("Mountain"));
    }

    @Test
    void testSearchForLandFormationFailure() {
        testContinent.addLandFormation(testLF1);

        assertNull(testContinent.searchLandFormation("Mountaim"));
    }

    @Test
    void testSetName() {
        testContinent.setName("New Name");
        assertEquals("New Name", testContinent.getName());
    }

    @Test
    void testSetDescription() {
        testContinent.setDescription("New Description");
        assertEquals("New Description", testContinent.getDescription());
    }
}
