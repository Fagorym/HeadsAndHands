package org.example;

import java.util.Random;

public class Monster extends Creature {
    private static final String[] types = {"Giant", "Orc", "Elf", "Naga", "Wolverine", "Dragon", "Elemental",
            "Undead", "Celestial", "Fiend"};
    private final String type;

    public Monster(int armor, int damage, int health) {
        super(armor, damage, health);
        this.type = types[(new Random().nextInt() & Integer.MAX_VALUE) % types.length];
    }

    @Override
    public Integer attack(Creature attacked) {
        Integer damage = super.attack(attacked);
        if (damage == -1) {
            System.out.println("Монстр не может пробить вашу броню");
        }
        else if (damage == 0) {
            System.out.println("Существо промахнулось");
        } else {
            System.out.println("Существо " + type + " атаковало игрока на " + damage);
        }
        return damage;

    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {

        return "type: " + type + "\n" + super.toString();
    }
}
