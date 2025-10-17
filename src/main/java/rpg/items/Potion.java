package rpg.items;

import rpg.characters.Characters;
import rpg.core.RarityType;
import rpg.core.StatusType;
import rpg.core.Usable;

public class Potion extends Item implements Usable {
    private final int healAmount;

    public Potion(int itemId, String name, String description, double weight, RarityType rarity, int healAmount) {
        super(itemId, name, description, weight, rarity);
        this.healAmount = healAmount;
    }

    @Override
    public int use(Characters user, Characters target) {
        target.heal(healAmount);
        user.getInventory().removeItem(this);
        return this.healAmount;
    }

    @Override
    public boolean isUsable(Characters user, Characters target) {
        return user.canAct() && (target.getStatus() != StatusType.MISSING || target.getStatus() != StatusType.DEAD);
    }

    @Override
    public String toString() {
        return String.format("%s Heals %d HP, %s",
            name, healAmount, rarity);
    }

}
