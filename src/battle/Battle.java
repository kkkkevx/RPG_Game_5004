package battle;

import gear.*;
import character.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Battle class represents a turn-based battle between two RPG characters.
 * During the battle, characters take turns choosing items from a list of available items,
 * and the winner is determined based on the calculated damage at the end of the battle.
 */
public class Battle {

    // RPGCharacter1 and RPGCharacter2 represent the two characters in the battle.
    private final RPGCharacter RPGCharacter1;
    private final RPGCharacter RPGCharacter2;

    // availableItems is the list of items characters can choose from during the battle.
    private final List<Gear> availableItems;

    /**
     * Constructs a Battle object with two RPG characters and a list of available items.
     *
     * @param RPGCharacter1 The first RPG character participating in the battle.
     * @param RPGCharacter2 The second RPG character participating in the battle.
     * @param availableItems The list of available items that characters can choose from during the battle.
     */
    public Battle(RPGCharacter RPGCharacter1, RPGCharacter RPGCharacter2, List<Gear> availableItems) {
        this.RPGCharacter1 = RPGCharacter1;
        this.RPGCharacter2 = RPGCharacter2;
        this.availableItems = new ArrayList<>(availableItems);
    }

    /**
     * Starts the battle, where characters take turns choosing items for a specified number of turns.
     * After the turns, the winner is determined based on the calculated damage.
     */
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

        System.out.println("Battle.Battle ends with " + RPGCharacter1.getName() + " having " + damage1 + " units of damage and " +
                RPGCharacter2.getName() + " having " + damage2 + " units of damage.");

        if (damage1 > damage2) {
            System.out.println(RPGCharacter1.getName() + " wins!");
        } else if (damage2 > damage1) {
            System.out.println(RPGCharacter2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Represents a single turn in which a character chooses an item to equip.
     *
     * @param RPGCharacter The RPG character whose turn it is to pick an item.
     */
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

    /**
     * Sorts the available items based on specified rules, prioritizing the type of items
     * characters have available slots for and considering attack and defense strengths.
     *
     * @param character The RPG character for whom the items are being sorted.
     */
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

    /**
     * Compares two items based on attack strength, defense strength, and randomness in case of a tie.
     *
     * @param item1 The first item to compare.
     * @param item2 The second item to compare.
     * @return A negative integer, zero, or a positive integer as the first item is less than, equal to,
     *         or greater than the second item, respectively.
     */
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

    /**
     * Calculates the damage inflicted by an attacker on a defender based on their stats.
     *
     * @param attacker The attacking RPG character.
     * @param defender The defending RPG character.
     * @return The calculated damage inflicted by the attacker on the defender.
     */
    private int calculateDamage(RPGCharacter attacker, RPGCharacter defender) {
        int damage = attacker.getTotalAttackStat() - defender.getTotalDefenseStat();
        // Ensure damage is non-negative
        return Math.max(0, damage);
    }
}

