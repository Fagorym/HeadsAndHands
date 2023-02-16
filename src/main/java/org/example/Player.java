package org.example;

import java.util.Random;

public class Player extends Creature {
    private final String name;
    private int potionCount = 3;

    public Player(String name, Integer armor, Integer damage, Integer health) {
        super(armor, damage, health);
        this.name = name;
    }

    public void heal() {
        if (potionCount > 0) {
            int healCount = super.getActualHealth() + super.getMaxHealth() / 2;
            healCount = healCount > super.getMaxHealth() ? super.getMaxHealth() : healCount;
            super.setActualHealth(healCount);
            potionCount--;
            System.out.println("Вы восстановили " + healCount + " здоровья");
            System.out.println("Ваше текущее здоровье " + super.getActualHealth() + " из " + super.getMaxHealth());
        } else {
            System.out.println("У вас недостаточно лечебных зарядов.");
        }
    }

    @Override
    public Integer attack(Creature attacked) {
        Integer damage = super.attack(attacked);
        if (damage == -1) {
            System.out.println("Вы не можете пробить броню монстра");
        } else if (damage == 0) {
            System.out.println("Вы промахнулись");
        } else {
            System.out.println("Вы атаковали монстра на " + damage);
        }
        return damage;

    }

    public void destroyArmor(Creature attacked) {
        System.out.println("Вы снизили броню существа на -1");
        attacked.setArmor(attacked.getArmor() - 1);
    }

    public boolean tryRun() {
        Random random = new Random();
        int score = random.nextInt(100);
        if (score > 80) {
            System.out.println("Вы успешно сбежали.");
            return true;
        } else {
            System.out.println("Вы не смогли сбежать.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + super.toString();
    }
}
