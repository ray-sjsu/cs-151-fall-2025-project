package rpg.battlefield;

import java.util.Queue;
import rpg.battlefield;
import rpg.characters;

class BattlefieldManager {
    private int turnCount;
    private Queue<TurnAction> turnOrder;

    public void initCombat(PlayableCharacter player, Enemy enemy) {
        // todo
    }

    public Characters currentActor() {
        return null;
    }

    public Characters nextTurn() {
        return null;
    }

    public boolean isBattleOver() {
        return false;
    }

    public int currentTurn() {
        return 0;
    }

    public String winner() {
        // todo
    }

    @Override
    public String toString() {
        return "";
    }
}