package rpg.items;

import rpg.characters.Characters;
import rpg.core.RarityType;
import rpg.core.StatusType;
import rpg.core.Usable;

public class Weapon extends Item implements Usable {
    private final int minDamage;
    private final int maxDamage;
    private final double critChance;

    public Weapon(int itemId, String name, String description, double weight, RarityType rarity, int minDamage, int maxDamage, double critChance) {
        super(itemId, name, description, weight, rarity);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.critChance = critChance;
    }

    public int use(Characters user, Characters target) {
        int base = (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
        boolean critical = Math.random() < critChance;
        return critical ? base * 2 : base;
    }

    public boolean isUsable(Characters user, Characters target) {
        return user.canAct() && (target.getStatus() != StatusType.MISSING || target.getStatus() != StatusType.DEAD);
    }

    @Override
    public String toString() {
        return String.format("%s [%d-%d dmg, %.0f%% crit, %s]",
                name, minDamage, maxDamage, critChance * 100, rarity);
    }
}
