package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    protected void checkLandFormation(String name, String description, LandFormation lf) {
        assertEquals(name, lf.getName());
        assertEquals(description, lf.getDescription());
    }

    protected void checkSettlement(String name, String description, Settlement s) {
        assertEquals(name, s.getName());
        assertEquals(description, s.getDescription());
    }

    protected void checkLocation(String name, String description, Location l) {
        assertEquals(name, l.getName());
        assertEquals(description, l.getDescription());
    }

    protected void checkBigItem(String name, String description, BigItem bi) {
        assertEquals(name, bi.getName());
        assertEquals(description, bi.getDescription());
    }

    protected void checkSmallItem(String name, String description, SmallItem si) {
        assertEquals(name, si.getName());
        assertEquals(description, si.getDescription());
    }
}
