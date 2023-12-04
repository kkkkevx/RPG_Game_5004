package gear;

/**
 * The FootGear class represents a type of gear that can be worn on the feet.
 * It extends the AbstractGear class and includes specific logic for foot gear.
 */
public class FootGear extends AbstractGear {

    /**
     * Constructs a FootGear with the specified prefix, name, attack, and defense stats.
     *
     * @param prefix The prefix of the foot gear.
     * @param name   The name of the foot gear.
     * @param attack The attack stat of the foot gear.
     * @param defense The defense stat of the foot gear.
     * @throws IllegalArgumentException if attack or defense stats are negative.
     */
    public FootGear(String prefix, String name, int attack, int defense) {
        super(prefix, name);

        // Validate that attack and defense stats are non-negative
        if (attack < 0 || defense < 0) {
            throw new IllegalArgumentException("Attack and defense stats must be non-negative.");
        }

        // Set the attack and defense stats for the foot gear
        this.setAttackStat(attack);
        this.setDefenseStat(defense);
    }

    /**
     * Creates a new foot gear by combining the current foot gear with the provided gear.
     *
     * @param gear The gear to be combined with the current foot gear.
     * @return A new foot gear created by combining the current foot gear with the provided gear.
     */
    @Override
    public Gear createGear(Gear gear) {
        // Combine the prefixes and calculate the new attack and defense stats
        String newPrefix = this.getPrefix() + ", " + gear.getPrefix();
        int newDefenseStat = this.getDefenseStat() + gear.getDefenseStat();
        int newAttackStat = this.getAttackStat() + gear.getAttackStat();

        // Create and return a new FootGear instance
        return new FootGear(newPrefix, getName(), newAttackStat, newDefenseStat);
    }

    @Override
    public String toString() {
        return super.toString() + " -- attack: " + getAttackStat() +
                ", defense: " + getDefenseStat();
    }
}

