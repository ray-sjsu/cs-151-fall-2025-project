package rpg.core;

import rpg.characters.Characters;

public interface Usable {
    int use(Characters user, Characters target);

    boolean isUsable(Characters user, Characters target);

    int cooldownRemaining();

    String toString();
}