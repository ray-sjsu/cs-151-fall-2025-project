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
import rpg.items.Item;
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
        player.addAbility(new Ability(999, "Instant kill", "Instantly kill the enemy.", StatType.STR, 300, 0, 9999));
        player.setStat(StatType.STR, 10);
        player.setStat(StatType.INT, 7);

        Enemy enemy = new Enemy(1, "Goblin", "Looks like a frog.", 1, 50);
        Weapon stick = new Weapon(1, "Stick", "A long stick.",
                2.0, RarityType.RARE, 8, 12, 0.40);
        enemy.getInventory().addItem(stick);
        enemy.getInventory().addItem(new Item(44, "Gold Coin", "A shiny coin.", 0.1, RarityType.COMMON));
        enemy.equipWeapon(stick);

        battlefield.initCombat(player, enemy);

        String lastActionText = centerText("The battle begins!");
        int healAmount = 5;

        // Start of battle (every loop iteration is one turn, both Characters can take action)
        while (true) {
            clearScreen();
            printBattleUI(player, enemy, lastActionText, battlefield);

            if (battlefield.isBattleOver()) {
                break;
            }

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.printf("2. Heal (for %d HP)\n", healAmount);
            System.out.println("3. View Ability Menu");
            System.out.println("4. Rest (Reduce used abilities cooldowns by an additional 1)");
            System.out.println("5. Wait (Skip turn)");
            System.out.println("6. View Inventory");
            System.out.println("7. View Turn History");
            System.out.println("8. View Player and Enemy Stats");
            System.out.println("9. Exit Game");
            System.out.print("\n".repeat(2));
            System.out.print("> ");
            String choice = scanner.nextLine();

            // Start of player actions
            switch (choice) {
                case "1" -> {
                    int dmg = player.attack(enemy);
                    lastActionText = String.format("Player %s uses Weapon %s on Enemy %s for %d damage!\n", player.getName(), player.getEquippedWeapon().getName(), enemy.getName(), dmg);
                    battlefield.addTurnAction(new TurnAction(player, ActionType.ATTACK, StatusType.READY, lastActionText));
                    if (enemy.getHp() <= 0) {
                        enemy.setStatus(StatusType.DEAD);
                    }
                }
                case "2" -> {
                    player.heal(healAmount);
                    lastActionText = String.format("Player %s heals for %d HP!\n", player.getName(), healAmount);
                    battlefield.addTurnAction(new TurnAction(player, ActionType.ITEM, StatusType.READY, lastActionText));
                }
                case "3" -> {
                    Ability chosen = useAbilityMenu(scanner, player, enemy, battlefield);
                    if (chosen != null) {
                        int damage = chosen.use(player, enemy);
                        battlefield.addTurnAction(new TurnAction(player, ActionType.ABILITY, StatusType.READY, chosen));
                        lastActionText =  String.format("%s uses %s on %s for %d damage!\n", player.getName(), chosen.getName(), enemy.getName(), damage);
                    } else {
                        continue;
                    }
                }
                case "4" -> {
                    player.rest();
                    lastActionText = String.format("Player %s rested to reduce all used abilities cooldowns by an additional 1\n", player.getName());
                    battlefield.addTurnAction(new TurnAction(player, ActionType.ITEM, StatusType.READY, lastActionText));
                    if (enemy.getHp() <= 0) {
                        enemy.setStatus(StatusType.DEAD);
                    }
                }
                case "5" -> {
                    lastActionText = String.format("Player %s skipped their turn!\n", player.getName());
                    battlefield.addTurnAction(new TurnAction(player, ActionType.WAIT, StatusType.IDLE, lastActionText));
                }
                case "6" -> {
                    manageInventoryMenu(scanner, player);
                    continue;
                }
                case "7" -> {
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
                case "8" -> {
                    printCharacterComparison(player, enemy);
                    System.out.println("\nPress ENTER to return...");
                    scanner.nextLine();
                    continue;
                }
                case "9" -> {
                    System.out.println("Thanks for playing!");
                    scanner.close();
                    return;
                }
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("\nPress ENTER to continue...");
                    scanner.nextLine();
                }
            }


            // Start of enemy actions
            if (enemy.fleeChance(10)) {
                lastActionText = enemy.getName() + " ran away!";
                battlefield.addTurnAction(new TurnAction(enemy, ActionType.WAIT, StatusType.MISSING, lastActionText));
                enemy.setStatus(StatusType.MISSING);
            } else {
                int dmg = enemy.attack(player);
                lastActionText += String.format("Enemy %s uses Weapon %s on Player %s for %d damage!", enemy.getName(), enemy.getEquippedWeapon().getName(), player.getName(), dmg);
                battlefield.addTurnAction(new TurnAction(enemy, ActionType.ATTACK, StatusType.READY, lastActionText));
                if (player.getHp() > 0) {
                    player.setStatus(StatusType.IDLE);
                } else {
                    player.setStatus(StatusType.DEAD);
                }
            }

            // Reset player cooldowns
            for (Ability a : player.getAbilities()) {
                a.reduceCooldown();
            }

            battlefield.incrementTurnCount();
        }
        // End of battle
        endGameMenu(scanner, player, enemy, battlefield);
    }
}
