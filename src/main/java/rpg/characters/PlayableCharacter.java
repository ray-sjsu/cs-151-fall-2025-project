package rpg.characters;

import rpg.abilities.Ability;
import rpg.core.StatType;
import rpg.exceptions.InventoryFullException;
import rpg.items.Item;

import java.util.List;

public class PlayableCharacter extends Characters {

    // Constructor
    public PlayableCharacter(int characterId, String name, String description, int level, int maxHp) {
        super(characterId, name, description, level, maxHp);
    }

    // Methods
    public void heal(int amount) {
        hp += amount;
    }

    public void levelUp() {
        level++;
        setStat(StatType.STR, getStat(StatType.STR) + 1);
        setStat(StatType.INT, getStat(StatType.INT) + 1);
        setStat(StatType.DEX, getStat(StatType.DEX) + 1);
    }

    public void rest() {
        for (Ability ability : abilities) {
            ability.reduceCooldown();
        }
    }

    public void loot(List<Item> items) {
        for (Item item : items) {
            try {
                inventory.addItem(item);
                System.out.println("Added " + item.getName() + " to " + getName() + "'s inventory.");
            } catch (InventoryFullException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}