package rpg;

import org.junit.jupiter.api.Test;
import rpg.abilities.Ability;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.StatType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbilityCooldownTest {

    @Test
    void usingAbilityWhileOnCooldown() {
        int result = 0;

        PlayableCharacter player = new PlayableCharacter(1, "player", "player description", 2, 20);
        Ability waterfall = new Ability(55, "Waterfall", "Torrents of water.", StatType.INT, 100, 0, 9999);
        player.addAbility(waterfall);

        Enemy enemy = new Enemy(1, "enemy", "enemy description", 4, 2000);

        // Use once
        result = waterfall.use(player, enemy);
        // Use twice on cooldown
        result = waterfall.use(player, enemy);

        assertEquals(-1, result, "Ability on cooldown should return -1");
    }
}
