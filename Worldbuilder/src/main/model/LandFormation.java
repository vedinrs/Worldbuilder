package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class LandFormation implements Writable {
    private String name;
    private String description;
    private final List<Settlement> settlements;
    private final List<Location> locations;

    public LandFormation(String name, String description) {
        this.name = name;
        this.description = description;
        settlements = new ArrayList<>();
        locations = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Settlement to the end of the list
    public void addSettlement(Settlement s) {
        settlements.add(s);
    }

    // REQUIRES: settlements.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes the Settlement at the given index in the list
    public void removeSettlement(int num) {
        settlements.remove(num - 1);
    }

    // MODIFIES: this
    // EFFECTS: adds a Location to the end of the list
    public void addLocation(Location l) {
        locations.add(l);
    }

    // REQUIRES: locations.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes the Location at the given index in the list
    public void removeLocation(int num) {
        locations.remove(num - 1);
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

    public List<Settlement> getSettlements() {
        return settlements;
    }

    public List<Location> getLocations() {
        return locations;
    }

    @Override
    // EFFECTS: stores the information in this Land Formation in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("Settlements", settlementsToJson());
        return json;
    }

    // EFFECTS: returns Settlements in this Land Formation as a JSON array
    public JSONArray settlementsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Settlement s : settlements) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
