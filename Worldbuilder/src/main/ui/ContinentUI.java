package ui;

import model.Continent;
import model.LandFormation;

import java.util.Scanner;

public class ContinentUI {
    private final Scanner input = new Scanner(System.in);

    // EFFECTS: prints the name, description, and Land Formations of the Continent
    public void printInfo(Continent c) {
        System.out.println(c.getName());
        System.out.println(c.getDescription());
        printLandFormations(c);
    }

    // MODIFIES: Continent c
    // EFFECTS: renames the given continent
    public void rename(Continent c) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        c.setName(newName);
    }

    // MODIFIES: Continent c
    // EFFECTS: sets a new description for given continent
    public void newDescription(Continent c) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        c.setDescription(newDescription);
    }

    // EFFECTS: Prints all the land formations in the list passed by continent
    public void printLandFormations(Continent c) {
        System.out.println(c.getName() + "'s Land Formations:");
        for (int i = 0; i < c.getLandFormations().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(c.getLandFormations().get(i).getName());
        }
    }

    // MODIFIES: Continent c
    // EFFECTS: removes the land formation associated with a given input number
    public void removeLandFormation(Continent c) {
        System.out.println("Select which number you would like to remove:");
        printLandFormations(c);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > c.getLandFormations().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            c.removeLandFormation(choiceInt);
        }
        printLandFormations(c);
    }

    // EFFECTS: prints the name and description of a Land Formation if it exists within a Continent
    public void searchLandFormation(Continent c) {
        System.out.println("Enter the name of the Land Formation:");
        String search = input.nextLine();
        if (c.searchLandFormation(search) == null) {
            System.out.println("There is no Land Formation with that name.");
        } else {
            LandFormation lf = c.searchLandFormation(search);
            System.out.println(lf.getName());
            System.out.println(lf.getDescription());
        }
    }

    // EFFECTS: returns a Land Formation
    public LandFormation getLandFormation(Continent c) {
        System.out.println("Which Land Formation would you like to view?");
        printLandFormations(c);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > c.getLandFormations().size()) {
            return null;
        } else {
            return c.getLandFormations().get(choiceInt - 1);
        }

    }
}
