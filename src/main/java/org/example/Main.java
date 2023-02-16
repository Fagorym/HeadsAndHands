package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Добрый день, хотите ли вы сыграть в мою небольшую игру?");
        System.out.println("Ее смысл состоит в том, чтобы попробовать свои силы в схватках с монстрами.");
        System.out.println("1. Начать игру");
        System.out.println("2. Выйти");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1 -> startGame(scanner);
            case 2 -> System.out.println("Хорошо, увидимся в следующий раз! :)");

            default -> System.out.println("Неизвестная опция.");
        }


    }


    private static void startGame(Scanner scanner) {
        System.out.println("Тогда тебе нужно придумать имя своему персонажу");
        String name = scanner.next();
        System.out.println("Тогда знакомься. Это твой персонаж. ");
        Player hero = new Player(name, 15, 15, 20);
        System.out.println(hero);
        int scoreCounter = 1;
        while (hero.getActualHealth() > 0) {
            Monster monster = generateMonster(scoreCounter);
            fight(hero, monster, scanner);
        }
        System.out.println("Ваш счетчик убийств: " + scoreCounter);
    }

    private static void fight(Player player, Monster monster, Scanner scanner) {
        boolean ran = false;
        boolean canRun = true;
        while (!ran) {
            System.out.println("Выберите действие:");
            System.out.println("1. Ударить монстра");
            System.out.println("2. Выпить лечебное зелье");
            if (canRun) {
                System.out.println("3. Бежать");
            }
            System.out.println("4. Пробить броню монстра");
            System.out.println("5. Общее состояние");
            switch (scanner.nextInt()) {
                case 1 -> {
                    int result = attackStage(player, monster);
                    if (result != 0) {
                        return;
                    }
                }
                case 2 -> player.heal();
                case 3 -> {
                    ran = player.tryRun();
                    canRun = false;
                }

                case 4 -> {
                    player.destroyArmor(monster);
                    monster.attack(player);
                    if (player.getActualHealth() <= 0) {
                        System.out.println("Вы убиты");
                    }
                }

                case 5 -> {
                    getState(player);
                    getState(monster);
                }

                default -> System.out.println("Неверная команда");
            }
        }
    }


    private static Monster generateMonster(int scoreCounter) {
        Random random = new Random();
        int armor = random.nextInt(20) + 1;
        int damage = random.nextInt(20) + 1;
        int health = random.nextInt(20 + scoreCounter) + 1;
        Monster monster = new Monster(armor, damage, health);
        System.out.println("Вы встретили монстра, вот его характеристики:");
        System.out.println(monster);
        return monster;
    }

    private static Integer attackStage(Player player, Monster monster) {
        player.attack(monster);
        if (monster.getActualHealth() <= 0) {
            System.out.println("Вы убили монстра " + monster.getType());
            return 1;
        } else {
            monster.attack(player);
            if (player.getActualHealth() <= 0) {
                System.out.println("Вас убил монстр");
                return -1;
            }
            return 0;
        }

    }

    private static void getState(Creature creature) {
        System.out.println("Текущее состояние:");
        System.out.println(creature);
    }

}