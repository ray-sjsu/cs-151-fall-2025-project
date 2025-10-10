package rpg.battlefield;

import java.util.Objects;
import rpg.core.ActionType;
import rpg.characters.Characters;

public class TurnAction {
    private Characters actor;
    private ActionType type;
    private Object payload;

    public TurnAction(Characters actor, ActionType type, Object payload) {
        this.actor = Objects.requireNonNull(actor, "actor");
        this.type = Objects.requireNonNull(type, "type");
        this.payload = payload;
    }

    public Characters actor() {
        return actor;
    }
    public ActionType type() {
        return type;
    }
    public Object payload() {
        return payload;
    }

    @Override
    public String toString() {
        String who = (actor == null) ? "?" : actor.getName();
        String what = (type == null) ? "?" : type.name();
        return "TurnAction(" + who + " -> " + what +
                (payload != null ? ", payload=" + payload : "") + ")";
    }
}