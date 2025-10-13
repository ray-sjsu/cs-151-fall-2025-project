package rpg.ui;

import rpg.battlefield.BattlefieldManager;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;

public class Scene {
    public static final int SCREEN_WIDTH = 100;
    private static final int SPRITE_SPACING = 20;

    public static String centerText(String text) {
        if (text.length() >= SCREEN_WIDTH) return text;
        int leftPadding = (SCREEN_WIDTH - text.length()) / 2;
        return " ".repeat(leftPadding) + text;
    }
    public static void loadingScreen(long milliseconds) {
        System.out.println("=".repeat(SCREEN_WIDTH));
        System.out.println(centerText("LOADING RPG WORLD..."));
        System.out.println("=".repeat(SCREEN_WIDTH));

        try {
            for (int i = 0; i <= 5; i++) {
                System.out.print(".");
                Thread.sleep(milliseconds);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void clearScreen() {
        System.out.print("\n".repeat(6));
        System.out.flush();
    }
    public static void printBattleUI(PlayableCharacter player, Enemy enemy, String actionText, BattlefieldManager bf) {
        System.out.println("=".repeat(SCREEN_WIDTH));
        System.out.println(centerText(String.format("Turn Number %d", bf.getTurnCount())));
        System.out.println("=".repeat(SCREEN_WIDTH));
        System.out.printf("%-15s Lv.%d | HP: %d | Weapon: %-10s%n",
                player.getName(),
                player.getLevel(),
                player.getHp(),
                (player.getEquippedWeapon() != null
                        ? player.getEquippedWeapon().getName()
                        : "None"));
        System.out.printf("%-15s Lv.%d | HP: %d | Weapon: %-10s%n",
                enemy.getName(),
                enemy.getLevel(),
                enemy.getHp(),
                (enemy.getEquippedWeapon() != null
                        ? enemy.getEquippedWeapon().getName()
                        : "None"));
        System.out.println("=".repeat(SCREEN_WIDTH));

        System.out.println();

        System.out.println(centerText("\uD83D\uDC68\u200D\uD83C\uDF73 \uD83D\uDD2A" + " ".repeat(SPRITE_SPACING) + "\uD83E\uDD1B \uD83D\uDC38"));
        System.out.println();
        System.out.println(centerText(player.getName() + " ".repeat(SPRITE_SPACING) + enemy.getName()));

        System.out.println("-".repeat(SCREEN_WIDTH));
        System.out.println(actionText);
        System.out.println("-".repeat(SCREEN_WIDTH));
    }
}
