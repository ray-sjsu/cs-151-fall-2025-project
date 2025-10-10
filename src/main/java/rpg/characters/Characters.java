package rpg.characters;

import java.util.List;
import java.util.Map;
import rpg.items.Inventory;
import rpg.abilities.Ability;

public abstract class Characters {
    protected String name;
    protected int characterId;

    protected String description;
    protected int level;

    protected int maxHealth;
    protected int currentHealth;
    protected int maxActionPoints;
    protected int currentActionPoints;

    protected Inventory inventory;
    protected List<Ability> abilities;
    protected Map<StatType, Integer> stats;
    protected StatusType status;

    // Constructor
    public Characters(String name, int characterId, String description, int level, int maxHealth,
                      int currentHealth, int maxActionPoints, int currentActionPoints, Inventory inventory,
                      List<Ability> abilities, Map<StatType, Integer> stats, statusType status) {
        this.name = name;
        this.characterId = characterId;
        this.description = description;
        this.level = level;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.maxActionPoints = maxActionPoints;
        this.currentActionPoints = currentActionPoints;
        this.inventory = inventory;
        this.abilities = abilities;
        this.stats = stats;
        this.status = status;
    }

    // Methods required of subclasses
    public abstract int getStat(StatType type) {
        return getStats().get(type);
    }
    public abstract void setStat(StatType type, int value) {
        getStats().put(type, value);
    }

    public abstract void attack(Characters target);
    public abstract void takeDamage(int amount);

    public abstract void useAbility(Ability ability, Characters target) throws AbilityOnCooldownException {
        if (ability.isReady()) {
            ability.use(this, target, bf);
            // Need to discuss what abilities will do to the target
            ability.putOnCooldown();
            System.out.println(this.getName() + " used " + ability.getName() + " on " +  target.getName() + ".");
        } else throw new AbilityOnCooldownException(ability.getName() + " is not ready to use!");
    }

    public abstract void startTurn();

    public abstract boolean canAct() {
        if (getStatus() != StatusType.READY) return false;
        else return true;
    }

    // Basic utilities
    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void restoreActionPoints() {
        currentActionPoints = maxActionPoints;
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

    public int getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxActionPoints() {
        return maxActionPoints;
    }
    public void setMaxActionPoints(int maxActionPoints) {
        this.maxActionPoints = maxActionPoints;
    }

    public int getCurrentActionPoints() {
        return currentactionPoints;
    }
    public void setCurrrentActionPoints(int currentActionPoints) {
        this.currentActionPoints = maxActionPoints;
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

    @Override
    public abstract String toString() {
        return String.format(
                "\nCharacter Info:" +
                "\n%s (Level %d)" +
                "\nHP: %d/%d | AP: %d/%d" +
                "\nStatus: %s",
                name, level, maxHealth, currentHealth, maxActionPoints, currentActionPoints, status
        );
    }

}