package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.\n");

        while (true) {
            System.out.print("Enter a request:");
            long number;
            try {
                number = scanner.nextLong();
                if (number < 0) throw new Exception("Un-natural number");
                if (number == 0) {
                    System.out.println("Goodbye!");
                    return;
                }
            } catch (Exception e) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
                continue;
            }

            boolean isEven = isEven(number);
            boolean isBuzz = isBuzz(number);
            boolean isDuck = isDuck(number);
            boolean isPalindromic = isPalindromic(number);

            System.out.printf("\nProperties of %d\n", number);
            System.out.printf("even: %b\n", isEven);
            System.out.printf("odd: %b\n", !isEven);
            System.out.printf("buzz: %b\n", isBuzz);
            System.out.printf("duck: %b\n", isDuck);
            System.out.printf("palindromic: %b\n", isPalindromic);
        }


    }

    static boolean isPalindromic(long number) {
        String original =  String.valueOf(number);
        StringBuilder reverse = new StringBuilder();
        reverse.append(original);
        reverse.reverse();
        return reverse.toString().equals(original);
    }

    static boolean isDuck(long number) {
        return String.valueOf(number).contains("0");
    }

    static boolean isBuzz(long number) {
        boolean isDivisibleBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        return isDivisibleBy7 || endsWith7;
    }

    static boolean isEven(long number) {
        return number % 2 == 0;
    }
}
