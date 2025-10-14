package rpg.abilities;

import rpg.characters.Characters;
import rpg.core.StatType;
import rpg.core.Usable;
import rpg.exceptions.AbilityOnCooldownException;
import rpg.exceptions.MaxInstancesLimitException;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public class Ability implements Usable {
    private final int abilityId;
    private String name;
    private String description;

    private final StatType scalingStat;
    private final int basePower;

    private int currentCooldownDuration;
    private int cooldownDuration;

    private static int instanceCount;

    // Constructor
    public Ability (int abilityId, String name, String description, StatType scalingStat, int basePower,
                    int currentCooldownDuration, int cooldownDuration) {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;

        this.abilityId = abilityId;
        this.name = name;
        this.description = description;
        this.scalingStat = scalingStat;
        this.basePower = basePower;
        this.currentCooldownDuration = currentCooldownDuration;
        this.cooldownDuration = cooldownDuration;
    }

    // Methods
    public void startCooldown() {
        this.currentCooldownDuration = cooldownDuration;
    }

    public void reduceCooldown() {
        if (currentCooldownDuration > 0) currentCooldownDuration--;
    }

    public boolean isOnCooldown() {
        return currentCooldownDuration > 0;
    }

    // Getters/Setters
    public String getName() {
        return this.name;
    }

    // Overrides
    @Override
    public int use(Characters user, Characters target) {
        int damage = 0;

        try {
            if (isOnCooldown()) {
                throw new AbilityOnCooldownException(name + " is on cooldown!");
            } else {
                int statValue = user.getStat(scalingStat);
                damage = basePower + (statValue / 2);
                target.takeDamage(damage);
                startCooldown();
            }
        } catch (AbilityOnCooldownException e) {
            damage = -1;
        }

        return damage;
    }

    @Override
    public boolean isUsable(Characters user, Characters target) {
        return currentCooldownDuration == 0 && user.canAct() && target.getHp() > 0;
    }

    public int cooldownRemaining() {
        return currentCooldownDuration;
    }

    @Override
    public String toString() {
        return String.format(
                "\n--- Ability Details ---" +
                "\nName: %s | Power: %d" +
                "\nCooldown Remaining: %d / %d", name, basePower, currentCooldownDuration, cooldownDuration
        );
    }

}