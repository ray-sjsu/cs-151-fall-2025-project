package rpg.characters;

import rpg.abilities.Ability;
import rpg.core.StatType;
import rpg.core.StatusType;
import rpg.exceptions.MaxInstancesLimitException;
import rpg.items.Inventory;
import rpg.items.Weapon;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public abstract class Characters {
    protected int characterId;
    protected String name;
    protected String description;

    protected int level;
    protected int hp;

    protected Inventory inventory;
    protected Weapon equippedWeapon;
    protected List<Ability> abilities;
    protected Map<StatType, Integer> stats;
    protected StatusType status;

    protected static int instanceCount;

    // Constructor
    public Characters(int characterId, String name, String description, int level, int maxHp) {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;

        this.characterId = characterId;
        this.name = name;
        this.description = description;
        this.level = level;
        this.hp = maxHp;
        this.inventory = new Inventory(10);
        this.abilities = new ArrayList<>();
        this.stats = new EnumMap<>(StatType.class);
        this.status = StatusType.READY;

        // New players start with STR 5, INT 5, DEX 5
        for (StatType type : StatType.values()) {
            this.stats.put(type, 5);
        }
    }

    // Methods
    public int attack(Characters target) {
        int damage;

        if (equippedWeapon != null) {
            damage = equippedWeapon.use(this, target);
        } else {
            damage = getStat(StatType.STR);
        }

        target.takeDamage(damage);
        return damage;
    }

    public void takeDamage(int amount) {
        hp -= amount;
        if (hp <= 0) {
            hp = 0;
            status = StatusType.DEAD;
        }
    }

    public boolean canAct() {
        return hp > 0 && status == StatusType.READY;
    }

    // Getters/Setters
    public Weapon getEquippedWeapon() {
        return this.equippedWeapon;
    }

    public void equipWeapon(Weapon weapon) {
        if (!inventory.getItems().contains(weapon)) {
            System.out.printf("%s does not have %s in their inventory!%n", name, weapon.getName());
            return;
        }

        this.equippedWeapon = weapon;
        System.out.printf("%s equips %s.%n", name, weapon.getName());
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void addAbility(Ability ability) {
        if (abilities == null) abilities = new ArrayList<>();
        abilities.add(ability);
    }

    public void removeAbility(Ability ability) {
        if (abilities != null) abilities.remove(ability);
    }

    // Basic Getters/Setters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public StatusType getStatus() {
        return this.status;
    }
    public void setStatus(StatusType status) {
        this.status = status;
    }

    public int getStat(StatType type) {
        return stats.getOrDefault(type, 0);
    }
    public void setStat(StatType type, int value) {
        stats.put(type, value);
    }

    // Overrides
    @Override
    public String toString() {
        return String.format(
                "\n--- Character Info ---" +
                "\n%s (Level %d) | HP: %d | Status: %s" +
                "\nEquipped Weapon: %s" +
                "\nDescription: %s" +
                "\nSTR: %s, INT: %s, DEX: %s",
                name, level, hp, status, equippedWeapon, description, getStat(StatType.STR), getStat(StatType.INT), getStat(StatType.DEX)
        );
    }

}