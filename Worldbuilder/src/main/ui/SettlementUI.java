package ui;

import model.LandFormation;
import model.Location;
import model.Settlement;

import java.util.Scanner;

public class SettlementUI {
    private final Scanner input = new Scanner(System.in);

    // MODIFIES: LandFormation lf
    // EFFECTS: makes a new Settlement and adds it to a Land Formation
    public void makeSettlement(LandFormation lf) {
        System.out.println("To make a new Settlement, enter a name:");
        String name = input.nextLine();
        System.out.println("And a description:");
        String description = input.nextLine();

        Settlement settlement = new Settlement(name, description);
        lf.addSettlement(settlement);
    }

    // EFFECTS: prints the name, description, and Locations within given Settlement
    public void printInfo(Settlement s) {
        System.out.println(s.getName());
        System.out.println(s.getDescription());
        printLocations(s);
    }

    // MODIFIES: Settlement s
    // EFFECTS: renames a given Settlement
    public void rename(Settlement s) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        s.setName(newName);
    }

    // MODIFIES: Settlement s
    // EFFECTS: sets a new description for a given Settlement
    public void newDescription(Settlement s) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        s.setDescription(newDescription);
    }

    // EFFECTS: prints the Locations within a given Settlement
    public void printLocations(Settlement s) {
        System.out.println(s.getName() + "'s Locations:");
        for (int i = 0; i < s.getLocations().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(s.getLocations().get(i).getName());
        }
    }

    // MODIFIES: Settlement s
    // EFFECTS: takes in a valid number to remove a Location
    public void removeLocation(Settlement s) {
        System.out.println("Select which number you would like to remove:");
        printLocations(s);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > s.getLocations().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            s.removeLocation(choiceInt);
        }
    }

    // EFFECTS: returns a Location
    public Location getLocation(Settlement s) {
        System.out.println("Which Location would you like to view?");
        printLocations(s);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > s.getLocations().size()) {
            return null;
        } else {
            return s.getLocations().get(choiceInt - 1);
        }
    }


}
