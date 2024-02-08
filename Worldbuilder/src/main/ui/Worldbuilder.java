package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Worldbuilder {
    private Continent continent;
    private final Scanner input;
    private static final String JSON_STORE = "./data/worldbuilder.json";
    private final JsonWriter writer;
    private final JsonReader reader;

    // UI classes -- START
    private final ContinentUI continentUI = new ContinentUI();
    private final LandFormationUI landFormationUI = new LandFormationUI();
    private final SettlementUI settlementUI = new SettlementUI();
    private final LocationUI locationUI = new LocationUI();
    private final BigItemUI bigItemUI = new BigItemUI();
    private final SmallItemUI smallItemUI = new SmallItemUI();
    // UI classes -- END

    // Booleans for while loops -- START
    private Boolean run = true;
    private Boolean runLandFormation = false;
    private Boolean runSettlement = false;
    private Boolean runLocation = false;
    private Boolean runBigItem = false;
    private Boolean runSmallItem = false;
    // Booleans for while loops -- END

    // Long strings that give info -- START
    private static final String WRONG_CHOICE = "You have entered an invalid choice.\n";
    private static final String RENAME_TEXT = "\nSelect 1 to rename\n";
    private static final String NEW_DESCRIPTION_TEXT = "Select 2 to make a new description";
    private static final String ADD_TEXT = "\nSelect 3 to add a ";
    private static final String REMOVE_TEXT = "\nSelect 4 to remove a ";
    private static final String VIEW_TEXT = "\nSelect 5 to view a ";
    private static final String BACK_TEXT = "\nType 'back' to go back";
    // Long strings that give info -- END

    // EFFECTS: constructs Worldbuilder and starts application
    public Worldbuilder() throws FileNotFoundException {
        input = new Scanner(System.in);
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        start();
    }

    // EFFECTS: starts the program by making a continent, then continuously calling functions
    public void start() throws FileNotFoundException {
        System.out.println("Hello Worldbuilder!\n");
        loadOption();

        while (run) {
            continentChoice();
        }
    }

    // EFFECTS: prompts the user if they would like to load a Continent
    public void loadOption() {
        System.out.println("Would you like to load a Continent?\nEnter 'y' or 'n'");
        String load = input.nextLine();
        if (load.equals("y")) {
            loadWorldbuilder();
        } else {
            continentSetUp();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Worldbuilder from file
    public void loadWorldbuilder() {
        try {
            continent = reader.read();
            System.out.println("Loading " + continent.getName() + " from " + JSON_STORE + "\n");
        } catch (IOException e) {
            System.out.println("Unable to read file: " + JSON_STORE + "\n");
        }

    }
    // CITATION: modified from loadWorkRoom() method in JsonSerializationDemo

    // EFFECTS: prompts the user if they would like to save their Continent
    public void saveOption() {
        System.out.println("\nWould you like to save this Continent?\nEnter 'y' to save or another key to exit "
                 + "without saving");
        String load = input.nextLine();
        if (load.equals("y")) {
            saveWorldbuilder();
        }
        run = false;
    }

    // EFFECTS: saves Worldbuilder to file
    public void saveWorldbuilder() {
        try {
            writer.open();
            writer.write(continent);
            writer.close();
            System.out.println("Saved " + continent.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
    // CITATION: modifies from saveWorkRoom() method in JsonSerializationDemo


    // EFFECTS: Creates the continent every entity is ultimately added to
    public void continentSetUp() {
        System.out.println("Enter your continent's name: ");
        String continentName = input.nextLine();
        System.out.println("Enter your continent's description: ");
        String continentDescription = input.nextLine();

        continent = new Continent(continentName, continentDescription);
        System.out.println("Welcome to " + continentName + "!\n" + continentDescription + "\n");
    }

    // EFFECTS: displays the choices you can make while viewing a Continent
    public void continentChoice() {
        continentUI.printInfo(continent);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + ADD_TEXT + "Land Formation" + REMOVE_TEXT
                + "Land Formation" + "\nSelect 5 to search for a Land Formation\nSelect 6 to view a Land Formation\n"
                + "Type 'quit' to quit");
        String ans = input.nextLine();
        if (ans.equals("1")) {
            continentUI.rename(continent);
        } else if (ans.equals("2")) {
            continentUI.newDescription(continent);
        } else if (ans.equals("3")) {
            landFormationUI.makeLandFormation(continent);
        } else if (ans.equals("4")) {
            continentUI.removeLandFormation(continent);
        } else if (ans.equals("5")) {
            continentUI.searchLandFormation(continent);
        } else if (ans.equals("6")) {
            viewLandFormation(continent);
        } else if (ans.equals("quit")) {
            saveOption();
        } else {
            System.out.println(WRONG_CHOICE + "\n");
        }
    }

    // gets the correct Land Formation to then be viewed
    public void viewLandFormation(Continent c) {
        LandFormation lf = continentUI.getLandFormation(c);
        if (lf != null) {
            runLandFormation = true;
            while (runLandFormation) {
                landFormationChoice(lf);
            }
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: displays the choices you can make while viewing a Land Formation
    public void landFormationChoice(LandFormation lf) {
        landFormationUI.printInfo(lf);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + ADD_TEXT + "Settlement" + REMOVE_TEXT
                +  "Settlement" + VIEW_TEXT + "Settlement" + BACK_TEXT);
        String ans = input.nextLine();
        if (ans.equals("1")) {
            landFormationUI.rename(lf);
        } else if (ans.equals("2")) {
            landFormationUI.newDescription(lf);
        } else if (ans.equals("3")) {
            settlementUI.makeSettlement(lf);
        } else if (ans.equals("4")) {
            landFormationUI.removeSettlement(lf);
        } else if (ans.equals("5")) {
            viewSettlement(lf);
        } else if (ans.equals("back")) {
            runLandFormation = false;
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: gets the correct Settlement to then be viewed
    public void viewSettlement(LandFormation lf) {
        Settlement s = landFormationUI.getSettlement(lf);
        if (s != null) {
            runSettlement = true;
            while (runSettlement) {
                settlementChoice(s);
            }
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: displays the choices you can make while viewing a Settlement
    public void settlementChoice(Settlement s) {
        settlementUI.printInfo(s);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + ADD_TEXT + "Location" + REMOVE_TEXT
                +  "Location" + VIEW_TEXT + "Location" + BACK_TEXT);
        String ans = input.nextLine();
        if (ans.equals("1")) {
            settlementUI.rename(s);
        } else if (ans.equals("2")) {
            settlementUI.newDescription(s);
        } else if (ans.equals("3")) {
            locationUI.makeLocation(s);
        } else if (ans.equals("4")) {
            settlementUI.removeLocation(s);
        } else if (ans.equals("5")) {
            viewLocation(s);
        } else if (ans.equals("back")) {
            runSettlement = false;
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: gets the correct Location to then be viewed
    public void viewLocation(Settlement s) {
        Location l = settlementUI.getLocation(s);
        if (l != null) {
            runLocation = true;
            while (runLocation) {
                locationChoice(l);
            }
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: displays the choices you can make while viewing a Location
    public void locationChoice(Location l) {
        locationUI.printInfo(l);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + ADD_TEXT + "Big Item" + REMOVE_TEXT
                +  "Big Item" + VIEW_TEXT + "Big Item" + BACK_TEXT);
        String ans = input.nextLine();
        if (ans.equals("1")) {
            locationUI.rename(l);
        } else if (ans.equals("2")) {
            locationUI.newDescription(l);
        } else if (ans.equals("3")) {
            bigItemUI.makeBigItem(l);
        } else if (ans.equals("4")) {
            locationUI.removeBigItem(l);
        } else if (ans.equals("5")) {
            viewBigItem(l);
        } else if (ans.equals("back")) {
            runLocation = false;
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: gets the correct Big Item to be viewed
    public void viewBigItem(Location l) {
        BigItem bi = locationUI.getBigItem(l);
        if (bi != null) {
            runBigItem = true;
            while (runBigItem) {
                bigItemChoice(bi);
            }
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: displays the choices you can make while viewing a Big Item
    public void bigItemChoice(BigItem bi) {
        bigItemUI.printInfo(bi);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + ADD_TEXT + "Small Item" + REMOVE_TEXT
                +  "Small Item" + VIEW_TEXT + "Small Item" + BACK_TEXT);
        String ans = input.nextLine();
        if (ans.equals("1")) {
            bigItemUI.rename(bi);
        } else if (ans.equals("2")) {
            bigItemUI.newDescription(bi);
        } else if (ans.equals("3")) {
            smallItemUI.makeSmallItem(bi);
        } else if (ans.equals("4")) {
            bigItemUI.removeSmallItem(bi);
        } else if (ans.equals("5")) {
            viewSmallItem(bi);
        } else if (ans.equals("back")) {
            runBigItem = false;
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: gets the correct Small Item to then be viewed
    public void viewSmallItem(BigItem bi) {
        SmallItem si = bigItemUI.getSmallItem(bi);
        if (si != null) {
            runSmallItem = true;
            while (runSmallItem) {
                smallItemChoice(si);
            }
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

    // EFFECTS: displays the choices you can make while viewing a Small Item
    public void smallItemChoice(SmallItem si) {
        smallItemUI.printInfo(si);
        System.out.println(RENAME_TEXT + NEW_DESCRIPTION_TEXT + BACK_TEXT);
        String choice = input.nextLine();
        if (choice.equals("1")) {
            smallItemUI.rename(si);
        } else if (choice.equals("2")) {
            smallItemUI.newDescription(si);
        } else if (choice.equals("back")) {
            runSmallItem = false;
        } else {
            System.out.println(WRONG_CHOICE);
        }
    }

}
