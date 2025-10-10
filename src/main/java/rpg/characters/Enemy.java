package rpg.characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import rpg.abilities.Ability;
import rpg.characters.Characters;
import rpg.items.Item;

public class Enemy extends Characters {

    // Constructor
    public Enemy(String name, int characterId, String description, int level, int maxHealth,
                 int currentHealth, int maxActionPoints, int currentActionPoints, Inventory inventory,
                 List<Ability> abilities, Map<StatType, Integer> stats, statusType status) {
        super(name, characterId, description, level, maxHealth, currentHealth, maxActionPoints, currentActionPoints,
                inventory, abilities, stats, status);
    }

    // Methods
    public boolean fleeChance() {
        int d20 = random.nextInt(20) + 1;
        if (d20 >= 15) return true;
        else return false;
    }

    public void respawn() {
        if (getMaxHealth() <= 0) setMaxHealth(1);
    }

    public List<Item> dropLoot() {
        List<Item> loot = new ArrayList<>();
        int lootRoller = Random.nextInt(20) + 1;

        if (lootRoller == 20) {
            loot.add(new Weapon(100, "Excalibur", "The mythical sword of King Arthur!",
                    10.0, RarityType.LEGENDARY, 10, 25, 50, 15.0, 200, 0, 25, 2));     // Values are TBD
            System.out.println("LEGENDARY DROP! EXCALIBUR");
        }
        else {
            loot.add(new Weapon(1, "Puny Twig", "Truly saddening...",
                    1.0, RarityType.COMMON,3, 2, 5, 2.0, 25, 0, 10, 1));                // Values are TBD
            System.out.println("Common drop... Puny Twig...");
        }
    }
    
    // Overrides due to Characters
    @Override
    public void attack(Characters target) {
        int dmgDealt = target.getMaxHealth() * 0.2;
        target.getMaxHealth() -= dmgDealt; // Mobs do ROUGHLY 20% of target's HP
    }

    @Override
    public void takeDamage(int amt) {
        this.getHealth() -= amt;
    }

    @Override
    public void useAbility(Ability ability, Characters target) throws AbilityOnCooldownException {
        super.useAbility(ability, target);
    }

    @Override
    public void startTurn() {
        for (Ability a : abilities) a.tickCooldown();
        restoreActionPoints();
        decideAction();
    }

    @Override
    public boolean canAct() {
        super.canAct();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getStat(StatType type) {
        super.getStat(type);
    }

    @Override
    public void setStat(StatType type, int value) {
        super.setStat(type, value);
    }

}