import battle.*;
import character.*;
import gear.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Create characters
        RPGCharacter player1 = new RPGCharacterImpl("Player 1", 2, 2);
        RPGCharacter player2 = new RPGCharacterImpl("Player 2", 2, 2);

        // Create a list of available items
        List<Gear> availableItems = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            availableItems.add(new HeadGear("adj" + (i + 1), "Helmet", rand.nextInt(10)));
            availableItems.add(new HandGear("adj" + (i + 6), "Glove", rand.nextInt(10)));
            availableItems.add(new FootGear("adj" + (i + 11), "Boot", rand.nextInt(10), rand.nextInt(10)));
        }

        for(int i = 16; i < 21; i++) {
            if (rand.nextInt(3) == 0) {
                availableItems.add(new HeadGear("adj" + (i), "Helmet", rand.nextInt(10)));
            } else if (rand.nextInt(3) == 1) {
                availableItems.add(new HandGear("adj" + (i), "Glove", rand.nextInt(10)));
            } else {
                availableItems.add(new FootGear("adj" + (i), "Boot", rand.nextInt(10), rand.nextInt(10)));
            }
        }

        // Create a BattleDriver instance and start the battle
        Battle battleDriver = new Battle(player1, player2, availableItems);
        battleDriver.startBattle();
        System.out.println();
        System.out.println(player1);
        System.out.println(player2);

    }
}
