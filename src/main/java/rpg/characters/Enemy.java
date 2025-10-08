package rpg.characters;

import java.util.List;
import rpg.items.Item;
import rpg.characters.Characters;
import rpg.abilities.Ability;

public class Enemy extends Characters {

    // Constructor
    public Enemy(String name, int characterId, String description, int level, int healthPoints,
                             int actionPoints, Inventory inventory, List<Ability> abilities,
                             Map<StatType, Integer> stats, statusType status) {
        super(name, characterId, description, level, healthPoints, actionPoints, inventory, abilities,
                stats, status);
    }

    // Methods
    public boolean fleeChance() {
        // TODO
    }

    public void respawn() {
        // TODO
    }

    public List<Item> dropLoot() {
        // TODO
    }

    public void applyStatusEffect(Characters target) {
        // TODO
    }

    // Overrides due to Characters
    @Override
    public void attack(Characters target) {}

    @Override
    public void takeDamage(int amt) {}

    @Override
    public void useAbility(Ability ability, Characters target) {}

    @Override
    public void startTurn() {
        // TODO
    }

    @Override
    public boolean canAct() {
        // TODO
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