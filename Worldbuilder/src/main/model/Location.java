package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Location implements Writable {
    private String name;
    private String description;
    private final List<BigItem> bigItems;
    private final List<SmallItem> smallItems;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        bigItems = new ArrayList<>();
        smallItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a big item to the end of the list
    public void addBigItems(BigItem bi) {
        bigItems.add(bi);
    }

    // REQUIRES: bigItems.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes the big item in the given index of the list
    public void removeBigItem(int num) {
        bigItems.remove(num - 1);
    }

    // MODIFIES: this
    // EFFECTS: adds a small item to the end of the list
    public void addSmallItems(SmallItem si) {
        smallItems.add(si);
    }

    // REQUIRES: smallItems.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes the small item in the given index of the list
    public void removeSmallItem(int num) {
        smallItems.remove(num - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public List<BigItem> getBigItems() {
        return bigItems;
    }

    public List<SmallItem> getSmallItems() {
        return smallItems;
    }

    @Override
    // EFFECTS: stores the information in this Location in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("Big Items", bigItemsToJson());
        return json;
    }

    // EFFECTS: returns Big Items in this Location as a JSON array
    public JSONArray bigItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (BigItem bi : bigItems) {
            jsonArray.put(bi.toJson());
        }

        return jsonArray;
    }
}
