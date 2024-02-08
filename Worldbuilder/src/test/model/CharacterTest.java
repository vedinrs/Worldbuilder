package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class CharacterTest {
    private Character testCharacter;
    private SmallItem testItem;

    @BeforeEach
    void runBefore() {
        String testName = "Test";
        String testOccupation = "Adventurer";
        String testDescription = "A bright, idealistic young hero";
        testCharacter = new Character(testName, testOccupation, testDescription);
        testItem = new SmallItem("Spoon", "A regular spoon.");
    }

    @Test
    void testConstructor() {
        assertEquals("Test", testCharacter.getName());
        assertEquals("Adventurer", testCharacter.getOccupation());
        assertEquals("A bright, idealistic young hero", testCharacter.getDescription());
        assertTrue(testCharacter.getInventory().isEmpty());
    }

    @Test
    void testAddItem() {
        testCharacter.addItem(testItem);
        assertEquals(1, testCharacter.getInventory().size());
        assertEquals(testItem, testCharacter.getInventory().get(0));
    }

    @Test
    void testAddMultipleItem() {
        SmallItem sword = new SmallItem("Sword", "A generic one");
        testCharacter.addItem(sword);
        testCharacter.addItem(testItem);

        assertEquals(sword, testCharacter.getInventory().get(0));
        assertEquals(testItem, testCharacter.getInventory().get(1));
    }

    @Test
    void testRemoveItem() {
        testCharacter.addItem(testItem);
        assertEquals(1, testCharacter.getInventory().size());

        testCharacter.removeItem(1);
        assertEquals(0, testCharacter.getInventory().size());
    }

    @Test
    void testRemoveMultipleItem() {
        SmallItem sword = new SmallItem("Sword", "A generic one");
        testCharacter.addItem(sword);
        testCharacter.addItem(testItem);

        testCharacter.removeItem(2);
        assertEquals(1, testCharacter.getInventory().size());

        testCharacter.removeItem(1);
        assertEquals(0, testCharacter.getInventory().size());
    }
}