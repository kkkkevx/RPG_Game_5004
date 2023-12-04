package character;

import gear.*;

public interface Character {
    void equip(Gear equipment);

    int getBaseAttackStat();

    int getBaseDefenseStat();
    String getName();
    void setName(String name);
    boolean hasHandGearSlot();
    boolean hasHeadGearSlot();
    boolean hasFootGearSlot();

    String toString();

}
