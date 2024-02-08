package ui;

import model.BigItem;
import model.SmallItem;

import java.util.Scanner;

public class SmallItemUI {
    private final Scanner input = new Scanner(System.in);

    // MODIFIES: BigItem bi
    // EFFECTS: makes a new Small Item and adds it to Big Item
    public void makeSmallItem(BigItem bi) {
        System.out.println("To make a new Small Item, enter a name:");
        String name = input.nextLine();
        System.out.println("And a description:");
        String description = input.nextLine();

        SmallItem si = new SmallItem(name, description);
        bi.addSmallItem(si);
    }

    // EFFECTS: prints the name and description of a BigItem
    public void printInfo(SmallItem si) {
        System.out.println(si.getName());
        System.out.println(si.getDescription());
    }

    // MODIFIES: SmallItem si
    // EFFECTS: renames a given SmallItem
    public void rename(SmallItem si) {
        System.out.println("Enter a new name:");
        String newName = input.nextLine();
        si.setName(newName);
    }

    // MODIFIES: SmallItem si
    // EFFECTS: sets a new description for a given SmallItem
    public void newDescription(SmallItem si) {
        System.out.println("Enter a new description:");
        String newDescription = input.nextLine();
        si.setDescription(newDescription);
    }
}
