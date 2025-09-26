package rpg.characters;

import java.util.List;
import rpg.items.Weapon;
import rpg.items.Item;
import rpg.abilities.Ability;

public class PlayableCharacter extends Characters {

    // Methods

    public void heal(int amount) {}

    public void levelUp() {}

    public public void equipWeapon(Weapon weapon) {}

    public void loot(List<Item> items) {}

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