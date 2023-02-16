package org.example;

import java.util.Random;

public class Creature {
    private final Integer damage;
    private final Integer maxHealth;
    private Integer armor;
    private Integer actualHealth;

    public Creature(Integer armor, Integer damage, Integer maxHealth) {
        this.armor = armor;
        this.damage = damage;
        this.maxHealth = this.actualHealth = maxHealth;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public Integer getActualHealth() {
        return actualHealth;
    }

    public void setActualHealth(int health) {
        if (health >= 0 && health <= maxHealth) {
            actualHealth = health;
        }

    }

    public Integer attack(Creature attacked) {
        int modifier = this.damage - (attacked.getArmor()) + 1;
        if (modifier <= 0) {
            return -1;
        }
        int actualDamage = 0;
        boolean isAttackSuccess = new Random()
                .ints(modifier)
                .map(x -> ((x & Integer.MAX_VALUE) % 6) + 1)
                .anyMatch(x -> x == 5 || x == 6);
        if (isAttackSuccess) {
            actualDamage = new Random().nextInt(this.damage) + 1;
            attacked.actualHealth -= actualDamage;
        }
        return actualDamage;

    }

    @Override
    public String toString() {
        return "Max Health: " + maxHealth + "\n" +
                "Actual Health " + actualHealth + "\n" +
                "Armor: " + armor + "\n" +
                "Damage: " + damage + "\n";
    }
}
