package model;

import org.json.JSONObject;
import persistence.Writable;

public class SmallItem implements Writable {
    private String name;
    private String description;

    public SmallItem(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    // EFFECTS: stores the information in this Small Item in json
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("description", description);
        return json;
    }
}
