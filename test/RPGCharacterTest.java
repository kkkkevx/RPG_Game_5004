import character.*;
import gear.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RPGCharacterTest {

    private RPGCharacter player1;
    private RPGCharacter player2;

    @Before
    public void setUp() {
        player1 = new RPGCharacterImpl("Player1", 5, 5);
        player2 = new RPGCharacterImpl("Player2", 4, 4);
    }

    @Test
    public void equipHeadGear_Success() {
        Gear headGear = new HeadGear("Head", "Helmet", 2);
        player1.equip(headGear);
        assertEquals(headGear, player1.getEquippedHeadGear());
    }

    @Test
    public void equipHandGear_Success() {
        Gear handGear = new HandGear("Hand", "Glove", 3);
        player1.equip(handGear);
        assertTrue(player1.getEquippedHandGears().contains(handGear));
    }

    @Test
    public void equipFootGear_Success() {
        Gear footGear = new FootGear("Foot", "Boot", 1, 2);
        player1.equip(footGear);
        assertTrue(player1.getEquippedFootGears().contains(footGear));
    }

    @Test
    public void equipCombineHeadGear_Success() {
        Gear headGear1 = new HeadGear("Head", "Helmet1", 2);
        Gear headGear2 = new HeadGear("Head", "Helmet2", 1);

        player1.equip(headGear1);
        player1.equip(headGear2);

        assertEquals("Head, Head", player1.getEquippedHeadGear().getPrefix());
        assertEquals("Helmet1", player1.getEquippedHeadGear().getName());
        assertEquals(3, player1.getEquippedHeadGear().getDefenseStat());
    }

    @Test
    public void equipCombineHandGear_Success() {
        Gear handGear1 = new HandGear("Hand", "Glove1", 3);
        Gear handGear2 = new HandGear("Hand", "Glove2", 2);

        player1.equip(handGear1);
        player1.equip(handGear2);
        player1.equip(handGear2);

        assertEquals("Hand, Hand", player1.getEquippedHandGears().get(0).getPrefix());
        assertEquals("Glove1", player1.getEquippedHandGears().get(0).getName());
        assertEquals(5, player1.getEquippedHandGears().get(0).getAttackStat());
    }

    @Test
    public void equipCombineFootGear_Success() {
        Gear footGear1 = new FootGear("Foot", "Boot1", 1, 2);
        Gear footGear2 = new FootGear("Foot", "Boot2", 3, 1);

        player1.equip(footGear1);
        player1.equip(footGear2);
        player1.equip(footGear2);

        assertEquals("Foot, Foot", player1.getEquippedFootGears().get(0).getPrefix());
        assertEquals("Boot1", player1.getEquippedFootGears().get(0).getName());
        assertEquals(4, player1.getEquippedFootGears().get(0).getAttackStat());
        assertEquals(3, player1.getEquippedFootGears().get(0).getDefenseStat());
    }

    @Test
    public void compareTo_Player1Wins() {
        player1.equip(new HandGear("Hand", "Glove1", 3));
        player2.equip(new HeadGear("Head", "Helmet2", 1));

        int result = player1.compareTo(player2);

        assertTrue(result > 0);
    }

    @Test
    public void compareTo_Player2Wins() {
        player1.equip(new HeadGear("Head", "Helmet1", 2));
        player2.equip(new HandGear("Hand", "Glove2", 2));

        int result = player1.compareTo(player2);

        assertTrue(result > 0);
    }

    @Test
    public void compareTo_Draw() {
        int result = player1.compareTo(player2);
        assertEquals(0, result);
    }
}
