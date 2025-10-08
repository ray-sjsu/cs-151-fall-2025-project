package rpg.characters;

import java.util.List;
import rpg.items.Weapon;
import rpg.items.Item;
import rpg.abilities.Ability;

public class PlayableCharacter extends Characters {

    // Constructor
    public PlayableCharacter(String name, int characterId, String description, int level, int healthPoints,
                             int actionPoints, Inventory inventory, List<Ability> abilities,
                             Map<StatType, Integer> stats, statusType status) {
        super(name, characterId, description, level, healthPoints, actionPoints, inventory, abilities,
                stats, status);
    }

    // Methods
    public void heal(int amount) {
        int newHp = getHealthPoints() + amount;
        setHealthPoints(newHp);
    }

    public void levelUp() {
        int newLvl = getLevel() + 1;
        setLevel(newLvl);
    }

    public public void equipWeapon(Weapon weapon) {
        // TODO
    }

    public void loot(List<Item> items) {
        // TODO
    }

    // Overrides due to Characters
    @Override
    public void attack(Characters target) {
        // TODO
    }

    @Override
    public void takeDamage(int amount) {
        int newHp = getHealthPoints() - amount;
        setHealthPoints(newHp);
    }

    @Override
    public void useAbility(Ability ability, Characters target) {
        // TODO
    }

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
    public Integer getStat(StatType type) {
        super.getStat(type);
    }

    @Override
    public void setStat(StatType type, int value) {}
        super.setStat(type, value);
}