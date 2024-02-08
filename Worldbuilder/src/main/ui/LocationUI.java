package ui;

import model.BigItem;
import model.Settlement;
import model.Location;

import java.util.Scanner;

public class LocationUI {
    private final Scanner input = new Scanner(System.in);

    // MODIFIES: Settlement s
    // EFFECTS: makes a Location within a given Settlement
    public void makeLocation(Settlement s) {
        System.out.println("To make a new Location, enter a name:");
        String name = input.nextLine();
        System.out.println("And a description:");
        String description = input.nextLine();

        Location location = new Location(name, description);
        s.addLocation(location);
    }

    // EFFECTS: prints the name, description, Big Items, and Small Items within a given Location
    public void printInfo(Location l) {
        System.out.println(l.getName());
        System.out.println(l.getDescription());
        printBigItems(l);
        // printSmallItems(l); TO BE DONE AT A LATER DATE
    }

    // MODIFIES: Location l
    // EFFECTS: renames a given Location
    public void rename(Location l) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        l.setName(newName);
    }

    // MODIFIES: Location l
    // EFFECTS: sets a new description for given Location l
    public void newDescription(Location l) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        l.setDescription(newDescription);
    }

    // EFFECTS: prints the Big Items in a given Location
    public void printBigItems(Location l) {
        System.out.println(l.getName() + "'s Big Items:");
        for (int i = 0; i < l.getBigItems().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(l.getBigItems().get(i).getName());
        }
    }

    // MODIFIES: Location l
    // EFFECTS: takes in a valid number to remove a Big Item
    public void removeBigItem(Location l) {
        System.out.println("Select which number you would like to remove:");
        printBigItems(l);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > l.getBigItems().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            l.removeBigItem(choiceInt);
        }
    }

    // EFFECTS: returns a Big Item
    public BigItem getBigItem(Location l) {
        System.out.println("Which Big Item would you like to view?");
        printBigItems(l);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > l.getBigItems().size()) {
            return null;
        } else {
            return l.getBigItems().get(choiceInt - 1);
        }
    }


    // TO BE DONE AT A LATER DATE:
//
//    // EFFECTS: prints the Small Items in a given Location
//    public void printSmallItems(Location l) {
//        System.out.println(l.getName() + "'s Small Items:");
//        for (int i = 0; i < l.getSmallItems().size(); i++) {
//            System.out.print(i + 1);
//            System.out.print(". ");
//            System.out.println(l.getSmallItems().get(i).getName());
//        }
//    }
//
//    // MODIFIES: Location l
//    // EFFECTS: takes in a valid number to remove a Small Item
//    public void removeSmallItem(Location l) {
//        System.out.println("Select which number you would like to remove:");
//        printSmallItems(l);
//        String choice = input.nextLine();
//        int choiceInt = 0;
//        try {
//            choiceInt = Integer.parseInt(choice);
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number.");
//        }
//        if (choiceInt < 1 || choiceInt > l.getSmallItems().size()) {
//            System.out.println("Please enter a valid number.");
//        } else {
//            l.removeSmallItem(choiceInt);
//        }
//    }

}
