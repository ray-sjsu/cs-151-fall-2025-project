package rpg.battlefield;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

import rpg.characters.Characters;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;

class BattlefieldManager {
    private int turnCount = 0;
    private final Queue<TurnAction> turnOrder = new ArrayDeque<>();
    private PlayableCharacter player;
    private Enemy enemy;

    public void initCombat(PlayableCharacter player, Enemy enemy) {
        this.player = Objects.requireNonNull(player, "player");
        this.enemy = Objects.requireNonNull(enemy, "enemy");
        turnOrder.clear();
        turnCount = 1;

        turnOrder.add(new TurnAction(player, TurnAction.ActionType.BEGIN_TURN, null));
        turnOrder.add(new TurnAction(enemy, TurnAction.ActionType.BEGIN_TURN, null));
    }

    public Characters currentActor() {
        TurnAction head = turnOrder.peek();
        return head == null ? null : head.actor();
    }

    public Characters nextTurn() {
        if (isBattleOver()) return null;

        TurnAction head = turnOrder.poll();
        if (head == null) return null;

        Characters actor = head.actor();
        if (actor == null || !actor.isAlive()) {
            // Skip
        } else {
            actor.startTurn(this);
            if (actor.isAlive()) {
                turnOrder.add(new TurnAction(actor, TurnAction.ActionType.BEGIN_TURN, null));
            }
        }

        turnCount++;
        if (isBattleOver()) return null;
        return currentActor();
    }

    public boolean isBattleOver() {
        boolean playerAlive = player != null && player.isAlive();
        boolean enemyAlive  = enemy  != null && enemy.isAlive();
        return !(playerAlive && enemyAlive);
    }

    public int currentTurn() {
        return turnCount;
    }

    public String winner() {
        if (!isBattleOver()) return "";
        if (player != null && player.isAlive()) return player.getName();
        if (enemy  != null && enemy.isAlive())  return enemy.getName();
        return "Draw";
    }

    @Override
    public String toString() {
        String p = (player == null) ? "—"
                : player.getName() + " (HP " + player.getHp() + (player.isAlive() ? "" : " ✖") + ")";
        String e = (enemy  == null) ? "—"
                : enemy.getName()  + " (HP " + enemy.getHp()  + (enemy.isAlive()  ? "" : " ✖") + ")";
        String next = (currentActor() == null) ? "—" : currentActor().getName();
        return "[Turn " + turnCount + "] " + p + "  vs  " + e + " | Next: " + next;
    }
}