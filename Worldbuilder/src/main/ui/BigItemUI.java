package ui;

import model.BigItem;
import model.Location;
import model.SmallItem;

import java.util.Scanner;

public class BigItemUI {
    private final Scanner input = new Scanner(System.in);

    // MODIFIES: Location l
    // EFFECTS: makes a big item and adds it to given Location
    public void makeBigItem(Location l) {
        System.out.println("To make a new Big Item, enter a name:");
        String bigItemName = input.nextLine();
        System.out.println("And a description:");
        String bigItemDescription = input.nextLine();

        BigItem bigItem = new BigItem(bigItemName, bigItemDescription);
        l.addBigItems(bigItem);
    }

    // EFFECTS: prints name, description, and list of Small Items
    public void printInfo(BigItem bi) {
        System.out.println(bi.getName());
        System.out.println(bi.getDescription());
        printSmallItems(bi);
    }

    // MODIFIES: BigItem bi
    // EFFECTS: renames a given Big Item
    public void rename(BigItem bi) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        bi.setName(newName);
    }

    // MODIFIES: BigItem bi
    // EFFECTS: sets a new description for a given Big Item
    public void newDescription(BigItem bi) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        bi.setDescription(newDescription);
    }

    // EFFECTS: prints all the small items contained within a given big item
    public void printSmallItems(BigItem bi) {
        System.out.println(bi.getName() + "'s Small Items:");
        for (int i = 0; i < bi.getSmallItems().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(bi.getSmallItems().get(i).getName());
        }
    }

    // MODIFIES: BigItem bi
    // EFFECTS: removes a specific item from a given big item
    public void removeSmallItem(BigItem bi) {
        System.out.println("Select which number you would like to remove:");
        printSmallItems(bi);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > bi.getSmallItems().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            bi.removeSmallItem(choiceInt);
        }
        printSmallItems(bi);
    }

    // EFFECTS: returns a Small Item
    public SmallItem getSmallItem(BigItem bi) {
        System.out.println("Which Small Item would you like to view?");
        printSmallItems(bi);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > bi.getSmallItems().size()) {
            return null;
        } else {
            return bi.getSmallItems().get(choiceInt - 1);
        }
    }
}
