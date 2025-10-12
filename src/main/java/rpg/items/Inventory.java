package rpg.items;

import rpg.exceptions.InventoryFullException;
import rpg.exceptions.MaxInstancesLimitException;

import java.util.ArrayList;
import java.util.List;

import static rpg.exceptions.MaxInstancesLimitException.CLASS_INSTANCE_LIMIT;

public class Inventory {
    private int slotCapacity;
    private final List<Item> items;
    private static int instanceCount;

    public Inventory(int slotCapacity) {
        if (CLASS_INSTANCE_LIMIT <= instanceCount) {
            throw new MaxInstancesLimitException(this.getClass().getSimpleName());
        }
        instanceCount++;

        this.slotCapacity = slotCapacity;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (items.size() >= slotCapacity) {
            throw new InventoryFullException("Inventory is full! Cannot add " + item.getName());
        }
        items.add(item);
        System.out.printf("Added %s to inventory.%n", item.getName());
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public double totalWeight() {
        return items.stream().mapToDouble(Item::getWeight).sum();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "[Empty Inventory]";
        StringBuilder sb = new StringBuilder("Inventory:\n");
        for (Item i : items) {
            sb.append(" - ").append(i).append("\n");
        }
        sb.append(String.format("Total Weight: %.2f lbs (%d/%d slots)%n",
                totalWeight(), items.size(), slotCapacity));
        return sb.toString();
    }
}