package rpg.abilities;

import rpg.battlefield.BattlefieldManager;
import rpg.core.StatType;
import rpg.characters.Characters;

public class Ability {
    private String name;
    private String description;
    private int actionPointCost;
    private int baseCooldown;
    private int cooldownRemaining;

    // Constructor
    public Ability (String name, String description, int actionPointCost, int baseCooldown) {
        this.name = name;
        this.description = description;
        this.actionPointCost = actionPointCost;
        this.baseCooldown = baseCooldown;
        this.cooldownRemaining = 0;
    }

    // Core Methods
    public void use(Characters user, List<Characters> target, BattlefieldManager bf) {
        if (!isReady()) {
            System.out.println(name + " is still on cooldown!");
        }
        if (user.getCurrentActionPoints() < actionPointCost) {
            System.out.println(user.getName() + " does not have enough AP!");
            return;
        }
        System.out.println(user.getName() + " uses " + name + " on " + target.size() + " target(s).");

        user.setCurrentActionPoints(user.getCurrentActionPoints() - actionPointCost);
        putOnCooldown();
    }

    // CD Management
    public boolean isReady() {
        if (cooldownRemaining == 0) return true;
        else return false;
    }

    public void putOnCooldown() {
        cooldownRemaining = baseCooldown;
    }

    public void resetCooldown() {
        cooldownRemaining = 0;
    }

    public void tickCooldown() {
        if (cooldownRemaining > 0) cooldownRemaining--;
        else System.out.println(getName() + " is ready to use.");
    }

    // There's already a variable for this.
//    public int getCooldownRemaining() {}

    // Debug Info
    @Override
    public string toString() {
        return String.format(
                "\n--- Ability Details ---" +
                "\nName: %s | AP Cost: %d | Cooldown: %d" +
                "\nDescription: %s" +
                "\nCooldown Remaining: %d",
                this.name, this.actionPointCost, this.baseCooldown, this.description, this.cooldownRemaining
        );
    }

}