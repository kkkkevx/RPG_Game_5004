package gear;


/**
 * The AbstractGear class represents a generic gear with common properties such as
 * prefix, name, attack stat, and defense stat. It also defines methods for creating
 * and combining gears.
 */
public abstract class AbstractGear implements Gear {

    // Common properties for all gears
    private String prefix;
    private String name;
    private int attackStat;
    private int defenseStat;

    /**
     * Constructs an AbstractGear with the specified prefix and name.
     *
     * @param prefix The prefix of the gear.
     * @param name   The name of the gear.
     */
    public AbstractGear(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    /**
     * Get the prefix of the gear.
     *
     * @return The prefix of the gear.
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * Set the prefix of the gear.
     *
     * @param prefix The new prefix for the gear.
     */
    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Get the name of the gear.
     *
     * @return The name of the gear.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set the name of the gear.
     *
     * @param name The new name for the gear.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the defense stat of the gear.
     *
     * @return The defense stat of the gear.
     */
    public int getDefenseStat() {
        return defenseStat;
    }

    /**
     * Set the defense stat of the gear.
     *
     * @param defenseStat The new defense stat for the gear.
     */
    public void setDefenseStat(int defenseStat) {
        this.defenseStat = defenseStat;
    }

    /**
     * Get the attack stat of the gear.
     *
     * @return The attack stat of the gear.
     */
    public int getAttackStat() {
        return attackStat;
    }

    /**
     * Set the attack stat of the gear.
     *
     * @param attackStat The new attack stat for the gear.
     */
    public void setAttackStat(int attackStat) {
        this.attackStat = attackStat;
    }

    /**
     * Abstract method for creating a new gear based on the given gear.
     *
     * @param gear The gear to be used as a base for the new gear.
     * @return A new gear created based on the provided gear.
     */
    public abstract Gear createGear(Gear gear);

    /**
     * Combines the current gear with the provided gear to create a new gear.
     *
     * @param gear The gear to be combined with the current gear.
     * @return The new gear created by combining the current gear with the provided gear.
     */
    public Gear combine(Gear gear) {
        return createGear(gear);
    }

    /**
     * Returns a string representation of the gear.
     *
     * @return A string representation of the gear.
     */
    @Override
    public String toString() {
        return "gear.AbstractGear{" +
                "prefix='" + prefix + '\'' +
                ", name='" + name + '\'' +
                ", attackStat=" + attackStat +
                ", defenseStat=" + defenseStat +
                '}';
    }
}
