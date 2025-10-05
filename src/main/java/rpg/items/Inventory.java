package rpg.items;

public class Inventory {

    private int slotCapacity;
    private List<Item> items;

    public Inventory(int slotCapacity) {
        this.slotCapacity = slotCapacity;
        this.items = new ArrayList<>();
    }

    public int getSlotCapacity() {
        return slotCapacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public void addItem(Item item) throws InventoryFullException {
        if (!hasSpaceFor(item)) {
            throw new InventoryFullException("Inventory is full.");
        }

        items.add(item);
    }

    public void removeItem(int itemId) {
        items.removeIf(item -> item.getItemId() == itemId);
    }

    public boolean hasSpaceFor(Item item) {
        int occupiedSlots = 0;
        for (Item currentItem : this.items) {
            occupiedSlots += currentItem.getSlotsTaken();
        }

        int availableSlots = this.slotCapacity - occupiedSlots;
        return availableSlots >= item.getSlotsTaken();
    }

    public Item findById(int itemId) {
        for (Item item : this.items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }

        return null;
    }

    public double totalWeight() {
        double weight = 0.0;

        for (Item item : this.items) {
            weight += item.getWeight();
        }

        return weight;
    }

    @Override
    public String toString() {
        String header = String.format("Inventory \n Slot Capacity: %d", slotCapacity);
        StringBuilder itemList = new StringBuilder();
        itemList.append("Items:\n");

        for(Item item : this.items) {
            itemList.append("- ").append(item.getName()).append("\n");
        }

        return header + "\n\n" + itemList;
}