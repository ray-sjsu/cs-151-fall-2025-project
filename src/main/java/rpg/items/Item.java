package rpg.items;

import rpg.core.RarityType;
import rpg.exceptions.MaxInstancesLimitException;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public class Item {
    protected int itemId;
    protected String name;
    protected String description;
    protected double weight;
    protected RarityType rarity;
    protected static int instanceCount;

    public Item(int itemId, String name, String description, double weight, RarityType rarity) {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;

        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.rarity = rarity;
    }

    public int getItemId() { return itemId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public RarityType getRarity() { return rarity; }

    @Override
    public String toString() {
        return String.format("%s (%s) - %.2f lbs - Description: %s", name, rarity, weight, description);
    }
}