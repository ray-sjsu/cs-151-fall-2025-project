package rpg.battlefield;

import rpg.characters.Characters;
import rpg.characters.Enemy;
import rpg.characters.PlayableCharacter;
import rpg.core.ActionType;
import rpg.core.StatusType;
import rpg.exceptions.MaxInstancesLimitException;

import java.util.*;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public class BattlefieldManager {
    private int turnCount = 0;
    private final Queue<TurnAction> turnOrder = new ArrayDeque<>();
    private PlayableCharacter player;
    private Enemy enemy;
    private static int instanceCount;

    public BattlefieldManager() {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;
    }

    public void initCombat(PlayableCharacter player, Enemy enemy) {
        this.player = Objects.requireNonNull(player, "player");
        this.enemy = Objects.requireNonNull(enemy, "enemy");
        turnOrder.clear();

        turnOrder.add(new TurnAction(player, ActionType.BEGIN_TURN, StatusType.READY, "begin"));
        turnOrder.add(new TurnAction(enemy, ActionType.BEGIN_TURN, StatusType.IDLE,"begin"));

        turnCount = 0;
    }

    public void addTurnAction(TurnAction action) {
        turnOrder.add(action);
        turnCount++;
    }

    public List<TurnAction> getTurnHistory() {
        return new ArrayList<>(turnOrder);
    }

    public boolean isBattleOver() {
        return getWinner() != null;
    }

    public Characters getWinner() {
        if (player == null || enemy == null) return null;

        StatusType playerStatus = player.getStatus();
        StatusType enemyStatus = enemy.getStatus();

        boolean playerLost = (playerStatus == StatusType.DEAD || playerStatus == StatusType.MISSING);
        boolean enemyLost  = (enemyStatus == StatusType.DEAD || enemyStatus == StatusType.MISSING);

        if (playerLost && enemyLost) {
            System.out.println("Both lost?");
            return null;
        } else if (playerLost) {
            System.out.println("Enemy wins!");
            return enemy;
        } else if (enemyLost) {
            System.out.println("Player wins!");
            return player;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("BattlefieldManager { Turn: %d, Player: %s, Enemy: %s }", turnCount, player, enemy);
    }
}
