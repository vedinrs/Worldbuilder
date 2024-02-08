package ui;

import model.Character;

import java.util.Scanner;

public class CharacterUI {
    private Scanner input = new Scanner(System.in);

    public void makeCharacter() {
        System.out.println("Enter a name:");
        String characterName = input.nextLine();
        System.out.println("An occupation:");
        String characterOccupation = input.nextLine();
        if (characterOccupation.equals("Adventurer")) {
            System.out.println("What class of adventurer? ");
            String adventurerClass = input.nextLine();
            characterOccupation = "Adventurer" + adventurerClass;
        }
        System.out.println("And a description:");
        String characterDescription = input.nextLine();

        Character character = new Character(characterName, characterOccupation, characterDescription);
    }

    public void printInventory(Character c) {
        System.out.println(c.getName() + "'s Inventory:");
        for (int i = 0; i < c.getInventory().size(); i++) {
            System.out.print(i + 1);
            System.out.print(". ");
            System.out.println(c.getInventory().get(i).getName());
        }
    }

    public void removeFromInventory(Character c) {
        System.out.println("Select which number you would like to remove:");
        printInventory(c);
        String choice = input.nextLine();
        int choiceInt = 0;
        try {
            choiceInt = Integer.valueOf(choice);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        if (choiceInt < 1 || choiceInt > c.getInventory().size()) {
            System.out.println("Please enter a valid number.");
        } else {
            c.removeItem(choiceInt);
        }
    }
}
