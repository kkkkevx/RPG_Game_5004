package gear;

/**
 * The HeadGear class represents a type of gear that can be worn on the head.
 * It extends the AbstractGear class and includes specific logic for head gear.
 */
public class HeadGear extends AbstractGear {

    /**
     * Constructs a HeadGear with the specified prefix, name, and defense stat.
     *
     * @param prefix  The prefix of the head gear.
     * @param name    The name of the head gear.
     * @param defense The defense stat of the head gear.
     * @throws IllegalArgumentException if the defense stat is negative.
     */
    public HeadGear(String prefix, String name, int defense) {
        super(prefix, name);

        // Validate that the defense stat is non-negative
        if (defense < 0) {
            throw new IllegalArgumentException("Defense stat must be non-negative.");
        }

        // Set the defense stat for the head gear
        this.setDefenseStat(defense);
    }

    /**
     * Creates a new head gear by combining the current head gear with the provided gear.
     *
     * @param gear The gear to be combined with the current head gear.
     * @return A new head gear created by combining the current head gear with the provided gear.
     */
    @Override
    public Gear createGear(Gear gear) {
        // Combine the prefixes and calculate the new defense stat
        String newPrefix = this.getPrefix() + ", " + gear.getPrefix();
        int newDefenseStat = this.getDefenseStat() + gear.getDefenseStat();

        // Create and return a new HeadGear instance
        return new HeadGear(newPrefix, getName(), newDefenseStat);
    }

    @Override
    public String toString() {
        return super.toString() + " -- attack: " + getAttackStat() +
                ", defense: " + getDefenseStat();
    }
}
