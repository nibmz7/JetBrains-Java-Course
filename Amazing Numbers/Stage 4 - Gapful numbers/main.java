package numbers;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n");

        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        while (true) {
            System.out.print("Enter a request: ");
            long number;
            int repeat = 0;
            try {
                String input = scanner.nextLine();
                if (input.equals("0")) {
                    System.out.println("Goodbye!");
                    return;
                }
                String[] parts = input.split(" ");
                if (parts.length == 2) {
                    repeat = Integer.parseInt(parts[1]);
                }
                number = Long.parseLong(parts[0]);
                if (number < 0) throw new Exception("number < 0");
                if (repeat < 0) throw new Exception("second");
                if (repeat > 0) {
                    processConsecutiveNumbers(number, repeat);
                } else processSingleNumber(number);
                System.out.println();
            } catch (Exception e) {
                System.out.printf("\nThe %s parameter should be a natural number or zero.\n", e.getMessage().equals("second") ? "second" : "first");
            }
        }

    }

    static void processConsecutiveNumbers(long input, int repeat) {
        for (int i = 0; i < repeat; i++) {
            long number = input + i;

            boolean isEven = isEven(number);
            boolean isBuzz = isBuzz(number);
            boolean isDuck = isDuck(number);
            boolean isPalindromic = isPalindromic(number);
            boolean isGapful = isGapful(number);

            ArrayList<String> properties = new ArrayList<String>();

            if (isEven) properties.add("even");
            if (!isEven) properties.add("odd");
            if (isBuzz) properties.add("buzz");
            if (isDuck) properties.add("duck");
            if (isPalindromic) properties.add("palindromic");
            if (isGapful) properties.add("gapful");

            String str = String.join(", ", properties);

            System.out.printf("%d is %s\n", number, str);
        }
    }

    static void processSingleNumber(long number) {
        boolean isEven = isEven(number);
        boolean isBuzz = isBuzz(number);
        boolean isDuck = isDuck(number);
        boolean isPalindromic = isPalindromic(number);
        boolean isGapful = isGapful(number);

        System.out.printf("\nProperties of %d\n", number);
        System.out.printf("even: %b\n", isEven);
        System.out.printf("odd: %b\n", !isEven);
        System.out.printf("buzz: %b\n", isBuzz);
        System.out.printf("duck: %b\n", isDuck);
        System.out.printf("palindromic: %b\n", isPalindromic);
        System.out.printf("gapful: %b\n", isGapful);
    }

    static boolean isGapful(long number) {
        String original = String.valueOf(number);
        if (original.length() <= 2) return false;
        int divisor = Integer.parseInt("" + original.charAt(0) + original.charAt(original.length() - 1));

        return number % divisor == 0;
    }


    static boolean isPalindromic(long number) {
        String original = String.valueOf(number);
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
