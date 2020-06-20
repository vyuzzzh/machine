package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private final Scanner scan;
    private int water;
    private int milk;
    private int beans;
    private int money;
    private int disCups;

    enum Coffee {
        ESPRESSO(250, 0, 16, 4),
        LATTER(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        private final int water;
        private final int milk;
        private final int beans;
        private final int money;

        Coffee(int water, int milk, int beans, int money) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.money = money;
        }

        public int getWater() {
            return this.water;
        }

        public int getMilk() {
            return this.milk;
        }

        public int getBeans() {
            return this.beans;
        }

        public int getMoney() {
            return this.money;
        }
    }

    private CoffeeMachine() {
        this.scan = new Scanner(System.in);
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.disCups = 9;
        this.money = 550;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        String action = coffeeMachine.checkAction();

        while (!action.equals("exit")) {
            action = coffeeMachine.checkAction();
        }
    }

    private String checkAction() {
        String action;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        action = scan.next();
        System.out.println();

        switch (action) {
            case "buy": {
                buyCoffee();
                break;
            }
            case "fill": {
                fillMachine();
                break;
            }
            case "take": {
                takeMoney();
                break;
            }
            case "remaining": {
                printState();
                break;
            }
        }
        return action;
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n", this.water);
        System.out.printf("%d of milk\n", this.milk);
        System.out.printf("%d of coffee beans\n", this.beans);
        System.out.printf("%d of disposable cups\n", this.disCups);
        System.out.printf("%d of money\n", this.money);
        System.out.println();
    }

    private void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String coffee = this.scan.next();

        switch (coffee) {
            case "back": {
                break;
            }
            case "1": {
                this.checkResources(Coffee.ESPRESSO);
                break;
            }
            case "2": {
                this.checkResources(Coffee.LATTER);
                break;
            }
            case "3": {
                this.checkResources(Coffee.CAPPUCCINO);
                break;
            }
        }
    }

    private void fillMachine() {
        System.out.println("Write how many ml of water do you want to add: ");
        int addWater = this.scan.nextInt();
        System.out.println("Write how many ml of milk do you want to add: ");
        int addMilk = this.scan.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add: ");
        int addBeans = this.scan.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        int addDisCups = this.scan.nextInt();
        this.water += addWater;
        this.milk += addMilk;
        this.beans += addBeans;
        this.disCups += addDisCups;
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d\n", this.money);
        this.money = 0;
    }

    private void checkResources(Coffee coffee) {
        if (coffee.getWater() > this.water) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee.getMilk() > this.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffee.getBeans() > this.beans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.disCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else if (coffee.getMoney() > this.money) {
            System.out.println("Sorry, not enough money!");
        } else {
            this.water -= coffee.getWater();
            this.beans -= coffee.getBeans();
            this.milk -= coffee.getMilk();
            this.disCups -= 1;
            this.money += coffee.getMoney();
            System.out.println("I have resources, making you a coffee!");
        }
        System.out.println();
    }
}
