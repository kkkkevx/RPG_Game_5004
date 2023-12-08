package character;

import gear.*;

import java.util.ArrayList;

/**
 * The RPGCharacter interface represents a character in a role-playing game (RPG).
 * Implementing classes are expected to provide functionality for equipping gear,
 * obtaining and modifying base and total attack/defense statistics, managing the character's name,
 * checking available gear slots, comparing characters, and obtaining a string representation of the character.
 */
public interface RPGCharacter {

    /**
     * Equips the character with a piece of gear.
     *
     * @param equipment The gear to be equipped.
     */
    void equip(Gear equipment);

    /**
     * Gets the base attack statistic of the character.
     *
     * @return The base attack statistic.
     */
    int getBaseAttackStat();

    /**
     * Gets the base defense statistic of the character.
     *
     * @return The base defense statistic.
     */
    int getBaseDefenseStat();

    /**
     * Gets the total attack statistic of the character, including equipped gear.
     *
     * @return The total attack statistic.
     */
    int getTotalAttackStat();

    /**
     * Gets the total defense statistic of the character, including equipped gear.
     *
     * @return The total defense statistic.
     */
    int getTotalDefenseStat();

    /**
     * Gets the name of the character.
     *
     * @return The name of the character.
     */
    String getName();

    /**
     * Sets the name of the character.
     *
     * @param name The new name for the character.
     */
    void setName(String name);

    /**
     * Checks if the character has an available hand gear slot.
     *
     * @return True if there is an available hand gear slot, otherwise false.
     */
    boolean hasHandGearSlot();

    /**
     * Checks if the character has an available head gear slot.
     *
     * @return True if there is an available head gear slot, otherwise false.
     */
    boolean hasHeadGearSlot();

    /**
     * Checks if the character has an available foot gear slot.
     *
     * @return True if there is an available foot gear slot, otherwise false.
     */
    boolean hasFootGearSlot();

    /**
     * Compares the character to another RPGCharacter based on their damage calculation.
     *
     * @param player The other RPGCharacter to compare.
     * @return A positive value if the character inflicts more damage, negative if it takes more damage, and zero if equal.
     */
    int compareTo(RPGCharacter player);

    /**
     * Provides a string representation of the character.
     *
     * @return A string containing information about the character, including name, base, and total statistics.
     */
    String toString();

    /**
     * Gets the list of equipped foot gears.
     *
     * @return The list of equipped foot gears.
     */
    ArrayList<FootGear> getEquippedFootGears();

    /**
     * Gets the list of equipped hand gears.
     *
     * @return The list of equipped hand gears.
     */
    ArrayList<HandGear> getEquippedHandGears();

    /**
     * Gets the equipped head gear.
     *
     * @return The equipped head gear.
     */
    HeadGear getEquippedHeadGear();
}
