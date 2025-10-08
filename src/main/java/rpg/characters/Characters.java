package rpg.characters;

import java.util.List;
import java.util.Map;
import rpg.items.Inventory;
import rpg.abilities.Ability;

public abstract class Characters {
    private String name;
    private int characterId;

    private String description;
    private int level;
    private int healthPoints;
    private int actionPoints;

    private Inventory inventory;
    private List<Ability> abilities;
    private Map<StatType, Integer> stats;
    private StatusType status;

    // Constructor
    public Characters(String name, int characterId, String description, int level, int healthPoints,
                      int actionPoints, Inventory inventory, List<Ability> abilities,
                      Map<StatType, Integer> stats, statusType status) {
        this.name = name;
        this.characterId = characterId;
        this.description = description;
        this.level = level;
        this.healthPoints = healthPoints;
        this.actionPoints = actionPoints;
        this.inventory = inventory;
        this.abilities = abilities;
        this.stats = stats;
        this.status = status;
    }

    // Getters/Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Map<StatType, Integer> getStats() {
        return stats;
    }

    public void setStats(Map<StatType, Integer> stats) {
        this.stats = stats;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    // Methods required of subclasses
    public abstract void attack(Characters target);
    public abstract void takeDamage(int amt);
    public abstract void useAbility(Ability ability, Characters target) throws AbilityOnCooldownException;
    public abstract void startTurn();
    public abstract boolean canAct();

    public abstract String toString() {
        // TODO
    }

    public abstract int getStat(StatType type) {
        return getStats().get(type);
    }

    public abstract void setStat(StatType type, int value) {
        getStats().put(type, value);
    }

}