package persistence;

import model.*;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private final String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Continent from file and returns it
    //          throws an IO Exception if an error occurs while reading the file
    public Continent read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseContinent(jsonObject);
    }
    // CITATION: modified from the read() method in JsonSerializationDemo

    // EFFECTS: parses Continent from JSON object and returns it
    private Continent parseContinent(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        Continent c = new Continent(name, description);
        addLandFormations(c, jsonObject);
        return c;
    }
    // CITATION: modified from the parseWorkRoom() method in JsonSerializationDemo

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder sb = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> sb.append(s));
        }

        return sb.toString();
    }
    // CITATION: modified from the readFile() method in JsonSerializationDemo

    // MODIFIES: c
    // EFFECTS: parses Land Formations (plural) from JSON object and adds it to Continent
    private void addLandFormations(Continent c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Land Formations");
        for (Object json : jsonArray) {
            JSONObject nextLandFormation = (JSONObject)  json;
            addLandFormation(c, nextLandFormation);
        }
    }
    // CITATION: modified from the addThingies() method in JsonSerializationDemo

    // MODIFIES: c
    // EFFECTS: parses Land Formation from JSON object and adds it to Continent
    private void addLandFormation(Continent c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        LandFormation lf = new LandFormation(name, description);
        addSettlements(lf, jsonObject);
        c.addLandFormation(lf);
    }
    // CITATION: modified from the addThingy() method in JsonSerializationDemo

    // MODIFIES: lf
    // EFFECTS: parses Settlements (plural) from JSON object and adds it to Land Formation
    private void addSettlements(LandFormation lf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Settlements");
        for (Object json : jsonArray) {
            JSONObject nextSettlement = (JSONObject) json;
            addSettlement(lf, nextSettlement);
        }
    }

    // MODIFIES: lf
    // EFFECTS: parses Settlement from JSON object and adds it to Land Formation
    private void addSettlement(LandFormation lf, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        Settlement s = new Settlement(name, description);
        addLocations(s, jsonObject);
        lf.addSettlement(s);
    }

    // MODIFIES: s
    // EFFECTS: parses Locations (plural) from JSON object and adds it to Settlement
    private void addLocations(Settlement s, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Locations");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocation(s, nextLocation);
        }
    }

    // MODIFIES: s
    // EFFECTS: parses Location from JSON object and adds it to Settlement
    private void addLocation(Settlement s, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        Location l = new Location(name, description);
        addBigItems(l, jsonObject);
        s.addLocation(l);
    }

    // MODIFIES: l
    // EFFECTS: parses Big Items (plural) from JSON object and adds it to Location
    private void addBigItems(Location l, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Big Items");
        for (Object json : jsonArray) {
            JSONObject nextBigItem = (JSONObject) json;
            addBigItem(l, nextBigItem);
        }
    }

    // MODIFIES: l
    // EFFECTS: parses Big Item from JSON object and adds it to Location
    private void addBigItem(Location l, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        BigItem bi = new BigItem(name, description);
        addSmallItems(bi, jsonObject);
        l.addBigItems(bi);
    }

    // MODIFIES: bi
    // EFFECTS: parses Small Items (plural) from JSON object and adds it to Big Item
    private void addSmallItems(BigItem bi, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Small Items");
        for (Object json : jsonArray) {
            JSONObject nextSmallItem = (JSONObject) json;
            addSmallItem(bi, nextSmallItem);
        }
    }

    // MODIFIES: bi
    // EFFECTS: parses Small Item from JSON object and adds it to Big Item
    private void addSmallItem(BigItem bi, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        SmallItem si = new SmallItem(name, description);
        bi.addSmallItem(si);
    }
}
