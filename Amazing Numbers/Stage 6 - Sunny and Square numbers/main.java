package numbers;

import java.util.ArrayList;
import java.util.Scanner;

enum Filter {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE
}

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameters show how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and two properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.");

        while (true) {
            System.out.print("\nEnter a request: ");
            long number;
            int repeat = 0;
            try {
                String input = scanner.nextLine();
                System.out.println();
                if (input.equals("0")) {
                    System.out.println("\nGoodbye!");
                    return;
                }
                String[] parts = input.split(" ");
                number = Long.parseLong(parts[0]);
                if (number < 0) {
                    System.out.println("\nThe first parameter should be a natural number or zero.");
                    continue;
                }
                if (parts.length == 1) {
                    processSingleNumber(number);
                } else {
                    repeat = Integer.parseInt(parts[1]);
                    if (repeat < 0) {
                        System.out.println("\nThe second parameter should be a natural number or zero.");
                        continue;
                    }
                }
                if (parts.length == 2) {
                    processConsecutiveNumbers(number, repeat);
                } else if (parts.length == 3) {
                    String property = parts[2].toUpperCase();
                    try {
                        Filter filter = Filter.valueOf(property);
                        processConsecutiveNumbers(number, repeat, filter);
                    } catch (Exception e) {
                        System.out.printf("\nThe property [%s] is wrong.\n", property);
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                        continue;
                    }

                } else if (parts.length == 4) {
                    Filter filter1 = null, filter2 = null;
                    String property1 = parts[2].toUpperCase();
                    String property2 = parts[3].toUpperCase();
                    boolean isFirstValid, isSecondValid;
                    try {
                        filter1 = Filter.valueOf(property1);
                        isFirstValid = true;
                    } catch (Exception e) {
                        isFirstValid = false;
                    }
                    try {
                        filter2 = Filter.valueOf(property2);
                        isSecondValid = true;
                    } catch (Exception e) {
                        isSecondValid = false;
                    }
                    if (!isFirstValid || !isSecondValid) {
                        if (!isFirstValid && !isSecondValid) {
                            System.out.printf("\nThe properties [%s, %s] are wrong.\n", property1, property2);
                        } else {
                            System.out.printf("\nThe property [%s] is wrong.\n", !isFirstValid ? property1 : property2);
                        }
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
                        continue;
                    }

                    if (isMutuallyExclusive(filter1, filter2)) {
                        System.out.printf("\nThe request contains mutually exclusive properties: [%s, %s]\n", property1, property2);
                    } else {
                        processConsecutiveNumbers(number, repeat, filter1, filter2);
                    }

                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        }
    }

    static void processConsecutiveNumbers(long input, int repeat, Filter... filters) {
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
            boolean isSunny = isSunny(number);
            boolean isSquare = isSquare(number);

            ArrayList<String> properties = new ArrayList<String>();
            int passCount = 0;

            for (Filter filter : filters) {
                switch (filter) {
                    case EVEN: {
                        if (isEven) passCount++;
                        break;
                    }
                    case ODD: {
                        if (!isEven) passCount++;
                        break;
                    }
                    case BUZZ: {
                        if (isBuzz) passCount++;
                        break;
                    }
                    case DUCK: {
                        if (isDuck) passCount++;
                        break;
                    }
                    case PALINDROMIC: {
                        if (isPalindromic) passCount++;
                        break;
                    }
                    case GAPFUL: {
                        if (isGapful) passCount++;
                        break;
                    }
                    case SPY: {
                        if (isSpy) passCount++;
                        break;
                    }
                    case SUNNY: {
                        if (isSunny) passCount++;
                        break;
                    }
                    case SQUARE: {
                        if (isSquare) passCount++;
                        break;
                    }
                    default:
                        break;
                }
            }

            if (passCount != filters.length) continue;


            if (isEven) properties.add("even");
            if (!isEven) properties.add("odd");
            if (isBuzz) properties.add("buzz");
            if (isDuck) properties.add("duck");
            if (isPalindromic) properties.add("palindromic");
            if (isGapful) properties.add("gapful");
            if (isSpy) properties.add("spy");
            if (isSquare) properties.add("square");
            if (isSunny) properties.add("sunny");

            String str = String.join(", ", properties);


            System.out.printf("%d is %s\n", number, str);
            printed++;
        }

        if (printed == 0) {
            System.out.println("\nThere are no numbers with these properties.");
        }
    }

    static void processSingleNumber(long number) {
        boolean isEven = isEven(number);
        boolean isBuzz = isBuzz(number);
        boolean isDuck = isDuck(number);
        boolean isPalindromic = isPalindromic(number);
        boolean isGapful = isGapful(number);
        boolean isSpy = isSpy(number);
        boolean isSunny = isSunny(number);
        boolean isSquare = isSquare(number);

        System.out.printf("\nProperties of %d\n", number);
        System.out.printf("even: %b\n", isEven);
        System.out.printf("odd: %b\n", !isEven);
        System.out.printf("buzz: %b\n", isBuzz);
        System.out.printf("duck: %b\n", isDuck);
        System.out.printf("palindromic: %b\n", isPalindromic);
        System.out.printf("gapful: %b\n", isGapful);
        System.out.printf("spy: %b\n", isSpy);
        System.out.printf("sunny: %b\n", isSunny);
        System.out.printf("square: %b\n", isSquare);
    }

    static boolean isMutuallyExclusive(Filter filter1, Filter filter2) {
        boolean hasEven = filter1 == Filter.EVEN || filter2 == Filter.EVEN;
        boolean hasOdd = filter1 == Filter.ODD || filter2 == Filter.ODD;
        boolean hasDuck = filter1 == Filter.DUCK || filter2 == Filter.DUCK;
        boolean hasSpy = filter1 == Filter.SPY || filter2 == Filter.SPY;
        boolean hasSunny = filter1 == Filter.SUNNY || filter2 == Filter.SUNNY;
        boolean hasSquare = filter1 == Filter.SQUARE || filter2 == Filter.SQUARE;
        return hasEven && hasOdd || hasDuck && hasSpy || hasSunny && hasSquare;
    }

    static boolean isSquare(long number) {
        return Math.sqrt(number) % 1 == 0;
    }

    static boolean isSunny(long number) {
        return isSquare(number + 1);
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
