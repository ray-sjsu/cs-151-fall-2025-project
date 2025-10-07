package rpg.items;

public abstract class Item {

    private int itemId;
    private String name;
    private String description;
    private double weight;
    private RarityType rarity;
    private int slotsTaken;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public RarityType getRarity() {
        return rarity;
    }

    public void setRarity(RarityType rarity) {
        this.rarity = rarity;
    }

    public int getSlotsTaken() {
        return slotsTaken;
    }

    public void setSlotsTaken(int slotsTaken) {
        this.slotsTaken = slotsTaken;
    }

    @Override
    public String toString() {
        return String.format(
                "\n --- Item Details ---" +
                "\n Name: %s" +
                "\n ID: %d" +
                "\n Description: %s" +
                "\n Weight: %.1f" +
                "\n Rarity: %s" +
                "\n Slots Taken: %d",
                this.name, this.itemId, this.description, this.weight, this.rarity, this.slotsTaken);
    }
}