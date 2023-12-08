package battle;

import gear.*;
import character.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The battle.Battle class represents a turn-based battle between two RPG characters.
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
     * Constructs a battle.Battle object with two RPG characters and a list of available items.
     *
     * @param RPGCharacter1  The first RPG character participating in the battle.
     * @param RPGCharacter2  The second RPG character participating in the battle.
     * @param availableItems The list of available items that characters can choose from during the battle.
     */
    public Battle(RPGCharacter RPGCharacter1, RPGCharacter RPGCharacter2, List<Gear> availableItems) {
        if (availableItems.size() != 20) {
            throw new IllegalArgumentException("Item Error: Make sure you create enough items to pick up: 20 items");
        }
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

    /**
     * Represents a single turn in which a character chooses an item to equip.
     *
     * @param RPGCharacter The RPG character whose turn it is to pick an item.
     */
    private void pickItem(RPGCharacter RPGCharacter) {
        // Sort available items based on the specified rules
        Gear chosenItem = findBestItem(RPGCharacter);

        System.out.println(RPGCharacter.getName() + " is picking up " + chosenItem.getClass().getSimpleName() +
                ": " + chosenItem.getPrefix() + " " + chosenItem.getName() +
                " -- defense strength: " + chosenItem.getDefenseStat() +
                ", attack strength: " + chosenItem.getAttackStat());

        // Equip the chosen item to the character
        RPGCharacter.equip(chosenItem);

        // Remove the chosen item from the available items
        availableItems.remove(chosenItem);
    }

    /**
     * Method   `chooseItem` determines which gear is to be chosen by the character
     *            during their turn, based on 1) whether that character has an open
     *            slot for a type of gear, 2) which gear has the highest attack,
     *            and 3) which gear has the highest defense. The gear chosen is
     *            equipped to the character and removed from itemsList. If itemsList
     *            is empty when method is called, throw an IllegalStateException.
     * @param   'inChar'      --    (RPGCharacter)    the character choosing the item
     * @returns  Gear         --                      the best gear
     */
    public Gear findBestItem(RPGCharacter inChar) {


        // Instantiate array lists to hold specific gear types
        List<Gear> headGearList = new ArrayList<>();
        List<Gear> handGearList = new ArrayList<>();
        List<Gear> footGearList = new ArrayList<>();

        // Parse through itemsList and copy gear to appropriate lists
        for (Gear gear : availableItems) {
            if (gear instanceof HeadGear) {
                headGearList.add(gear);
            } else if (gear instanceof HandGear) {
                handGearList.add(gear);
            } else {
                footGearList.add(gear);
            }
        }

        // Instantiate new list from which gear will be chosen
        List<Gear> candidatesList = new ArrayList<>();

        // If char has open head, hand, or foot slot, add that type's list
        // to the candidatesList

        if (inChar.hasHeadGearSlot()) {
            candidatesList.addAll(headGearList);
        }

        if (inChar.hasHandGearSlot()) {
            candidatesList.addAll(handGearList);
        }

        if (inChar.hasFootGearSlot()) {
            candidatesList.addAll(footGearList);
        }

        // If char has all slots already filled, add all items in itemsList to
        // candidatesList
        if (candidatesList.isEmpty()) {
            candidatesList.addAll(availableItems);
        }

        // Find the gear with the best stats in the candidatesList
        Gear maxStatsGear = candidatesList.get(0);
        for (Gear gear : candidatesList) {
            if (gear.compareTo(maxStatsGear) > 0) {
                maxStatsGear = gear;
            }
        }
        return maxStatsGear;

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

    public int getWinner(int damage1, int damage2) {
        if (damage1 > damage2) {
            System.out.println(RPGCharacter1.getName() + " wins!");
            return 1;
        } else if (damage2 > damage1) {
            System.out.println(RPGCharacter2.getName() + " wins!");
            return 2;
        } else {
            System.out.println("It's a tie!");
            return 0;
        }
    }

}

