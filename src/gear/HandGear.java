package gear;

/**
 * The HandGear class represents a type of gear that can be worn on the hands.
 * It extends the AbstractGear class and includes specific logic for hand gear.
 */
public class HandGear extends AbstractGear {

    /**
     * Constructs a HandGear with the specified prefix, name, and attack stat.
     *
     * @param prefix The prefix of the hand gear.
     * @param name   The name of the hand gear.
     * @param attack The attack stat of the hand gear.
     * @throws IllegalArgumentException if the attack stat is negative.
     */
    public HandGear(String prefix, String name, int attack) {
        super(prefix, name);

        // Validate that the attack stat is non-negative
        if (attack < 0) {
            throw new IllegalArgumentException("Attack stat must be non-negative.");
        }

        // Set the attack stat for the hand gear
        this.setAttackStat(attack);
    }

    /**
     * Creates a new hand gear by combining the current hand gear with the provided gear.
     *
     * @param gear The gear to be combined with the current hand gear.
     * @return A new hand gear created by combining the current hand gear with the provided gear.
     */
    @Override
    public Gear createGear(Gear gear) {
        // Combine the prefixes and calculate the new attack stat
        String newPrefix = this.getPrefix() + ", " + gear.getPrefix();
        int newAttackStat = this.getAttackStat() + gear.getAttackStat();

        // Create and return a new HandGear instance
        return new HandGear(newPrefix, getName(), newAttackStat);
    }
}

