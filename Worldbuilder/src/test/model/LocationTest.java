package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LocationTest {
    private Location testLocation;
    private BigItem testBI1;
    private BigItem testBI2;
    private BigItem testBI3;
    private SmallItem testSI1;
    private SmallItem testSI2;
    private SmallItem testSI3;

    @BeforeEach
    void runBefore() {
        testLocation = new Location("Test", "A test location.");
        testBI1 = new BigItem("Closet", "Yeah it's there.");
        testBI2 = new BigItem("Oven", "It's a big item!");
        testBI3 = new BigItem("Chest", "An old wood chest.");
        testSI1 = new SmallItem("Shirt", "It's in the closet.");
        testSI2 = new SmallItem("Pants", "Some pants.");
        testSI3 = new SmallItem("Scarf", "A nice scarf.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", testLocation.getName());
        assertEquals("A test location.", testLocation.getDescription());
        assertTrue(testLocation.getBigItems().isEmpty());
        assertTrue(testLocation.getSmallItems().isEmpty());
    }

    @Test
    void testAddBigItem() {
        testLocation.addBigItems(testBI1);
        assertEquals(testBI1, testLocation.getBigItems().get(0));
    }

    @Test
    void testAddMultipleBigItem() {
        testLocation.addBigItems(testBI1);
        testLocation.addBigItems(testBI2);
        testLocation.addBigItems(testBI3);

        assertEquals(testBI1, testLocation.getBigItems().get(0));
        assertEquals(testBI2, testLocation.getBigItems().get(1));
        assertEquals(testBI3, testLocation.getBigItems().get(2));
    }

    @Test
    void testAddSmallItem() {
        testLocation.addSmallItems(testSI1);
        assertEquals(testSI1, testLocation.getSmallItems().get(0));
    }

    @Test
    void testAddMultipleSmallItem() {
        testLocation.addSmallItems(testSI1);
        testLocation.addSmallItems(testSI2);
        testLocation.addSmallItems(testSI3);

        assertEquals(testSI1, testLocation.getSmallItems().get(0));
        assertEquals(testSI2, testLocation.getSmallItems().get(1));
        assertEquals(testSI3, testLocation.getSmallItems().get(2));
    }

    @Test
    void testAddBigItemContainingSmallItem() {
        testBI1.addSmallItem(testSI1);
        testLocation.addBigItems(testBI1);
        assertEquals(testBI1, testLocation.getBigItems().get(0));
        assertEquals(testSI1, testLocation.getBigItems().get(0).getSmallItems().get(0));
    }

    @Test
    void testAddBigItemThenSmallItem() {
        testLocation.addBigItems(testBI1);
        testBI1.addSmallItem(testSI1);
        assertEquals(testBI1, testLocation.getBigItems().get(0));
        assertEquals(testSI1, testLocation.getBigItems().get(0).getSmallItems().get(0));
    }

    @Test
    void testAddMultipleOfEach() {
        BigItem oven = new BigItem("Oven", "It's a big item!");
        SmallItem skillet = new SmallItem("Skillet", "A skillet in the oven.");
        SmallItem bread = new SmallItem("Bread Loaf", "Bread loaf inside the oven.");
        oven.addSmallItem(skillet);
        oven.addSmallItem(bread);

        SmallItem pants = new SmallItem("Pants", "Some pants.");
        testBI1.addSmallItem(testSI1);
        testBI1.addSmallItem(pants);

        testLocation.addBigItems(oven);
        testLocation.addBigItems(testBI1);

        List<BigItem> testBigItems = testLocation.getBigItems();

        assertEquals(skillet, testBigItems.get(0).getSmallItems().get(0));
        assertEquals(bread, testBigItems.get(0).getSmallItems().get(1));

        assertEquals(testSI1, testBigItems.get(1).getSmallItems().get(0));
        assertEquals(pants, testBigItems.get(1).getSmallItems().get(1));
    }

    @Test
    void testRemoveBigItem() {
        testLocation.addBigItems(testBI1);
        testLocation.removeBigItem(1);

        assertEquals(0, testLocation.getBigItems().size());
    }

    @Test
    void testRemoveMultipleBigItem() {
        testLocation.addBigItems(testBI1);
        testLocation.addBigItems(testBI2);
        testLocation.addBigItems(testBI3);

        testLocation.removeBigItem(2);
        assertEquals(2, testLocation.getBigItems().size());
        assertEquals(testBI1, testLocation.getBigItems().get(0));
        assertEquals(testBI3, testLocation.getBigItems().get(1));

        testLocation.removeBigItem(1);
        assertEquals(1, testLocation.getBigItems().size());
        assertEquals(testBI3, testLocation.getBigItems().get(0));
    }

    @Test
    void testRemoveSmallItem() {
        testLocation.addSmallItems(testSI1);
        testLocation.removeSmallItem(1);

        assertEquals(0, testLocation.getSmallItems().size());
    }

    @Test
    void testRemoveMultipleSmallItem() {
        testLocation.addSmallItems(testSI1);
        testLocation.addSmallItems(testSI2);
        testLocation.addSmallItems(testSI3);

        testLocation.removeSmallItem(1);
        assertEquals(2, testLocation.getSmallItems().size());
        assertEquals(testSI2, testLocation.getSmallItems().get(0));
        assertEquals(testSI3, testLocation.getSmallItems().get(1));

        testLocation.removeSmallItem(2);
        assertEquals(1, testLocation.getSmallItems().size());
        assertEquals(testSI2, testLocation.getSmallItems().get(0));
    }

    @Test
    void testSetName() {
        testLocation.setName("New Name");
        assertEquals("New Name", testLocation.getName());
    }

    @Test
    void testSetDescription() {
        testLocation.setDescription("New Description");
        assertEquals("New Description", testLocation.getDescription());
    }
}
