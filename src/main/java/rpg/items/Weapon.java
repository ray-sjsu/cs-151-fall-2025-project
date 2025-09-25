package rpg.items;

public class Weapon extends Item implements Usable {

    private int minDamage;
    private int maxDamage;
    private double critChance;
    private int durability;
    private int lastUsedTurn;
    private int actionPointCost;
    private int cooldown;

    public Weapon(int itemId, String name, String description, double weight, RarityType rarity, int slotsTaken,
                  int minDamage, int maxDamage, double critChance, int durability, int lastUsedTurn,
                  int actionPointCost, int cooldown) {
        super(itemId, name, description, weight, rarity, slotsTaken);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.critChance = critChance;
        this.durability = durability;
        this.lastUsedTurn = lastUsedTurn;
        this.actionPointCost = actionPointCost;
        this.cooldown = cooldown;
    }

    @Override
    public void use(Characters user, Characters target) {

    }

    @Override
    public boolean isUsable(Characters user, Characters target) {

    }

    @Override
    public int cooldownRemaining() {

    }

    @Override
    public String toString() {

    }

    public int calculateDamage(Characters player, Characters target) {

    }

    public boolean rollCrit(Characters player, Characters target) {

    }

    public void consumeDurability() {

    }

    public void rechargeCooldown() {

    }
}