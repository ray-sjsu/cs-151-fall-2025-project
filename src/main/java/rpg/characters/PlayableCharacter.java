package rpg.characters;

import java.util.List;
import rpg.items.Weapon;
import rpg.items.Item;
import rpg.abilities.Ability;

public class PlayableCharacter extends Characters {

    private Weapon equippedWeapon;

    // Constructor
    public PlayableCharacter(String name, int characterId, String description, int level, int maxHealth,
                             int currentHealth, int maxActionPoints, int currentActionPoints, Inventory inventory,
                             List<Ability> abilities, Map<StatType, Integer> stats, statusType status) {
        super(name, characterId, description, level, maxHealth, currentHealth, maxActionPoints, currentActionPoints,
                inventory, abilities, stats, status);
        this.equippedWeapon = null;
    }

    // Methods
    public void heal(int amount) {
        int newHp = getMaxHealth() + amount;
        setMaxHealth(newHp);
    }

    public void levelUp() {
        int newLvl = getLevel() + 1;
        setLevel(newLvl);
    }

    public public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        System.out.println(getName() + " has equipped " + weapon.getName() + "!");
    }

    public void loot(List<Item> items) {
        for (Item item : items) {
            inventory.addItem(item);
            System.out.println("Added " + item.getName() + " to " + getName() + "'s inventory.");
        }
    }

    // Overrides due to Characters
    @Override
    public void attack(Characters target) {
        if (equippedWeapon != null) {
            int dmgDealt = equippedWeapon.calculateDamage(this, target);
            target.getHealth() -= dmgDealt;
            System.out.println(this.getName() + " dealt " + dmgDealt + " to " + target.getName() + ".");
        } else System.out.println("No damage dealt. Equip a weapon!");

    }

    @Override
    public void takeDamage(int amount) {
        int newHp = getMaxHealth() - amount;
        setMaxHealth(newHp);
    }

    @Override
    public void useAbility(Ability ability, Characters target) throws AbilityOnCooldownException {
        super.useAbility(ability, target);
    }

    @Override
    public void startTurn() {
        for (Ability a : abilities) a.tickCooldown();
        restoreActionPoints();
        System.out.println(name + "'s turn begins!");
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