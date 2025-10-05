package rpg.items;

public class Inventory {

    private int slotCapacity;
    private List<Items> items;

    public Inventory(int slotCapacity) {
        this.slotCapacity = slotCapacity;
        this.items = new ArrayList<>();
    }

    public int getSlotCapacity() {
        return slotCapacity;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setSlotCapacity(int slotCapacity) {
        this.slotCapacity = slotCapacity;
    }

    public void addItem(Item item) throws InventoryFullException {

    }

    public void removeItem(int itemId) {

    }

    public boolean hasSpaceFor(Item item) {

    }

    public Item findById(int itemId) {

    }

    public double totalWeight() {

    }

    @Override
    public String toString() {

    }
}