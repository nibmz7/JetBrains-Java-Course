package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number;
        try {
            number = scanner.nextInt();
            if(number <= 0) throw new Exception("Un-natural number");
        } catch (Exception e) {
            System.out.println("This number is not natural!");
            return;
        }

        boolean isEven = isEven(number);
        boolean isBuzz = isBuzz(number);
        boolean isDuck = isDuck(number);

        System.out.printf("Properties of %d\n", number);
        System.out.printf("even: %b\n", isEven);
        System.out.printf("odd: %b\n", !isEven);
        System.out.printf("buzz: %b\n", isBuzz);
        System.out.printf("duck: %b", isDuck);

    }

    static boolean isDuck(int number) {
        return String.valueOf(number).contains("0");
    }

    static boolean isBuzz(int number) {
        boolean isDivisibleBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        return isDivisibleBy7 || endsWith7;
    }

    static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
