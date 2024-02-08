package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmallItemTest {
    private SmallItem testSmallItem;

    @BeforeEach
    void runBefore() {
        testSmallItem = new SmallItem("Test", "A small item.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", testSmallItem.getName());
        assertEquals("A small item.", testSmallItem.getDescription());
    }

    @Test
    void testSetName() {
        testSmallItem.setName("New Name");
        assertEquals("New Name", testSmallItem.getName());
    }

    @Test
    void testSetDescription() {
        testSmallItem.setDescription("New Description");
        assertEquals("New Description", testSmallItem.getDescription());
    }
}
