package machine;

import java.util.Scanner;

enum COFFEE {
    espresso,
    latte,
    cappuccino;

    public static COFFEE getType(int type) {
        switch (type) {
            case 1:
                return COFFEE.espresso;
            case 2:
                return COFFEE.latte;
            default:
                return COFFEE.cappuccino;

        }
    }
}

class CoffeeMachineState {
    int water;
    int milk;
    int beans;
    int cups;
    int money;


    public CoffeeMachineState(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }
}

public class CoffeeMachine {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        CoffeeMachineState state = new CoffeeMachineState(400, 540, 120, 9, 550);

        printState(state);

        System.out.println("Write action (buy, fill, take):");
        String action = scanner.nextLine();
        switch (action) {
            case "buy": {
                buy(state);
                break;
            }
            case "fill": {
                fill(state);
                break;
            }
            case "take": {
                take(state);
                break;
            }
            default:
                break;
        }

        printState(state);
    }

    public static void fill(CoffeeMachineState state) {
        System.out.println("Write how many ml of water you want to add:");
        state.water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        state.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        state.beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        state.cups += scanner.nextInt();
    }

    public static void buy(CoffeeMachineState state) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        COFFEE type = COFFEE.getType(scanner.nextInt());

        switch (type) {
            case espresso: {
                state.water -= 250;
                state.beans -= 16;
                state.money += 4;
                state.cups--;
                break;
            }
            case latte: {
                state.water -= 350;
                state.milk -= 75;
                state.beans -= 20;
                state.money += 7;
                state.cups--;
                break;
            }

            case cappuccino: {
                state.water -= 200;
                state.milk -= 100;
                state.beans -= 12;
                state.money += 6;
                state.cups--;
                break;
            }
            default:
                break;
        }
    }

    public static void take(CoffeeMachineState state) {
        System.out.printf("I gave you $%d", state.money);
        state.money = 0;
    }

    public static void printState(CoffeeMachineState state) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", state.water);
        System.out.printf("%d ml of milk\n", state.milk);
        System.out.printf("%d g of coffee beans\n", state.beans);
        System.out.printf("%d disposable cups\n", state.cups);
        System.out.printf("$%d of money\n", state.money);

    }
}
