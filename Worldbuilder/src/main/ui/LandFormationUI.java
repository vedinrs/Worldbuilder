package ui;

import model.Continent;
import model.LandFormation;
import model.Settlement;

import java.util.Scanner;

public class LandFormationUI {
    private final Scanner input = new Scanner(System.in);

    // MODIFIES: Continent c
    // EFFECTS: makes a new Land Formation with given name and description
    public void makeLandFormation(Continent c) {
        System.out.println("To make a new Land Formation, enter a name:");
        String landFormationName = input.nextLine();
        System.out.println("And a description:");
        String landFormationDescription = input.nextLine();

        LandFormation landFormation = new LandFormation(landFormationName, landFormationDescription);
        c.addLandFormation(landFormation);
    }

    // EFFECTS: prints the name, description, Settlements, and Locations of given Land Formation
    public void printInfo(LandFormation lf) {
        System.out.println(lf.getName());
        System.out.println(lf.getDescription());
        printSettlements(lf);
        // printLocations(lf); TO BE DONE AT A LATER DATE
    }

    // MODIFIES: LandFormation lf
    // EFFECTS: renames given Land Formation
    public void rename(LandFormation lf) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        lf.setName(newName);
    }

    // MODIFIES: LandFormation lf
    // EFFECTS: sets a new description for given Land Formation
    public void newDescription(LandFormation lf) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        lf.setDescription(newDescription);
    }

    // EFFECTS: prints the Settlements in given Land Formation
    public void printSettlements(LandFormation lf) {
        System.out.println(lf.getName() + "'s Settlements:");
        for (int i = 0; i < lf.getSettlements().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(lf.getSettlements().get(i).getName());
        }
    }

    // MODIFIES: LandFormation lf
    // EFFECTS: takes in a valid number to remove a Settlement
    public void removeSettlement(LandFormation lf) {
        System.out.println("Select which number you would like to remove:");
        printSettlements(lf);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > lf.getSettlements().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            lf.removeSettlement(choiceInt);
        }
    }

    // EFFECTS: returns a Settlement
    public Settlement getSettlement(LandFormation lf) {
        System.out.println("Which Settlement would you like to view?");
        printSettlements(lf);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > lf.getSettlements().size()) {
            return null;
        } else {
            return lf.getSettlements().get(choiceInt - 1);
        }
    }


    // TO BE DONE AT A LATER DATE:

//    // EFFECTS: prints the Locations within given Land Formation
//    public void printLocations(LandFormation lf) {
//        System.out.println(lf.getName() + "'s Locations:");
//        for (int i = 0; i < lf.getLocations().size(); i++) {
//            System.out.print(i + 1);
//            System.out.print(". ");
//            System.out.println(lf.getLocations().get(i).getName());
//        }
//    }


//    // MODIFIES: LandFormation lf
//    // EFFECTS: takes in a valid number to remove a Location
//    public void removeLocation(LandFormation lf) {
//        System.out.println("Select which number you would like to remove:");
//        printLocations(lf);
//        String choice = input.nextLine();
//        int choiceInt = 0;
//        try {
//            choiceInt = Integer.parseInt(choice);
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number.");
//        }
//        if (choiceInt < 1 || choiceInt > lf.getLocations().size()) {
//            System.out.println("Please enter a valid number.");
//        } else {
//            lf.removeLocation(choiceInt);
//        }
//    }


}
