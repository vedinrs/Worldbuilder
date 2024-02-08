package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    private JsonReader reader;

    @Test
    void testReaderNonExistentFile() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            Continent c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected!
        }
    }

    @Test
    void testReaderEmptyContinent() {
        reader = new JsonReader("./data/testReaderEmptyContinent.json");
        try {
            Continent c = reader.read();
            assertEquals("Test Continent", c.getName());
            assertEquals("Test Description", c.getDescription());
            assertEquals(0, c.getLandFormations().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralContinent() {
        reader = new JsonReader("./data/testReaderGeneralContinent.json");
        try {
            Continent c = reader.read();
            assertEquals("Test Continent", c.getName());
            assertEquals("Test Description", c.getDescription());

            List<LandFormation> landFormations = c.getLandFormations();
            assertEquals(2, landFormations.size());
            checkLandFormation("Mountain", "Towering Peaks", landFormations.get(0));
            checkLandFormation("River", "Swift and Coursing", landFormations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderContinentToSmallItem() {
        reader = new JsonReader("./data/testReaderContinentToSmallItem.json");
        try {
            Continent c = reader.read();
            assertEquals("Test Continent", c.getName());
            assertEquals("Test Description", c.getDescription());

            List<LandFormation> landFormations = c.getLandFormations();
            assertEquals(1, landFormations.size());
            checkLandFormation("TestLF", "First Step", landFormations.get(0));
            LandFormation readLF = landFormations.get(0);

            List<Settlement> settlements = readLF.getSettlements();
            assertEquals(1, settlements.size());
            checkSettlement("TestS", "Second Step", settlements.get(0));
            Settlement readS = settlements.get(0);

            List<Location> locations = readS.getLocations();
            assertEquals(1, locations.size());
            checkLocation("TestL", "Third Step", locations.get(0));
            Location readL = locations.get(0);

            List<BigItem> bigItems = readL.getBigItems();
            assertEquals(1, bigItems.size());
            checkBigItem("TestBI", "Fourth Step", bigItems.get(0));
            BigItem readBI = bigItems.get(0);

            List<SmallItem> smallItems = readBI.getSmallItems();
            assertEquals(1, smallItems.size());
            checkSmallItem("TestSI", "Fifth Step: All The Way Down", smallItems.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
