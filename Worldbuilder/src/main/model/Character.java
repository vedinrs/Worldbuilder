package model;

import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private String occupation;
    private String description;
    private List<SmallItem> inventory;

    public Character(String name, String occupation, String description) {
        this.name = name;
        this.occupation = occupation;
        this.description = description;
        inventory = new ArrayList<SmallItem>();
    }

    public void addItem(SmallItem item) {
        inventory.add(item);
    }

    public void removeItem(int num) {
        inventory.remove(num - 1);
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDescription() {
        return description;
    }

    public List<SmallItem> getInventory() {
        return inventory;
    }
}
