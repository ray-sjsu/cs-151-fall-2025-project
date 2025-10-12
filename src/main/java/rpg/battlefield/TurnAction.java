package rpg.battlefield;

import rpg.characters.Characters;
import rpg.core.ActionType;
import rpg.core.StatusType;
import rpg.exceptions.MaxInstancesLimitException;

import java.util.Objects;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public class TurnAction {
    private final Characters actor;
    private final ActionType actionType;
    private final StatusType statusType;
    private final Object payload;
    private static int instanceCount;

    public TurnAction(Characters actor, ActionType actionType, StatusType statusType, Object payload) {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;

        this.actor = Objects.requireNonNull(actor, "actor cannot be null");
        this.actionType = Objects.requireNonNull(actionType, "actionType cannot be null");
        this.statusType = Objects.requireNonNull(statusType, "statusType cannot be null");
        this.payload = payload;
    }

    public Characters actor() {
        return actor;
    }

    public ActionType actionType() {
        return actionType;
    }

    public Object payload() {
        return payload;
    }

    @Override
    public String toString() {
        return String.format("\n***actor: %s\n ***actionType: %s\n ***statusType: %s\n ***payload: %s\n", actor, actionType, statusType, payload);
    }
}
