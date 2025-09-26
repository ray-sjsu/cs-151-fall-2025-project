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

    // Methods

    public abstract void attack(Characters target);
    public abstract void takeDamage(int amt);
    public abstract void useAbility(Ability ability, Characters target) throws AbilityOnCooldownException;
    public abstract void startTurn();
    public abstract boolean canAct();

    @Override
    public abstract String toString();

    public abstract int getStat(StatType type);
    public abstract int setStat(StatType type, int value);
}