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
        // TODO
    }

    public void putOnCooldown() {
        // TODO
    }

    public int scalingValue(Characters user) {
        // TODO
    }

    public void resetCooldown() {
        // TODO
    }

    // Overrides due to Usable
    @Override
    public void use(Characters user, Characters target) {
        if (!isUsable(user, target)) return;

        // TODO

    }

    @Override
    public boolean isUsable(Characters user, Characters target) {
        // TODO
    }

    @Override
    public int cooldownRemaining() {
        // TODO: refer to battlefield package
    }

    @Override
    public string toString() {
        return String.format(
                "\n --- Ability Details ---" +
                "\n Name: %s" +
                "\n ID: %d" +
                "\n Last Used: %d" +
                "\n Power: %d" +
                "\n Scaling Stat: %s" +     // Possible error?
                "\n AP Cost: %d", +
                "\n Cooldown: %d"
                this.name, this.abilityId, this.lastUsedTurn, this.power, this.scalingStat, this.actionPointCost,
                this.cooldown);
    }

}