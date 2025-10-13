package rpg.characters;

import rpg.core.StatusType;
import rpg.core.RarityType;
import rpg.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Characters {

    // Constructor
    public Enemy(int characterId, String name, String description, int level, int maxHp) {
        super(characterId, name, description, level, maxHp);
    }

    // Methods
    public boolean fleeChance(int percentChance) {
        Random rand = new Random();

        boolean success = rand.nextInt(100) < percentChance;
        if (success) {
            setStatus(StatusType.MISSING);
        }

        return success;
    }

    public List<Item> dropLoot() {
        List<Item> loot = new ArrayList<>();

        loot.add(new Item(1, "Gold Coin", "A shiny coin.", 0.1, RarityType.COMMON));
        System.out.printf("%s drops loot!%n", name);
        return loot;
    }

}