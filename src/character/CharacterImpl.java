package character;

import gear.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CharacterImpl implements Character, Comparable<Character>{
    public static void main(String[] args) {
        Character player1 = new CharacterImpl("Kan", 2, 2);
        HeadGear head = new HeadGear("wudi", "tou", 2);
        HandGear hand1 = new HandGear("wudi","shoutao", 2);
        HandGear hand2 = new HandGear("wudi","shoutao2", 2);
        HandGear hand3 = new HandGear("wudi3","shoutao3", 3);
        player1.equip(head);
        player1.equip(hand1);
        player1.equip(hand2);
        player1.equip(hand2);
    }

    private static final int Max_Hand_Gear_Slot = 2;
    private static final int Max_Foot_Gear_Slot = 2;


    String name;
    private final int baseAttackStat;
    private final int baseDefenseStat;

    private int totalAttackStat;
    private int totalDefenseStat;
    HeadGear equippedHeadGear;
    ArrayList<HandGear> equippedHandGears;
    ArrayList<FootGear> equippedFootGears;

    public CharacterImpl(String name, int baseAttackStat, int baseDefenseStat) {
        this.name = name;
        this.baseAttackStat = baseAttackStat;
        this.baseDefenseStat = baseDefenseStat;
        equippedFootGears = new ArrayList<FootGear>(Max_Foot_Gear_Slot);
        equippedHandGears = new ArrayList<HandGear>(Max_Hand_Gear_Slot);
    }

    @Override
    public void equip(Gear equipment) {
        // If gear is headgear
        if (equipment instanceof HeadGear) {
            if (hasHeadGearSlot()) {
                //equip gear in empty slot

                equippedHeadGear = (HeadGear) equipment;
            } else {
                //combine gear when no more slot
                equippedHeadGear = (HeadGear) equippedHeadGear.combine(equipment);
            }
        }
        // If gear is handgear
        if (equipment instanceof HandGear) {
            if (hasHandGearSlot()) {
                //equip gear in empty slot

                equippedHandGears.add((HandGear) equipment);
            } else {
                //combine gear when no more slot
                HandGear newItem = (HandGear) equippedHandGears.get(0).combine(equipment);
                equippedHandGears.set(0, newItem);
            }
        }
        // if gear is footgear
        if (equipment instanceof FootGear) {
            if (hasFootGearSlot()) {
                //equip gear in empty slot
                equippedFootGears.add((FootGear) equipment);
            } else {
                //combine gear when no more slot
                FootGear newItem = (FootGear) equippedFootGears.get(0).combine(equipment);
                equippedFootGears.set(0, newItem);
            }

        }
        totalDefenseStat += equipment.getDefenseStat();
        totalAttackStat += equipment.getAttackStat();

    }

        @Override
    public int getBaseAttackStat() {
        return baseAttackStat;
    }



    @Override
    public int getBaseDefenseStat() {
        return baseDefenseStat;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean hasHandGearSlot() {
        return equippedHandGears.size() < Max_Hand_Gear_Slot;
    }

    @Override
    public boolean hasHeadGearSlot() {
        return equippedHeadGear == null;
    }

    @Override
    public boolean hasFootGearSlot() {
        return equippedFootGears.size() < Max_Foot_Gear_Slot;
    }

    @Override
    public int compareTo(Character o) {
        return 0;
    }

    @Override
    public String toString() {

        return "Character: \n" +
                "name='" + name + "'\n" +
                "baseAttackStat=" + baseAttackStat + "\n" +
                "baseDefenseStat=" + baseDefenseStat + "\n" +
                "totalAttackStat=" + totalAttackStat + "\n" +
                "totalDefenseStat=" + totalDefenseStat + "\n" +
                "equippedHeadGear=" + equippedHeadGear + "\n" +
                "equippedHandGears=" + Arrays.toString(new ArrayList[]{equippedHandGears}) + "\n" +
                "equippedFootGears=" + Arrays.toString(new ArrayList[]{equippedFootGears}) + "\n" +
                "*******************************";
    }
}
