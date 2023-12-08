import battle.Battle;
import character.*;
import gear.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BattleTest {

    private RPGCharacter character1;
    private RPGCharacter character2;
    private List<Gear> availableItems;

    @Before
    public void setUp() {
        // Initialize test data before each test method
        character1 = new RPGCharacterImpl("Player1", 2, 2);
        character2 = new RPGCharacterImpl("Player2", 2, 2);
        availableItems = new ArrayList<>();
        availableItems.add(new HeadGear("adj1", "Helmet", 10));
        availableItems.add(new HandGear("adj1", "Sword", 5));
        availableItems.add(new FootGear("adj1", "Boots", 0, 5));
        availableItems.add(new HeadGear("adj2", "Helmet", 7));
        availableItems.add(new HandGear("adj2", "Sword", 7));
        availableItems.add(new FootGear("adj2", "Boots", 3, 4));
        availableItems.add(new HeadGear("adj3", "Helmet", 3));
        availableItems.add(new HandGear("adj3", "Sword", 7));
        availableItems.add(new FootGear("adj3", "Boots", 7, 5));
        availableItems.add(new HandGear("adj4", "Sword", 1));
        availableItems.add(new FootGear("adj4", "Boots", 8, 1));
        availableItems.add(new HandGear("adj5", "Sword", 3));
        availableItems.add(new FootGear("adj5", "Boots", 2, 1));
        availableItems.add(new HandGear("adj6", "Sword", 5));
        availableItems.add(new FootGear("adj6", "Boots", 5, 5));
        availableItems.add(new HandGear("adj7", "Sword", 8));
        availableItems.add(new FootGear("adj7", "Boots", 1, 5));
        availableItems.add(new HandGear("adj8", "Sword", 3));
        availableItems.add(new FootGear("adj8", "Boots", 6, 4));
        availableItems.add(new HeadGear("adj9", "Helmet", 7));
    }

    @Test
    public void testBattleResult() {
        // Create a battle.Battle object with the initialized data
        Battle battle = new Battle(character1, character2, availableItems);

        // Start the battle
        battle.startBattle();

        // Assert the result based on your logic
        int damage1 = character1.getTotalAttackStat() - character2.getTotalDefenseStat();
        int damage2 = character2.getTotalAttackStat() - character1.getTotalDefenseStat();


        assertEquals(2, battle.getWinner(damage1, damage2));

    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItemList() {
        availableItems.remove(0);
        Battle battle = new Battle(character1, character2, availableItems);

    }

}