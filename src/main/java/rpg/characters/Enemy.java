package rpg.characters;

import java.util.List;
import java.util.Random;
import rpg.abilities.Ability;
import rpg.characters.Characters;
import rpg.items.Item;

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
        int d20 = random.nextInt(20) + 1;
        if (d20 >= 10) return true;
        else return false;
    }

    public void respawn() {
        if (super.getHealthPoints() <= 0) super.setHealthPoints(1);
    }

    public List<Item> dropLoot() {
        // TODO: make this drop basic stuff for now
    }

    public void applyStatusEffect(Characters target) {
        // TODO: what statuses?
    }

    // Overrides due to Characters
    @Override
    public void attack(Characters target) {
        target.getHealthPoints() -= 5;          // Mobs do 5 damage (for now)
    }

    @Override
    public void takeDamage(int amt) {
        super.getHealth() -= amt;
    }

    @Override
    public void useAbility(Ability ability, Characters target) {
        // TODO
    }

    @Override
    public void startTurn() {
        // TODO: should this be in TurnAction.java? not sure what this means
        // Uses StatusType.java
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