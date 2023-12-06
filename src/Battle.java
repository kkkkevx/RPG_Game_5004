import gear.*;
import character.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private final RPGCharacter RPGCharacter1;
    private final RPGCharacter RPGCharacter2;
    private final List<Gear> availableItems;

    public Battle(RPGCharacter RPGCharacter1, RPGCharacter RPGCharacter2, List<Gear> availableItems) {
        this.RPGCharacter1 = RPGCharacter1;
        this.RPGCharacter2 = RPGCharacter2;
        this.availableItems = new ArrayList<>(availableItems);
    }

    public void startBattle() {
        for (int turn = 1; turn <= 10; turn++) {
            System.out.println("Turn " + turn + ":");
            pickItem(RPGCharacter1);
            pickItem(RPGCharacter2);
            System.out.println();
        }

        // Determine the winner based on damage calculation
        int damage1 = calculateDamage(RPGCharacter1, RPGCharacter2);
        int damage2 = calculateDamage(RPGCharacter2, RPGCharacter1);

        System.out.println("Player 1 has " + RPGCharacter1.getTotalAttackStat() +
                " attack and " + RPGCharacter1.getTotalDefenseStat() + " defense.");

        System.out.println("Player 2 has " + RPGCharacter2.getTotalAttackStat() +
                " attack and " + RPGCharacter2.getTotalDefenseStat() + " defense.");

        System.out.println("Battle ends with " + RPGCharacter1.getName() + " having " + damage1 + " units of damage and " +
                RPGCharacter2.getName() + " having " + damage2 + " units of damage.");

        if (damage1 > damage2) {
            System.out.println(RPGCharacter1.getName() + " wins!");
        } else if (damage2 > damage1) {
            System.out.println(RPGCharacter2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void pickItem(RPGCharacter RPGCharacter) {
        // Sort available items based on the specified rules
        sortItems(RPGCharacter);

        // Choose the first item from the sorted list
        Gear chosenItem = availableItems.get(0);
        System.out.println(RPGCharacter.getName() + " is picking up a piece of " + chosenItem.getClass().getSimpleName() +
                ": " + chosenItem.getName() + " -- defense strength: " + chosenItem.getDefenseStat() +
                ", attack strength: " + chosenItem.getAttackStat());

        // Equip the chosen item to the character
        RPGCharacter.equip(chosenItem);

        // Remove the chosen item from the available items
        availableItems.remove(chosenItem);
    }

    private void sortItems(RPGCharacter character) {
        availableItems.sort((item1, item2) -> {
            if (character.hasHeadGearSlot() && item1 instanceof HeadGear && item2 instanceof HeadGear) {
                return compareItems(item1, item2);
            } else if (character.hasHandGearSlot() && item1 instanceof HandGear && item2 instanceof HandGear) {
                return compareItems(item1, item2);
            } else if (character.hasFootGearSlot() && item1 instanceof FootGear && item2 instanceof FootGear) {
                return compareItems(item1, item2);
            } else {
                // If no specific slot available, compare items without considering slot preference
                return compareItems(item1, item2);
            }
        });
    }

    private int compareItems(Gear item1, Gear item2) {
        // Compare items based on attack strength, defense strength, and then randomly
        int attackComparison = Integer.compare(item2.getAttackStat(), item1.getAttackStat());
        if (attackComparison != 0) {
            return attackComparison;
        }

        int defenseComparison = Integer.compare(item2.getDefenseStat(), item1.getDefenseStat());
        if (defenseComparison != 0) {
            return defenseComparison;
        }

        // Randomly choose if there is still a tie
        return new Random().nextBoolean() ? -1 : 1;
    }

    private int calculateDamage(RPGCharacter attacker, RPGCharacter defender) {
        int damage = attacker.getTotalAttackStat() - defender.getTotalDefenseStat();
        return Math.max(0, damage);
    }

    public static void main(String[] args) {
        // Create characters
        RPGCharacter player1 = new RPGCharacterImpl("Player 1", 2, 2);
        RPGCharacter player2 = new RPGCharacterImpl("Player 2", 2, 2);

        // Create a list of available items
        List<Gear> availableItems = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            availableItems.add(new HeadGear("adj" + (i + 1), "Helmet", rand.nextInt(10)));
            availableItems.add(new HandGear("adj" + (i + 6), "Glove", rand.nextInt(10)));
            availableItems.add(new FootGear("adj" + (i + 11), "Boot", rand.nextInt(10), rand.nextInt(10)));
        }

        for(int i = 16; i < 21; i++) {
            if (rand.nextInt(3) == 0) {
                availableItems.add(new HeadGear("adj" + (i), "Helmet", rand.nextInt(10)));
            } else if (rand.nextInt(3) == 1) {
                availableItems.add(new HandGear("adj" + (i), "Glove", rand.nextInt(10)));
            } else {
                availableItems.add(new FootGear("adj" + (i), "Boot", rand.nextInt(10), rand.nextInt(10)));
            }
        }

        // Create a BattleDriver instance and start the battle
        Battle battleDriver = new Battle(player1, player2, availableItems);
        battleDriver.startBattle();

    }
}
