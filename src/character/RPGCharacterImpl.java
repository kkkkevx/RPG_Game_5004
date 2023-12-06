package character;

import gear.*;

import java.util.ArrayList;

/**
 * Implementation of the RPGCharacter interface representing a character in a role-playing game (RPG).
 */
public class RPGCharacterImpl implements RPGCharacter {

    // Constants defining the maximum number of gear slots for hand and foot
    private static final int Max_Hand_Gear_Slot = 2;
    private static final int Max_Foot_Gear_Slot = 2;

    // Character attributes
    private String name;
    private final int baseAttackStat;
    private final int baseDefenseStat;
    private int totalAttackStat;
    private int totalDefenseStat;

    // Equipped gear
    private HeadGear equippedHeadGear;
    private ArrayList<HandGear> equippedHandGears;
    private ArrayList<FootGear> equippedFootGears;

    /**
     * Constructor for creating an RPGCharacter with a specified name, base attack stat, and base defense stat.
     *
     * @param name             The name of the character.
     * @param baseAttackStat   The base attack stat of the character.
     * @param baseDefenseStat  The base defense stat of the character.
     */
    public RPGCharacterImpl(String name, int baseAttackStat, int baseDefenseStat) {
        this.name = name;
        this.baseAttackStat = baseAttackStat;
        this.baseDefenseStat = baseDefenseStat;
        this.equippedFootGears = new ArrayList<>(Max_Foot_Gear_Slot);
        this.equippedHandGears = new ArrayList<>(Max_Hand_Gear_Slot);
        totalAttackStat = baseAttackStat;
        totalDefenseStat = baseDefenseStat;
    }

    /**
     * Equips the character with a piece of gear. The gear is equipped based on the type of gear and available slots.
     * If no slots are available, the gear is combined with the existing gear.
     *
     * @param equipment The gear to be equipped.
     */
    @Override
    public void equip(Gear equipment) {
        // If gear is headgear
        if (equipment instanceof HeadGear) {
            if (hasHeadGearSlot()) {
                equippedHeadGear = (HeadGear) equipment;
            } else {
                equippedHeadGear = (HeadGear) equippedHeadGear.combine(equipment);
            }
        }
        // If gear is handgear
        if (equipment instanceof HandGear) {
            if (hasHandGearSlot()) {
                equippedHandGears.add((HandGear) equipment);
            } else {
                HandGear newItem = (HandGear) equippedHandGears.get(0).combine(equipment);
                equippedHandGears.set(0, newItem);
            }
        }
        // If gear is footgear
        if (equipment instanceof FootGear) {
            if (hasFootGearSlot()) {
                equippedFootGears.add((FootGear) equipment);
            } else {
                FootGear newItem = (FootGear) equippedFootGears.get(0).combine(equipment);
                equippedFootGears.set(0, newItem);
            }
        }
        totalDefenseStat += equipment.getDefenseStat();
        totalAttackStat += equipment.getAttackStat();
    }

    /**
     * Gets the base attack stat of the character.
     *
     * @return The base attack stat.
     */
    @Override
    public int getBaseAttackStat() {
        return baseAttackStat;
    }

    /**
     * Gets the base defense stat of the character.
     *
     * @return The base defense stat.
     */
    @Override
    public int getBaseDefenseStat() {
        return baseDefenseStat;
    }


    /**
     * Retrieves the currently equipped headgear of the RPGCharacter.
     *
     * @return The HeadGear object representing the equipped headgear.
     */
    public HeadGear getEquippedHeadGear() {
        return equippedHeadGear;
    }

    /**
     * Retrieves the list of currently equipped handgears of the RPGCharacter.
     *
     * @return An ArrayList of HandGear objects representing the equipped handgears.
     */
    public ArrayList<HandGear> getEquippedHandGears() {
        return equippedHandGears;
    }

    /**
     * Retrieves the list of currently equipped footgears of the RPGCharacter.
     *
     * @return An ArrayList of FootGear objects representing the equipped footgears.
     */
    public ArrayList<FootGear> getEquippedFootGears() {
        return equippedFootGears;
    }


    /**
     * Gets the total attack stat of the character.
     *
     * @return The total attack stat.
     */
    @Override
    public int getTotalAttackStat() {
        return totalAttackStat;
    }

    /**
     * Gets the total defense stat of the character.
     *
     * @return The total defense stat.
     */
    @Override
    public int getTotalDefenseStat() {
        return totalDefenseStat;
    }

    /**
     * Gets the name of the character.
     *
     * @return The name of the character.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the character.
     *
     * @param name The new name for the character.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the character has available hand gear slots.
     *
     * @return True if there are available hand gear slots, false otherwise.
     */
    @Override
    public boolean hasHandGearSlot() {
        return equippedHandGears.size() < Max_Hand_Gear_Slot;
    }

    /**
     * Checks if the character has an available head gear slot.
     *
     * @return True if there is an available head gear slot, false otherwise.
     */
    @Override
    public boolean hasHeadGearSlot() {
        return equippedHeadGear == null;
    }

    /**
     * Checks if the character has available foot gear slots.
     *
     * @return True if there are available foot gear slots, false otherwise.
     */
    @Override
    public boolean hasFootGearSlot() {
        return equippedFootGears.size() < Max_Foot_Gear_Slot;
    }

/**
 * Compares two RPG characters based on the damage made and taken during a battle.
 * The comparison is made by subtracting the total defense stat of the opponent from the total attack
 * stat of the current character, and vice versa. The result is then compared to determine the winner.
 *
 * @param player The RPGCharacter to compare against.
 * @return An integer representing the comparison result:
 *         - Negative if the current character received more damage.
 *         - Positive if the current character dealt more damage.
 *         - Zero if both characters dealt and received the same amount of damage.
 */
@Override
public int compareTo(RPGCharacter player) {
    int dmgMade = this.totalAttackStat - player.getTotalDefenseStat();
    int dmgTaken = player.getTotalAttackStat() - this.totalDefenseStat;
    return Integer.compare(dmgMade, dmgTaken);
}

    /**
     * Returns a string representation of the RPGCharacter, including its name, base stats, total stats,
     * and equipped gear details.
     *
     * @return A string representation of the RPGCharacter.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Character: \n");
        result.append("name='").append(name).append("'\n");
        result.append("baseAttackStat=").append(baseAttackStat).append("\n");
        result.append("baseDefenseStat=").append(baseDefenseStat).append("\n");
        result.append("totalAttackStat=").append(totalAttackStat).append("\n");
        result.append("totalDefenseStat=").append(totalDefenseStat).append("\n");

        // Check if there is equippedHeadGear and append details
        if (equippedHeadGear != null) {
            result.append("equippedHeadGear: ").append(equippedHeadGear).append("\n");
        } else {
            result.append("equippedHeadGear: null\n");
        }

        // Check if there are equippedHandGears and append details
        if (!equippedHandGears.isEmpty()) {
            for (HandGear handGear : equippedHandGears) {
                result.append("equippedHandGear: ").append(handGear).append("\n");
            }
        } else {
            result.append("equippedHandGears: null\n");
        }

        // Check if there are equippedFootGears and append details
        if (!equippedFootGears.isEmpty()) {
            for (FootGear footGear : equippedFootGears) {
                result.append("equippedFootGear: ").append(footGear).append("\n");
            }
        } else {
            result.append("equippedFootGears: null\n");
        }

        result.append("**************************************************************");
        return result.toString();
    }
}
