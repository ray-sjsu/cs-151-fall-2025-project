package rpg;

import rpg.abilities.Ability;
import rpg.battlefield.BattlefieldManager;
import rpg.battlefield.TurnAction;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.ActionType;
import rpg.core.RarityType;
import rpg.core.StatType;
import rpg.core.StatusType;
import rpg.items.Weapon;

import java.util.List;
import java.util.Scanner;

import static rpg.ui.Menus.*;
import static rpg.ui.Scene.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        loadingScreen(1000);
        BattlefieldManager battlefield = new BattlefieldManager();

        System.out.println(centerText("\n\nWelcome to the Battlefield!\n"));

        PlayableCharacter player = new PlayableCharacter(1, "Gordon", "Used to run a kitchen in hell.", 1, 50);
        Weapon sword = new Weapon(1, "Knife", "Used for cooking or cutting.",
                5.0, RarityType.COMMON, 10, 15, 0.40);
        player.getInventory().addItem(sword);
        player.equipWeapon(sword);
        player.addAbility(new Ability(1, "Flambé\uD83D\uDD25", "INT-scaled attack that sets the enemy on fire.", StatType.INT, 20, 0, 2));
        player.addAbility(new Ability(2, "Casted Iron\uD83C\uDF73", "STR-scaled attack for hitting un-pleasant customers.", StatType.STR, 30, 0, 3));
        player.setStat(StatType.STR, 10);
        player.setStat(StatType.INT, 7);

        Enemy enemy = new Enemy(1, "Goblin", "Looks like a frog.", 1, 50);
        Weapon fists = new Weapon(1, "Hands", "Can't catch these.",
                2.0, RarityType.RARE, 8, 12, 0.40);
        enemy.getInventory().addItem(fists);
        enemy.equipWeapon(fists);

        battlefield.initCombat(player, enemy);

        String lastActionText = centerText("The battle begins!");

        while (true) {
            clearScreen();
            printBattleUI(player, enemy, lastActionText, battlefield);

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Use Ability");
            System.out.println("4. Wait (Skip turn)");
            System.out.println("5. View Inventory");
            System.out.println("6. View Turn History");
            System.out.println("7. View Player and Enemy stats");
            System.out.print("> ");

            String choice = scanner.nextLine();

            // Continue battle
            if (!battlefield.isBattleOver()) {
                // Start of player turn
                switch (choice) {
                    case "1" -> {
                        int dmg = player.attack(enemy);
                        lastActionText = String.format("Player %s uses Weapon %s on Enemy %s for %d damage!\n", player.getName(), player.getEquippedWeapon().getName(), enemy.getName(), dmg);
                        battlefield.addTurnAction(new TurnAction(player, ActionType.ATTACK, StatusType.READY, lastActionText));
                    }
                    case "2" -> {
                        int healAmount = 5;
                        player.heal(healAmount);
                        lastActionText = String.format("Player %s heals for %d HP!\n", player.getName(), healAmount);
                        battlefield.addTurnAction(new TurnAction(player, ActionType.ITEM, StatusType.READY, lastActionText));
                    }
                    case "3" -> {
                        String result = useAbilityMenu(scanner, player, enemy, battlefield);
                        if (result != null) {
                            lastActionText = result;
                        } else {
                            continue;
                        }
                    }
                    case "4" -> {
                        lastActionText = String.format("Player %s skipped their turn!\n", player.getName());
                        battlefield.addTurnAction(new TurnAction(player, ActionType.WAIT, StatusType.IDLE, lastActionText));
                    }
                    case "5" -> {
                        System.out.println(player.getName() + "'s Inventory:");
                        System.out.println(player.getInventory());
                        System.out.println("Press ENTER to return...");
                        scanner.nextLine();
                        continue;
                    }
                    case "6" -> {
                        System.out.printf("\nBattle History - Total Turns: %d", battlefield.getTurnCount());
                        List<TurnAction> history = battlefield.getTurnHistory();
                        if (history.isEmpty()) {
                            System.out.println("No actions have been taken yet.");
                        } else {
                            System.out.println();
                            for (int i = 0; i < history.size(); i++) {
                                System.out.printf("%2d. %s%n", i + 1, history.get(i));
                            }
                        }
                        System.out.println("\nPress ENTER to return...");
                        scanner.nextLine();
                        continue;
                    }
                    case "7" -> {
                        printCharacterComparison(player, enemy);
                        System.out.println("\nPress ENTER to return...");
                        scanner.nextLine();
                        continue;
                    }
                    default -> {
                        continue;
                    }
                }


                // Check after player turn
                if (battlefield.isBattleOver()) {
                    clearScreen();
                    printBattleUI(player, enemy, lastActionText, battlefield);
                    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                    break;
                }

                // Start of enemy turn
                if (enemy.fleeChance(10)) {
                    lastActionText = enemy.getName() + " ran away!";
                    battlefield.addTurnAction(new TurnAction(enemy, ActionType.WAIT, StatusType.MISSING, lastActionText));
                    break;
                } else {
                    player.setStatus(StatusType.IDLE);
                    int dmg = enemy.attack(player);
                    lastActionText += String.format("Enemy %s uses Weapon %s on Player %s for %d damage!", enemy.getName(), enemy.getEquippedWeapon().getName(), player.getName(), dmg);
                    battlefield.addTurnAction(new TurnAction(enemy, ActionType.ATTACK, StatusType.READY, lastActionText));
                    enemy.setStatus(StatusType.IDLE);
                }

                // Reset player cooldowns
                for (Ability a : player.getAbilities()) {
                    a.reduceCooldown();
                }

                battlefield.incrementTurnCount();
            } else {
                break;
            }
        }

        // End of battle
        clearScreen();
        printBattleUI(player, enemy, lastActionText, battlefield);

        if (battlefield.getWinner() instanceof PlayableCharacter) {
            showLevelUpStats(player);
        }

        System.out.println(battlefield.getWinner());
        System.out.println("\nThanks for playing!");
        scanner.close();
    }
}
