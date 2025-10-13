package rpg.ui;

import rpg.abilities.Ability;
import rpg.battlefield.BattlefieldManager;
import rpg.battlefield.TurnAction;
import rpg.characters.Characters;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.ActionType;
import rpg.core.StatType;
import rpg.core.StatusType;

import java.util.List;
import java.util.Scanner;

import static rpg.ui.Scene.clearScreen;

public class Menus {
    public static void showLevelUpStats(PlayableCharacter player) {
        int oldLevel = player.getLevel();
        int oldStr = player.getStat(StatType.STR);
        int oldInt = player.getStat(StatType.INT);
        int oldDex = player.getStat(StatType.DEX);

        player.levelUp();

        clearScreen();
        System.out.printf("%s LEVEL UP! %n", player.getName());
        System.out.printf("Level: %d → %d%n", oldLevel, player.getLevel());

        System.out.printf("%-10s : %2d → %2d  (+%d)%n", "STR",
                oldStr, player.getStat(StatType.STR),
                player.getStat(StatType.STR) - oldStr);

        System.out.printf("%-10s : %2d → %2d  (+%d)%n", "INT",
                oldInt, player.getStat(StatType.INT),
                player.getStat(StatType.INT) - oldInt);

        System.out.printf("%-10s : %2d → %2d  (+%d)%n", "DEX",
                oldDex, player.getStat(StatType.DEX),
                player.getStat(StatType.DEX) - oldDex);

    }
    public static void printCharacterComparison(Characters character1, Characters character2) {
        clearScreen();
        System.out.println("\n--- Character Comparison ---");
        System.out.print(character1);
        System.out.println(character2);
    }
    public static String useAbilityMenu(Scanner scanner, PlayableCharacter player, Enemy enemy, BattlefieldManager bf) {
        List<Ability> abilities = player.getAbilities();

        while (true) {
            System.out.println("\nAvailable Abilities:");
            for (int i = 0; i < abilities.size(); i++) {
                Ability a = abilities.get(i);
                String cooldownText = a.isOnCooldown()
                        ? String.format("(Cooldown: %d turns left)", a.cooldownRemaining())
                        : "(Ready)";
                System.out.printf("%d) %s %s%n", i + 1, a, cooldownText);
            }

            System.out.println("0) Cancel (Or press ENTER)");
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equals("0") || input.isEmpty()) {
                break;
            }

            int abilityIndex;
            try {
                abilityIndex = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            if (abilityIndex < 0 || abilityIndex >= abilities.size()) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            Ability chosen = abilities.get(abilityIndex);

            int damage = chosen.use(player, enemy);
            bf.addTurnAction(new TurnAction(player, ActionType.ABILITY, StatusType.READY, chosen));
            return String.format("%s uses %s on %s for %d damage!\n", player.getName(), chosen.getName(), enemy.getName(), damage);
        }

        return null;
    }
}
