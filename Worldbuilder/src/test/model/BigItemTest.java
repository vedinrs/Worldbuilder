package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BigItemTest {
    private BigItem testBigItem;
    private SmallItem testSI1;
    private SmallItem testSI2;
    private SmallItem testSI3;

    @BeforeEach
    void runBefore() {
        testBigItem = new BigItem("Test", "A big item.");
        testSI1 = new SmallItem("Spoon", "A silver spoon.");
        testSI2 = new SmallItem("Fork", "A silver fork.");
        testSI3 = new SmallItem("Knife", "A silver knife.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", testBigItem.getName());
        assertEquals("A big item.", testBigItem.getDescription());
        assertTrue(testBigItem.getSmallItems().isEmpty());
    }

    @Test
    void testAddSmallItem() {
        testBigItem.addSmallItem(testSI1);
        assertEquals(testSI1, testBigItem.getSmallItems().get(0));
    }

    @Test
    void testAddMultipleSmallItem() {
        testBigItem.addSmallItem(testSI1);
        testBigItem.addSmallItem(testSI2);
        testBigItem.addSmallItem(testSI3);
        testBigItem.addSmallItem(testSI2);

        assertEquals(testSI1, testBigItem.getSmallItems().get(0));
        assertEquals(testSI2, testBigItem.getSmallItems().get(1));
        assertEquals(testSI3, testBigItem.getSmallItems().get(2));
        assertEquals(testSI2, testBigItem.getSmallItems().get(3));
    }

    @Test
    void testRemoveSmallItem() {
        testBigItem.addSmallItem(testSI1);
        testBigItem.removeSmallItem(1);

        assertEquals(0, testBigItem.getSmallItems().size());
    }

    @Test
    void testRemoveMultipleSmallItem() {
        testBigItem.addSmallItem(testSI1);
        testBigItem.addSmallItem(testSI2);
        testBigItem.addSmallItem(testSI3);

        testBigItem.removeSmallItem(2);
        assertEquals(2, testBigItem.getSmallItems().size());
        assertEquals(testSI1, testBigItem.getSmallItems().get(0));
        assertEquals(testSI3, testBigItem.getSmallItems().get(1));

        testBigItem.removeSmallItem(2);
        assertEquals(1, testBigItem.getSmallItems().size());
        assertEquals(testSI1, testBigItem.getSmallItems().get(0));
    }

    @Test
    void testSetName() {
        testBigItem.setName("New Name");
        assertEquals("New Name", testBigItem.getName());
    }

    @Test
    void testSetDescription() {
        testBigItem.setDescription("New Description");
        assertEquals("New Description", testBigItem.getDescription());
    }
}
