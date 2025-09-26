package rpg.abilities;

// IMPORT USABLE HERE ONCE IMPLEMENTED
import rpg.core.StatType;
import rpg.characters.Characters;

public class Ability /*implements Usable*/ {
    private int abilityId;
    private String name;
    private int lastUsedTurn;
    private int power;
    private StatType scalingStat;
    private int actionPointCost;
    private int cooldown;

    // Methods

    public int spendActionPoints(Characters user) {}

    public void putOnCooldown() {}

    public int scalingValue(Characters user) {}

    public void resetCooldown() {}

    // Overrides due to Usable

}