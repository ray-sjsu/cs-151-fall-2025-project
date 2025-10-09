package rpg.abilities;

import rpg.core.Usable;
import rpg.core.StatType;
import rpg.characters.Characters;

public class Ability implements Usable {
    private String name;
    private int abilityId;
    private int lastUsedTurn;
    private int power;
    private StatType scalingStat;
    private int actionPointCost;
    private int cooldown;

    // Constructor
    public Ability (String name, int abilityId, int lastUsedTurn, int power, StatType scalingStat,
                    int actionPointCost, int cooldown) {
        this.name = name;
        this.abilityId = abilityId;
        this.lastUsedTurn = lastUsedTurn;
        this.power = power;
        this.scalingStat = scalingStat;
        this.actionPointCost = actionPointCost;
        this.cooldown = cooldown;
    }

    // Methods
    public int spendActionPoints(Characters user) {
        user.getActionPoints() -= this.actionPointCost;
    }

    public void putOnCooldown() {
        // TODO: how are we tracking time?
    }

    public int scalingValue(Characters user) {
        // TODO: take the user's stat and have it affect base power, return actual damage dealt
    }

    public void resetCooldown() {
        // TODO: how are we tracking time?
    }

    // Overrides due to Usable
    @Override
    public void use(Characters user, Characters target) {
        if (!isUsable(user, target)) return;

        if (user.getStatus() != StatusType.READY) return;

        // check that the ability is not on cd

        //TODO: incorporate INT

    }

    @Override
    public boolean isUsable(Characters user, Characters target) {
        if (user.getHealthPoints() <= 0) {
            System.out.println(user.getName() + " is not alive");
            return false;
        }
        if (target.getHealthPoints() <= 0) {
            System.out.println(target.getName() + " is not alive");
            return false;
        }
        if (user.getActionPoints() <= 0) {
            System.out.println(user.getName() + " does not have enough AP");
            return false;
        }
        return true;
    }

    @Override
    public int cooldownRemaining() {
        // TODO: how are we tracking time?
    }

    @Override
    public string toString() {
        return String.format(
                "\n --- Ability Details ---" +
                "\n Name: %s" +
                "\n ID: %d" +
                "\n Last Used: %d" +
                "\n Power: %d" +
                "\n Scaling Stat: %s" +
                "\n AP Cost: %d" +
                "\n Cooldown: %d",
                this.name, this.abilityId, this.lastUsedTurn, this.power, this.scalingStat, this.actionPointCost,
                this.cooldown
        );
    }

}