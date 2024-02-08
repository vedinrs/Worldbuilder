package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

public class BigItem implements Writable {
    private String name;
    private String description;
    private final List<SmallItem> smallItems;

    public BigItem(String name, String description) {
        this.name = name;
        this.description = description;
        smallItems = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a given small item into the list of small items
    public void addSmallItem(SmallItem si) {
        smallItems.add(si);
    }

    // REQUIRES: smallItems.size() >= num >= 0
    // MODIFIES: this
    // EFFECTS: removes the given index from the list of small items
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

    public List<SmallItem> getSmallItems() {
        return smallItems;
    }

    @Override
    // EFFECTS: stores the information in this Big Item in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("Small Items", smallItemsToJson());
        return json;
    }

    // EFFECTS: returns Small Items in this Big Item as a JSON array
    public JSONArray smallItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SmallItem si : smallItems) {
            jsonArray.put(si.toJson());
        }

        return jsonArray;
    }

}
