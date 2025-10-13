package rpg;

import org.junit.jupiter.api.Test;
import rpg.core.RarityType;
import rpg.items.Inventory;
import rpg.items.Weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryFullLimitTest {

    @Test
    void testAddingItemToFullInventory() {
        Weapon sword = new Weapon(1, "Knife", "Used for cooking or cutting.",
                5.0, RarityType.COMMON, 10, 15, 0.40);
        Inventory inventory = new Inventory(1);

        inventory.addItem(sword);
        int before = inventory.getItems().size();

        inventory.addItem(sword);
        int after = inventory.getItems().size();

        assertEquals(before, after, "Inventory should not exceed slot capacity.");
    }
}
