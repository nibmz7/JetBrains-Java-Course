package numbers;

import java.util.*;

enum Filter {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING
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
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
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
                    processConsecutiveNumbers(number, repeat, Collections.emptyList());
                } else if (parts.length >= 3) {
                    List<Filter> filters = new ArrayList<>();
                    ArrayList<String> invalidFilters = new ArrayList<>();
                    for (int i = 2; i < parts.length; i++) {
                        String property = parts[i].toUpperCase();
                        try {
                            Filter filter = Filter.valueOf(property);
                            filters.add(filter);
                        } catch (Exception e) {
                            invalidFilters.add(property);
                        }
                    }

                    if (invalidFilters.size() > 0) {
                        if (invalidFilters.size() == 1) {
                            System.out.printf("\nThe property [%s] is wrong.", invalidFilters.get(0));
                        } else {
                            String str = String.join(", ", invalidFilters);
                            System.out.printf("\nThe properties [%s] are wrong.", str);
                        }
                        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
                    } else if (!isMutuallyExclusive(filters)) {
                        processConsecutiveNumbers(number, repeat, filters);
                    }
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        }
    }

    static void processConsecutiveNumbers(long input, int repeat, List<Filter> filters) {
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
            boolean isJumping = isJumping(number);

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
                    case JUMPING: {
                        if (isJumping) passCount++;
                        break;
                    }
                    default:
                        break;
                }
            }

            if (passCount != filters.size()) continue;


            if (isEven) properties.add("even");
            if (!isEven) properties.add("odd");
            if (isBuzz) properties.add("buzz");
            if (isDuck) properties.add("duck");
            if (isPalindromic) properties.add("palindromic");
            if (isGapful) properties.add("gapful");
            if (isSpy) properties.add("spy");
            if (isSquare) properties.add("square");
            if (isSunny) properties.add("sunny");
            if (isJumping) properties.add("jumping");

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
        boolean isJumping = isJumping(number);

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
        System.out.printf("jumping: %b\n", isJumping);
    }

    static boolean isMutuallyExclusive(List<Filter> filters) {

        boolean hasEven = filters.contains(Filter.EVEN);
        boolean hasOdd = filters.contains(Filter.ODD);
        boolean hasDuck = filters.contains(Filter.DUCK);
        boolean hasSpy = filters.contains(Filter.SPY);
        boolean hasSunny = filters.contains(Filter.SUNNY);
        boolean hasSquare = filters.contains(Filter.SQUARE);

        if (hasEven && hasOdd) {
            System.out.println("\nThe request contains mutually exclusive properties: [EVEN, ODD]");
            return true;
        } else if (hasDuck && hasSpy) {
            System.out.println("\nThe request contains mutually exclusive properties: [DUCK, SPY]");
            return true;
        } else if (hasSunny && hasSquare) {
            System.out.println("\nThe request contains mutually exclusive properties: [SUNNY, SQUARE]");
            return true;
        }

        return false;
    }

    static boolean isJumping(long number) {
        int prev = 0;
        boolean firstLoop = true;

        String original = String.valueOf(number);
        for (char ch : original.toCharArray()) {
            int digit = Integer.parseInt(String.valueOf(ch));
            if (firstLoop) {
                firstLoop = false;
            } else {
                int diff = Math.abs(prev - digit);
                if (diff != 1) return false;
            }
            prev = digit;

        }

        return true;
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
