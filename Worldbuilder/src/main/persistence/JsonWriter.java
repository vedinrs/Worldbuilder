package persistence;

import model.*;
import org.json.JSONObject;

import java.io.*;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    //          cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }
    // CITATION: from the open() method in JsonSerializationDemo

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Continent to file
    public void write(Continent c) {
        JSONObject json = c.toJson();
        saveToFile(json.toString(TAB));
    }
    // CITATION: from the writer() method in JsonSerializationDemo

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
    // CITATION: from the saveToFile() method in JsonSerializationDemo

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
    // CITATION: from the close() method in JsonSerializationDemo

}
