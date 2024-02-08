package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Continent implements Writable {
    private String name;
    private String description;
    private final List<LandFormation> landFormations;

    public Continent(String name, String description) {
        this.name = name;
        this.description = description;
        landFormations = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Land Formation to the list of Land Formations
    public void addLandFormation(LandFormation lf) {
        landFormations.add(lf);
    }

    // REQUIRES: landFormations.size() >= num >= 1
    // MODIFIES: this
    // EFFECTS: removes a Land Formation to the list of Land Formations
    public void removeLandFormation(int num) {
        landFormations.remove(num - 1);
    }

    // EFFECTS: returns a Land Formation if it exists within the list, otherwise return null
    public LandFormation searchLandFormation(String search) {
        for (LandFormation lf : landFormations) {
            if (search.equals(lf.getName())) {
                return lf;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LandFormation> getLandFormations() {
        return landFormations;
    }

    @Override
    // EFFECTS: stores the information within Continent in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        json.put("Land Formations", landFormationsToJson());
        return json;
    }

    // EFFECTS: returns Land Formations in this Continent as a JSON array
    public JSONArray landFormationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (LandFormation lf : landFormations) {
            jsonArray.put(lf.toJson());
        }

        return jsonArray;
    }
}
