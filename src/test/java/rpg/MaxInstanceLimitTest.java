package rpg;

import org.junit.jupiter.api.Test;
import rpg.abilities.Ability;
import rpg.battlefield.TurnAction;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.ActionType;
import rpg.core.RarityType;
import rpg.core.StatType;
import rpg.core.StatusType;
import rpg.exceptions.MaxInstancesLimitException;
import rpg.items.Weapon;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MaxInstanceLimitTest {

    @Test
    void testTooManyEnemy() {
        for (int i = 0; i < 100; i++) {
            new Enemy(i, "Bears", "100 bears", 6, 10);
        }

        assertThrows(MaxInstancesLimitException.class, () -> {
            new Enemy(999, "Destroyer of worlds", "A description", 6, 10);
        });
    }

    @Test
    void testTooManyPlayableCharacters() {
        for (int i = 0; i < 100; i++) {
            new PlayableCharacter(i, "Player", "100 Player", 6, 10);
        }

        assertThrows(MaxInstancesLimitException.class, () -> {
            new PlayableCharacter(999, "The Player", "A description", 6, 10);
        });
    }

    @Test
    void testTooManyWeapons() {
        for (int i = 0; i < 100; i++) {
            new Weapon(i, "Knife", "Used for cooking or cutting.",
                    5.0, RarityType.COMMON, 10, 15, 0.40);
        }

        assertThrows(MaxInstancesLimitException.class, () -> {
            new Weapon(999, "Knife", "Used for cooking or cutting.",
                    5.0, RarityType.COMMON, 10, 15, 0.40);
        });
    }

    @Test
    void testTooManyAbilities() {
        for (int i = 0; i < 100; i++) {
            new Ability(i, "Waterfall", "Torrents of water.", StatType.INT, 100, 0, 9999);
        }

        assertThrows(MaxInstancesLimitException.class, () -> {
            new Ability(999, "Waterfall", "Torrents of water.", StatType.INT, 100, 0, 9999);
        });
    }

    @Test
    void testTooManyTurnActions() {
        Enemy enemy = new Enemy(1, "Goblin", "Looks like a frog.", 1, 50);
        for (int i = 0; i < 100; i++) {
            new TurnAction(enemy, ActionType.WAIT, StatusType.MISSING, i);
        }

        assertThrows(MaxInstancesLimitException.class, () -> {
            new TurnAction(enemy, ActionType.WAIT, StatusType.MISSING, 999);
        });
    }
}
