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

        while (true) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String action = scanner.next();

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
                case "remaining": {
                    printState(state);
                    break;
                }
                case "exit": {
                    return;
                }
                default:
                    break;
            }
        }


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
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        COFFEE type;

        try {
            type = COFFEE.getType(scanner.nextInt());
        } catch (Exception e) {
            return;
        }

        switch (type) {
            case espresso: {
                makeCoffee(state, 250, 0, 16, 4);
                break;
            }
            case latte: {
                makeCoffee(state, 350, 75, 20, 7);
                break;
            }

            case cappuccino: {
                makeCoffee(state, 200, 100, 12, 6);
                break;
            }
            default:
                break;
        }
    }

    public static void makeCoffee(CoffeeMachineState state, int water, int milk, int beans, int money) {
        if (state.water < water) {
            System.out.println("Sorry, not enough water!");
            return;
        } else if (state.milk < milk) {
            System.out.println("Sorry, not enough milk!");
            return;
        } else if (state.beans < beans) {
            System.out.println("Sorry, not enough beans!");
            return;
        } else if (state.cups <= 0) {
            System.out.println("Sorry, not enough cups!");
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");

        state.water -= water;
        state.milk -= milk;
        state.beans -= beans;
        state.cups--;
        state.money += money;
    }

    public static void take(CoffeeMachineState state) {
        System.out.printf("I gave you $%d\n", state.money);
        state.money = 0;
    }

    public static void printState(CoffeeMachineState state) {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", state.water);
        System.out.printf("%d ml of milk\n", state.milk);
        System.out.printf("%d g of coffee beans\n", state.beans);
        System.out.printf("%d disposable cups\n", state.cups);
        System.out.printf("$%d of money\n", state.money);

    }
}
