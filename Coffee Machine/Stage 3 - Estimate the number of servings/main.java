package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int water, milk, beans, requiredCups;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        water = scanner.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        milk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        beans = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need:");
        requiredCups = scanner.nextInt();

        int availableCups = estimateServings(0, water, milk, beans);

        if (availableCups >= requiredCups) {
            System.out.print("Yes, I can make that amount of coffee");
            int extraCups = availableCups - requiredCups;
            if (extraCups > 0) {
                System.out.printf(" (and even %d more than that)", extraCups);
            }
        } else System.out.printf("No, I can make only %d cup(s) of coffee", availableCups);

    }

    public static int estimateServings(int count, int water, int milk, int beans) {
        int remainingWater = water - 200;
        int remainingMilk = milk - 50;
        int remainingBeans = beans - 15;

        if (remainingWater >= 0 && remainingMilk >= 0 && remainingBeans >= 0) {
            return estimateServings(count + 1, remainingWater, remainingMilk, remainingBeans);
        } else return count;
    }
}
