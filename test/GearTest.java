import org.junit.Test;
import static org.junit.Assert.*;
import gear.*;
public class GearTest {

    @Test
    public void testCombineFootGear() {
        // Arrange
        Gear footGear1 = new FootGear("Prefix1", "FootGear1", 10, 5);
        Gear footGear2 = new FootGear("Prefix2", "FootGear2", 8, 3);

        // Act
        Gear combinedFootGear = footGear1.combine(footGear2);

        // Assert
        assertNotNull(combinedFootGear);
        assertEquals("Prefix1, Prefix2", combinedFootGear.getPrefix());
        assertEquals("FootGear1", combinedFootGear.getName());
        assertEquals(18, combinedFootGear.getAttackStat());
        assertEquals(8, combinedFootGear.getDefenseStat());
    }

    @Test
    public void testCombineHandGear() {
        // Arrange
        Gear handGear1 = new HandGear("Prefix1", "HandGear1", 10);
        Gear handGear2 = new HandGear("Prefix2", "HandGear2", 8);

        // Act
        Gear combinedHandGear = handGear1.combine(handGear2);

        // Assert
        assertNotNull(combinedHandGear);
        assertEquals("Prefix1, Prefix2", combinedHandGear.getPrefix());
        assertEquals("HandGear1", combinedHandGear.getName());
        assertEquals(18, combinedHandGear.getAttackStat());
        assertEquals(0, combinedHandGear.getDefenseStat());
    }

    @Test
    public void testCombineHeadGear() {
        // Arrange
        Gear headGear1 = new HeadGear("Prefix1", "HeadGear1", 5);
        Gear headGear2 = new HeadGear("Prefix2", "HeadGear2", 3);

        // Act
        Gear combinedHeadGear = headGear1.combine(headGear2);

        // Assert
        assertNotNull(combinedHeadGear);
        assertEquals("Prefix1, Prefix2", combinedHeadGear.getPrefix());
        assertEquals("HeadGear1", combinedHeadGear.getName());
        assertEquals(0, combinedHeadGear.getAttackStat());
        assertEquals(8, combinedHeadGear.getDefenseStat());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFootGear() {
        // Arrange & Act
        new FootGear("InvalidPrefix", "InvalidFootGear", -5, 3);

        // Assert: IllegalArgumentException should be thrown
    }

    // Similar tests for HandGear and HeadGear...

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHandGear() {
        // Arrange & Act
        new HandGear("InvalidPrefix", "InvalidHandGear", -5);

        // Assert: IllegalArgumentException should be thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHeadGear() {
        // Arrange & Act
        new HeadGear("InvalidPrefix", "InvalidHeadGear", -5);

        // Assert: IllegalArgumentException should be thrown
    }
}
