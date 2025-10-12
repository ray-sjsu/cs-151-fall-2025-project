package rpg.exceptions;

import rpg.items.Item;

public class InventoryFullException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Inventory is full.";

    public InventoryFullException() {
        super(DEFAULT_MESSAGE);
    }

    public InventoryFullException(String message) {
        super(message);
    }

    public InventoryFullException(Item item) {
        super("Inventory is full! Cannot pick up: " + item.getName());
    }

}