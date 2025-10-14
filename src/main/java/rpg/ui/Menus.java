package rpg.ui;

import rpg.abilities.Ability;
import rpg.battlefield.BattlefieldManager;
import rpg.battlefield.TurnAction;
import rpg.characters.Characters;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.StatType;
import rpg.items.Item;

import java.util.List;
import java.util.Scanner;

import static rpg.ui.Scene.*;

public class Menus {
    public static void showLevelUpStats(PlayableCharacter player) {
        int oldLevel = player.getLevel();
        int oldStr = player.getStat(StatType.STR);
        int oldInt = player.getStat(StatType.INT);
        int oldDex = player.getStat(StatType.DEX);

        player.levelUp();

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
    public static Ability useAbilityMenu(Scanner scanner, PlayableCharacter player, Enemy enemy, BattlefieldManager bf) {
        List<Ability> abilities = player.getAbilities();

        while (true) {
            System.out.println("\nAvailable Abilities:");
            for (int i = 0; i < abilities.size(); i++) {
                Ability a = abilities.get(i);
                String cooldownText = a.isOnCooldown()
                        ? String.format("(Cooldown: %d turns left)", a.cooldownRemaining())
                        : "(Ready)";
                System.out.printf("%d) %s %s%n\n", i + 1, a, cooldownText);
            }

            System.out.println("0) Cancel (Or press ENTER)");
            System.out.print("\n\n> ");
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

            return abilities.get(abilityIndex);
        }

        return null;
    }
    public static void endGameMenu(Scanner scanner, PlayableCharacter player, Enemy enemy, BattlefieldManager bf) {
        String resultMessage = "Battle results.";

        if (bf.getWinner().isEmpty()) {
            resultMessage = "It's a draw!";
        }
        else if (bf.getWinner().getFirst() instanceof PlayableCharacter) {
            resultMessage = "Player wins!";
            System.out.printf("Enemy %s has dropped some loot.\n", enemy.getName());
            List<Item> enemyLoot = enemy.dropLoot();
            System.out.printf("Player %s has looted Enemy %s.\n", player.getName(), enemy.getName());
            player.loot(enemyLoot);
        } else if (bf.getWinner().getFirst() instanceof Enemy) {
            resultMessage = "Enemy wins!";
        }


        while (true) {
            // Check winner
            System.out.println("*".repeat(SCREEN_WIDTH));
            System.out.println(centerText(resultMessage));
            System.out.println("*".repeat(SCREEN_WIDTH));

            if (bf.getWinner().getFirst() instanceof PlayableCharacter) {
                showLevelUpStats(player);
                System.out.printf("Enemy %s has dropped some loot.\n", enemy.getName());
                System.out.printf("Player %s has looted Enemy %s.\n", player.getName(), enemy.getName());
            }

            // Menu
            System.out.println("\nChoose an action:");
            System.out.println("1. View Inventory");
            System.out.println("2. View Turn History");
            System.out.println("3. View Player and Enemy stats");
            System.out.println("4. End game");
            System.out.print("\n".repeat(2));
            System.out.print("> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    manageInventoryMenu(scanner, player);
                }
                case "2" -> {
                    System.out.printf("\nBattle History - Total Turns: %d%n", bf.getTurnCount());
                    List<TurnAction> history = bf.getTurnHistory();

                    if (history.isEmpty()) {
                        System.out.println("No actions have been taken yet.");
                    } else {
                        for (int i = 0; i < history.size(); i++) {
                            System.out.printf("%2d. %s%n", i + 1, history.get(i));
                        }
                    }

                    System.out.println("\nPress ENTER to return...");
                    scanner.nextLine();
                }
                case "3" -> {
                    printCharacterComparison(player, enemy);
                    System.out.println("\nPress ENTER to return...");
                    scanner.nextLine();
                }
                case "4" -> {
                    System.out.println("\nThanks for playing!");
                    scanner.close();
                    return;
                }
            }
        }
    }
    public static void manageInventoryMenu(Scanner scanner, PlayableCharacter player) {
        while (true) {
            clearScreen();
            System.out.println("\n" + player.getName() + "'s Inventory:");
            System.out.println(player.getInventory());

            System.out.println("\nChoose an action:");
            System.out.println("1. Remove Item");
            System.out.println("2. Exit Inventory");
            System.out.print("\n> ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    List<Item> availableItems = player.getInventory().getItems();
                    Item equippedWeapon = player.getEquippedWeapon();

                    if (availableItems.isEmpty()) {
                        System.out.println("Inventory is empty. Nothing to remove.");
                        System.out.println("\nPress ENTER to continue...");
                        scanner.nextLine();
                        break;
                    }

                    System.out.println("\nSelect an item to remove:");
                    for (int i = 0; i < availableItems.size(); i++) {
                        Item item = availableItems.get(i);
                        if (item.equals(equippedWeapon)) {
                            System.out.printf("%d. %s (Equipped)%n", i + 1, item.getName());
                        } else {
                            System.out.printf("%d. %s%n", i + 1, item.getName());
                        }
                    }

                    System.out.print("\n> ");
                    String input = scanner.nextLine();

                    try {
                        int index = Integer.parseInt(input) - 1;
                        if (index >= 0 && index < availableItems.size()) {
                            Item selectedItem = availableItems.get(index);

                            if (selectedItem.equals(equippedWeapon)) {
                                System.out.println("You cannot remove your equipped weapon!");
                            } else {
                                player.getInventory().removeItem(selectedItem);
                                System.out.println("Removed: " + selectedItem.getName());
                            }
                        } else {
                            System.out.println("Invalid item number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }

                    System.out.println("\nPress ENTER to continue...");
                    scanner.nextLine();
                }

                case "2" -> {
                    System.out.println("Exiting inventory...");
                    return;
                }

                default -> {
                    return;
                }
            }
        }
    }
}
