package rpg.items;

import java.util.Random;

public class Weapon extends Item implements Usable {

    private int minDamage;
    private int maxDamage;
    private double critChance;
    private int durability;
    private int lastUsedTurn;
    private int actionPointCost;
    private int cooldown;
    private static final Random random = new Random();

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

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getLastUsedTurn() {
        return lastUsedTurn;
    }

    public void setLastUsedTurn(int lastUsedTurn) {
        this.lastUsedTurn = lastUsedTurn;
    }

    public int getActionPointCost() {
        return actionPointCost;
    }

    public void setActionPointCost(int actionPointCost) {
        this.actionPointCost = actionPointCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public void use(Characters user, Characters target) {

        if (!isUsable(user, target)) {
            return;
        }

        int damageDealt = calculateDamage(user, target);

        System.out.printf("\n%s attacks %s with %s dealing %d damage!", user.getName(), target.getName(), this.name, damageDealt);

        target.takeDamage(damageDealt);
        consumeDurability();
        rechargeCooldown();
        user.spendActionPoints(this.actionPointCost);
    }

    @Override
    public boolean isUsable(Characters user, Characters target) {
        if (this.durability == 0) {
            System.out.println(this.name + " is broken and can not be used.");
            return false;
        }

        if (cooldownRemaining() > 0) {
            System.out.println(this.name + " is on cooldown for " + cooldownRemaining() + " more turns.");
            return false;
        }

        if (user.getActionPoints() < this.actionPointCost) {
            System.out.println(user.getName() + " lacks " + this.actionPointCost + " AP to use " + this.name + ".");
            return false;
        }

        return true;
    }

    @Override
    public int cooldownRemaining() {
        int currentTurn = BattlefieldManager.currentTurn();
        int turnAvailable = this.lastUsedTurn + this.cooldown;
        int remaining = turnAvailable - currentTurn;
        return Math.max(0, remaining);
    }

    @Override
    public String toString() {
        String itemDetails = super.toString();
        String weaponDetails =  String.format(
                "\n\n --- Weapon Details ---" +
                "\n Damage: %d - %d" +
                "\n Critical Hit Chance: %.2f%%" +
                "\n Durability: %d" +
                "\n AP Cost: %d" +
                "\n Cooldown: %d turns",
                this.minDamage, this.maxDamage, this.critChance * 100,
                this.durability, this.actionPointCost, this.cooldown);
        return itemDetails + weaponDetails;
    }

    public int calculateDamage(Characters player, Characters target) {
        int baseDamage = random.nextInt(this.maxDamage - this.minDamage + 1) + this.minDamage;
        int strengthBonus = player.getStat(StatType.STRENGTH);
        int totalDamage = baseDamage + strengthBonus;

        if (rollCrit(player, target)) {
            totalDamage = (int) (totalDamage * 1.5);
            System.out.println("CRITICAL HIT! " + player.getName() + " landed a crushing blow!");
        }

        return totalDamage;
    }

    public boolean rollCrit(Characters player, Characters target) {
        double roll = random.nextDouble();
        return roll < this.critChance;
    }

    public void consumeDurability() {
        this.durability--;

        if (this.durability <= 0) {
            this.durability = 0;
            System.out.println(this.name + " has broken and is now useless!");
        }
    }

    public void rechargeCooldown() {
        this.lastUsedTurn = BattlefieldManager.currentTurn();
    }
}