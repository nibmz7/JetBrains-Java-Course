package numbers;

import java.util.ArrayList;
import java.util.Scanner;

enum Filter {
    NONE, EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and a property to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");

        while (true) {
            System.out.print("Enter a request: ");
            long number;
            int repeat = 0;
            Filter filter = Filter.NONE;
            try {
                String input = scanner.nextLine();
                System.out.println();
                if (input.equals("0")) {
                    System.out.println("Goodbye!");
                    return;
                }
                String[] parts = input.split(" ");
                if (parts.length >= 2) {
                    repeat = Integer.parseInt(parts[1]);
                }
                if (parts.length == 3) {
                    String property = parts[2].toUpperCase();
                    try {
                        filter = Filter.valueOf(property);
                        if (filter == Filter.NONE) throw new Exception();
                    } catch (Exception e) {
                        System.out.printf("The property [%s] is wrong.\n", property);
                        System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
                        continue;
                    }
                }
                number = Long.parseLong(parts[0]);
                if (number < 0) throw new Exception("number < 0");
                if (repeat < 0) throw new Exception("second");
                if (repeat > 0) {
                    processConsecutiveNumbers(number, repeat, filter);
                } else processSingleNumber(number);
                System.out.println();
            } catch (Exception e) {
                System.out.printf("\nThe %s parameter should be a natural number or zero.\n", e.getMessage().equals("second") ? "second" : "first");
            }
        }

    }

    static void processConsecutiveNumbers(long input, int repeat, Filter filter) {
        int count = 0;
        int printed = 0;

        while (printed < repeat) {
            long number = input + count;
            count++;

            boolean isEven = isEven(number);
            boolean isBuzz = isBuzz(number);
            boolean isDuck = isDuck(number);
            boolean isPalindromic = isPalindromic(number);
            boolean isGapful = isGapful(number);
            boolean isSpy = isSpy(number);

            ArrayList<String> properties = new ArrayList<String>();

            switch (filter) {
                case EVEN: {
                    if (!isEven) continue;
                    break;
                }
                case ODD: {
                    if (isEven) continue;
                    break;
                }
                case BUZZ: {
                    if (!isBuzz) continue;
                    break;
                }
                case DUCK: {
                    if (!isDuck) continue;
                    break;
                }
                case PALINDROMIC: {
                    if (!isPalindromic) continue;
                    break;
                }
                case GAPFUL: {
                    if (!isGapful) continue;
                    break;
                }
                case SPY: {
                    if (!isSpy) continue;
                    break;
                }
                default:
                    break;
            }

            if (isEven) properties.add("even");
            if (!isEven) properties.add("odd");
            if (isBuzz) properties.add("buzz");
            if (isDuck) properties.add("duck");
            if (isPalindromic) properties.add("palindromic");
            if (isGapful) properties.add("gapful");
            if (isSpy) properties.add("spy");

            String str = String.join(", ", properties);


            System.out.printf("%d is %s\n", number, str);
            printed++;
        }
    }

    static void processSingleNumber(long number) {
        boolean isEven = isEven(number);
        boolean isBuzz = isBuzz(number);
        boolean isDuck = isDuck(number);
        boolean isPalindromic = isPalindromic(number);
        boolean isGapful = isGapful(number);
        boolean isSpy = isSpy(number);

        System.out.printf("\nProperties of %d\n", number);
        System.out.printf("even: %b\n", isEven);
        System.out.printf("odd: %b\n", !isEven);
        System.out.printf("buzz: %b\n", isBuzz);
        System.out.printf("duck: %b\n", isDuck);
        System.out.printf("palindromic: %b\n", isPalindromic);
        System.out.printf("gapful: %b\n", isGapful);
        System.out.printf("spy: %b\n", isSpy);
    }

    static boolean isSpy(long number) {
        String original = String.valueOf(number);
        long addition = 0, multiply = 0;
        boolean firstLoop = true;

        for (char ch : original.toCharArray()) {
            int digit = Integer.parseInt(String.valueOf(ch));
            if (firstLoop) {
                firstLoop = false;
                addition = digit;
                multiply = digit;
            } else {
                addition += digit;
                multiply *= digit;
            }

        }

        return addition == multiply;
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
