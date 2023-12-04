package gear;

public interface Gear {
    String getPrefix();
    void setPrefix(String prefix);
    String getName();
    void setName(String name);
    int getAttackStat();
    int getDefenseStat();
    Gear combine(Gear other);
    String toString();
}
