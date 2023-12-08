package gear;

/**
 * The Gear interface represents an abstraction for items that can be equipped by characters in a role-playing game (RPG).
 * Implementing classes are expected to provide functionality for obtaining and modifying properties of the gear,
 * such as prefix, name, attack and defense statistics, and the ability to combine with other gear.
 */
public interface Gear{

    /**
     * Gets the prefix of the gear.
     *
     * @return The prefix of the gear.
     */
    String getPrefix();

    /**
     * Sets the prefix of the gear.
     *
     * @param prefix The new prefix for the gear.
     */
    void setPrefix(String prefix);

    /**
     * Gets the name of the gear.
     *
     * @return The name of the gear.
     */
    String getName();

    /**
     * Sets the name of the gear.
     *
     * @param name The new name for the gear.
     */
    void setName(String name);

    /**
     * Gets the attack statistic of the gear.
     *
     * @return The attack statistic of the gear.
     */
    int getAttackStat();

    /**
     * Gets the defense statistic of the gear.
     *
     * @return The defense statistic of the gear.
     */
    int getDefenseStat();

    /**
     * Combines the current gear with another gear.
     *
     * @param other The other gear to combine with.
     * @return A new gear resulting from the combination.
     */
    Gear combine(Gear other);

    /**
     * Provides a string representation of the gear.
     *
     * @return A string containing information about the gear, including prefix, name, attack, and defense statistics.
     */
    String toString();
    int compareTo(Gear other);
}
