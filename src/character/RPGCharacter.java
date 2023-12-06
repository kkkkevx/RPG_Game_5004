package character;

import gear.*;

public interface RPGCharacter {
    void equip(Gear equipment);

    int getBaseAttackStat();

    int getBaseDefenseStat();
    int getTotalAttackStat();
    int getTotalDefenseStat();
    String getName();
    void setName(String name);
    boolean hasHandGearSlot();
    boolean hasHeadGearSlot();
    boolean hasFootGearSlot();

    public int compareTo(RPGCharacter player);

    String toString();

}
