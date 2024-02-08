package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Continent continent;
    private JsonWriter writer;

    @BeforeEach
    void runBefore() {
        continent = new Continent("Test Continent", "Test Description");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected!
        }
    }

    @Test
    void testWriterEmptyContinent() {
        try {
            writer = new JsonWriter("./data/testWriterEmptyContinent.json");
            writer.open();
            writer.write(continent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyContinent.json");
            continent = reader.read();
            assertEquals("Test Continent", continent.getName());
            assertEquals("Test Description", continent.getDescription());
            assertEquals(0, continent.getLandFormations().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralContinent() {
        try {
            continent.addLandFormation(new LandFormation("Mountain", "Towering Peaks"));
            continent.addLandFormation(new LandFormation("River", "Swift and Coursing"));
            writer = new JsonWriter("./data/testWriterGeneralContinent.json");
            writer.open();
            writer.write(continent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralContinent.json");
            continent = reader.read();
            assertEquals("Test Continent", continent.getName());
            assertEquals("Test Description", continent.getDescription());

            List<LandFormation> landFormations = continent.getLandFormations();
            assertEquals(2, landFormations.size());
            checkLandFormation("Mountain", "Towering Peaks", landFormations.get(0));
            checkLandFormation("River", "Swift and Coursing", landFormations.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterContinentToSmallItem() {
        try {
            LandFormation lf = new LandFormation("TestLF", "First Step");
            Settlement s = new Settlement("TestS", "Second Step");
            Location l = new Location("TestL", "Third Step");
            BigItem bi = new BigItem("TestBI", "Fourth Step");
            SmallItem si = new SmallItem("TestSI", "Fifth Step: All The Way Down");

            continent.addLandFormation(lf);
            lf.addSettlement(s);
            s.addLocation(l);
            l.addBigItems(bi);
            bi.addSmallItem(si);

            writer = new JsonWriter("./data/testWriterContinentToSmallItem.json");
            writer.open();
            writer.write(continent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterContinentToSmallItem.json");
            continent = reader.read();
            assertEquals("Test Continent", continent.getName());
            assertEquals("Test Description", continent.getDescription());

            List<LandFormation> landFormations = continent.getLandFormations();
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
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTwoOfEachEntity() {
        try {
            LandFormation lf = new LandFormation("TestLF", "First Step");
            LandFormation lf2 = new LandFormation("TestLF2", "Not A Step");
            Settlement s = new Settlement("TestS", "Second Step");
            Settlement s2 = new Settlement("TestS2", "Not A Step");
            Location l = new Location("TestL", "Third Step");
            Location l2 = new Location("TestL2", "Not A Step");
            BigItem bi = new BigItem("TestBI", "Fourth Step");
            BigItem bi2 = new BigItem("TestBI2", "Not A Step");
            SmallItem si = new SmallItem("TestSI", "Fifth Step: All The Way Down");
            SmallItem si2 = new SmallItem("TestSI2", "Not A Step");

            continent.addLandFormation(lf);
            continent.addLandFormation(lf2);
            lf.addSettlement(s);
            lf.addSettlement(s2);
            s.addLocation(l);
            s.addLocation(l2);
            l.addBigItems(bi);
            l.addBigItems(bi2);
            bi.addSmallItem(si);
            bi.addSmallItem(si2);

            writer = new JsonWriter("./data/testWriterTwoOfEachEntity.json");
            writer.open();
            writer.write(continent);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTwoOfEachEntity.json");
            continent = reader.read();
            assertEquals("Test Continent", continent.getName());
            assertEquals("Test Description", continent.getDescription());

            List<LandFormation> landFormations = continent.getLandFormations();
            assertEquals(2, landFormations.size());
            checkLandFormation("TestLF", "First Step", landFormations.get(0));
            checkLandFormation("TestLF2", "Not A Step", landFormations.get(1));
            LandFormation readLF = landFormations.get(0);

            List<Settlement> settlements = readLF.getSettlements();
            assertEquals(2, settlements.size());
            checkSettlement("TestS", "Second Step", settlements.get(0));
            checkSettlement("TestS2", "Not A Step", settlements.get(1));
            Settlement readS = settlements.get(0);

            List<Location> locations = readS.getLocations();
            assertEquals(2, locations.size());
            checkLocation("TestL", "Third Step", locations.get(0));
            checkLocation("TestL2", "Not A Step", locations.get(1));
            Location readL = locations.get(0);

            List<BigItem> bigItems = readL.getBigItems();
            assertEquals(2, bigItems.size());
            checkBigItem("TestBI", "Fourth Step", bigItems.get(0));
            checkBigItem("TestBI2", "Not A Step", bigItems.get(1));
            BigItem readBI = bigItems.get(0);

            List<SmallItem> smallItems = readBI.getSmallItems();
            assertEquals(2, smallItems.size());
            checkSmallItem("TestSI", "Fifth Step: All The Way Down", smallItems.get(0));
            checkSmallItem("TestSI2", "Not A Step", smallItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
