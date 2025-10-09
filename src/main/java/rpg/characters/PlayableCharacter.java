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
        // TODO:
    }

    public void loot(List<Item> items) {
        // TODO
    }

    // Overrides due to Characters
    @Override
    public void attack(Characters target) {
        // INCORPORATE STR AND DEX HERE
        int d20 = random.nextInt(20) + 1;
        if (d20 == 20) {
            // CRIT: target.getHealth() -= equipped weapon's damage x 2
            System.out.println("CRITICAL HIT! " + target.getName() + " took " +  + " damage!");
        } else if (d20 >= 10) {
            // NORM: target.getHealth -= equipped weapon's damage
            System.out.println(target.getName() + " took " +  + " damage.");
        } else System.out.println(super.getName() + " missed their attack!");
    }

    @Override
    public void takeDamage(int amount) {
        int newHp = getHealthPoints() - amount;
        setHealthPoints(newHp);
    }

    @Override
    public void useAbility(Ability ability, Characters target) {
        // TODO: differ between damaging and healing abilities
    }

    @Override
    public void startTurn() {
        // TODO: not sure what this is
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
    public Integer getStat(StatType type) {
        super.getStat(type);
    }

    @Override
    public void setStat(StatType type, int value) {}
        super.setStat(type, value);
}