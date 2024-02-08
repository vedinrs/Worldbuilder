package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Settlement implements Writable {
    private String name;
    private String description;
    private final List<Location> locations;

    public Settlement(String name, String description) {
        this.name = name;
        this.description = description;
        locations = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a location to the end of the list
    public void addLocation(Location l) {
        locations.add(l);
    }

    // REQUIRES: locations.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes the location at the given index from the list
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

    public List<Location> getLocations() {
        return locations;
    }

    @Override
    // EFFECTS: stores the information in this Settlement in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("Locations", locationsToJson());
        return json;
    }

    // EFFECTS: returns Locations in this Settlement as a JSON array
    public JSONArray locationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Location l : locations) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
