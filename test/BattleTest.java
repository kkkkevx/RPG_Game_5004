import gear.*;
import character.*;
import battle.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BattleMethodsTest {

    private RPGCharacter player1;
    private List<Gear> availableItems;

    @Before
    public void setUp() {
        player1 = new RPGCharacterImpl("Player1", 5, 5);

        availableItems = new ArrayList<>();
        availableItems.add(new HeadGear("Head", "Helmet1", 2));
        availableItems.add(new HandGear("Hand", "Glove1", 3));
        availableItems.add(new FootGear("Foot", "Boot1", 1, 2));
        availableItems.add(new HeadGear("Head", "Helmet2", 1));
        availableItems.add(new HandGear("Hand", "Glove2", 2));
        availableItems.add(new FootGear("Foot", "Boot2", 3, 1));
        availableItems.add(new HeadGear("Head", "Helmet3", 3));
        availableItems.add(new HandGear("Hand", "Glove3", 1));
        availableItems.add(new FootGear("Foot", "Boot3", 2, 3));
    }

    @Test
    public void testPickItem() {
        Battle battle = new Battle(player1, null, availableItems);
        Gear chosenItem = availableItems.get(0);

        battle.pickItem(player1);

        assertTrue(player1.getEquippedHeadGear() == chosenItem ||
                player1.getEquippedHandGears().contains(chosenItem) ||
                player1.getEquippedFootGears().contains(chosenItem));
        assertTrue(!availableItems.contains(chosenItem));
    }

    @Test
    public void testSortItems() {
        Battle battle = new Battle(player1, null, availableItems);

        // Manually sort items based on the specified rules
        availableItems.sort((item1, item2) -> {
            if (player1.hasHeadGearSlot() && item1 instanceof HeadGear && item2 instanceof HeadGear) {
                return Battle.compareItems(item1, item2);
            } else if (player1.hasHandGearSlot() && item1 instanceof HandGear && item2 instanceof HandGear) {
                return Battle.compareItems(item1, item2);
            } else if (player1.hasFootGearSlot() && item1 instanceof FootGear && item2 instanceof FootGear) {
                return Battle.compareItems(item1, item2);
            } else {
                return Battle.compareItems(item1, item2);
            }
        });

        // Compare manually sorted items with items sorted by the method
        List<Gear> manuallySortedItems = new ArrayList<>(availableItems);
        battle.sortItems(player1);

        assertEquals(manuallySortedItems, availableItems);
    }

    @Test
    public void testCompareItems() {
        HeadGear headGear1 = new HeadGear("Head", "Helmet1", 2);
        HeadGear headGear2 = new HeadGear("Head", "Helmet2", 1);
        HandGear handGear1 = new HandGear("Hand", "Glove1", 3);
        HandGear handGear2 = new HandGear("Hand", "Glove2", 2);
        FootGear footGear1 = new FootGear("Foot", "Boot1", 1, 2);
        FootGear footGear2 = new FootGear("Foot", "Boot2", 3, 1);

        // Test when attack strength is different
        assertEquals(-1, Battle.compareItems(headGear1, headGear2));
        assertEquals(-1, Battle.compareItems(handGear1, handGear2));
        assertEquals(-1, Battle.compareItems(footGear1, footGear2));

        // Test when attack strength is the same, but defense strength is different
        assertEquals(-1, Battle.compareItems(headGear1, headGear2));
        assertEquals(-1, Battle.compareItems(handGear1, handGear2));
        assertEquals(-1, Battle.compareItems(footGear1, footGear2));

        // Test when attack and defense strength are the same, randomly choose
        assertEquals(-1, Battle.compareItems(headGear1, headGear2));
        assertEquals(-1, Battle.compareItems(handGear1, handGear2));
        assertEquals(-1, Battle.compareItems(footGear1, footGear2));
    }
}