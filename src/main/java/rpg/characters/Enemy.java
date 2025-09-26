package rpg.characters;

import java.util.List;
import rpg.items.Item;
import rpg.characters.Characters;
import rpg.abilities.Ability;

public class Enemy extends Characters {

    // Methods

    public boolean fleeChance() {}

    public void respawn() {}

    public List<Item> dropLoot() {}

    public void applyStatusEffect(Characters target) {}

    // Overrides due to Characters

    @Override
    public void attack(Characters target) {}

    @Override
    public void takeDamage(int amt) {}

    @Override
    public void useAbility(Ability ability, Characters target) {}

    @Override
    public void startTurn() {}

    @Override
    public boolean canAct() {}

}