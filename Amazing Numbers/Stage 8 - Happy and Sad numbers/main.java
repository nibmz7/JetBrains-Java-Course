package numbers;

import java.util.*;

enum Filter {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING, SAD, HAPPY
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
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
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
                    processConsecutiveNumbers(number, repeat, Collections.emptyList(), Collections.emptyList());
                } else if (parts.length >= 3) {
                    List<Filter> include = new ArrayList<>();
                    List<Filter> exclude = new ArrayList<>();

                    ArrayList<String> invalidInclude = new ArrayList<>();
                    ArrayList<String> invalidExclude = new ArrayList<>();

                    for (int i = 2; i < parts.length; i++) {
                        String property = parts[i].toUpperCase();
                        boolean isExclude = property.contains("-");
                        try {
                            if (isExclude) {
                                Filter filter = Filter.valueOf(property.split("-")[1]);
                                exclude.add(filter);
                            } else {
                                Filter filter = Filter.valueOf(property);
                                include.add(filter);
                            }

                        } catch (Exception e) {
                            if (isExclude) {
                                invalidExclude.add(property);
                            } else invalidInclude.add(property);
                        }
                    }

                    invalidInclude.addAll(invalidExclude);
                    if (invalidInclude.size() > 0) {
                        if (invalidInclude.size() == 1) {
                            System.out.printf("\nThe property [%s] is wrong.", invalidInclude.get(0));
                        } else {
                            String str = String.join(", ", invalidInclude);
                            System.out.printf("\nThe properties [%s] are wrong.", str);
                        }
                        System.out.println("\nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
                    } else if (!isMutuallyExclusive(include, exclude)) {
                        processConsecutiveNumbers(number, repeat, include, exclude);
                    }
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }
        }
    }

    static void processConsecutiveNumbers(long input, int repeat, List<Filter> include, List<Filter> exclude) {
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
            boolean isHappy = isHappy(number);
            boolean isSad = isSad(number);

            ArrayList<String> properties = new ArrayList<String>();
            int passCount = 0;

            for (Filter filter : include) {
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
                    case SAD: {
                        if (isSad) passCount++;
                        break;
                    }
                    case HAPPY: {
                        if (isHappy) passCount++;
                        break;
                    }
                    default:
                        break;
                }
            }

            for (Filter filter : exclude) {
                switch (filter) {
                    case EVEN: {
                        if (!isEven) passCount++;
                        break;
                    }
                    case ODD: {
                        if (isEven) passCount++;
                        break;
                    }
                    case BUZZ: {
                        if (!isBuzz) passCount++;
                        break;
                    }
                    case DUCK: {
                        if (!isDuck) passCount++;
                        break;
                    }
                    case PALINDROMIC: {
                        if (!isPalindromic) passCount++;
                        break;
                    }
                    case GAPFUL: {
                        if (!isGapful) passCount++;
                        break;
                    }
                    case SPY: {
                        if (!isSpy) passCount++;
                        break;
                    }
                    case SUNNY: {
                        if (!isSunny) passCount++;
                        break;
                    }
                    case SQUARE: {
                        if (!isSquare) passCount++;
                        break;
                    }
                    case JUMPING: {
                        if (!isJumping) passCount++;
                        break;
                    }
                    case SAD: {
                        if (!isSad) passCount++;
                        break;
                    }
                    case HAPPY: {
                        if (!isHappy) passCount++;
                        break;
                    }
                    default:
                        break;
                }
            }

            if (passCount != include.size() + exclude.size()) continue;


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
            if (isHappy) properties.add("happy");
            if (isSad) properties.add("sad");

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
        boolean isHappy = isHappy(number);
        boolean isSad = isSad(number);

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
        System.out.printf("happy: %b\n", isHappy);
        System.out.printf("sad: %b\n", isSad);
    }

    static boolean isMutuallyExclusive(List<Filter> include, List<Filter> exclude) {
        for (Filter filter : include) {
            if (exclude.contains(filter)) {
                System.out.printf("The request contains mutually exclusive properties: [%s, %s]", filter, filter);
                return true;
            }
        }

        boolean hasEven = include.contains(Filter.EVEN) || exclude.contains(Filter.ODD);
        boolean hasOdd = include.contains(Filter.ODD) || exclude.contains(Filter.EVEN);
        boolean hasDuck = include.contains(Filter.DUCK) || exclude.contains(Filter.SPY);
        boolean hasSpy = include.contains(Filter.SPY) || exclude.contains(Filter.DUCK);
        boolean hasSunny = include.contains(Filter.SUNNY) || exclude.contains(Filter.SQUARE);
        boolean hasSquare = include.contains(Filter.SQUARE) || exclude.contains(Filter.SUNNY);
        boolean hasSad = include.contains(Filter.SAD) || exclude.contains(Filter.HAPPY);
        boolean hasHappy = include.contains(Filter.HAPPY) || exclude.contains(Filter.SAD);

        if (hasEven && hasOdd) {
            String even = include.contains(Filter.EVEN) ? "EVEN" : "-ODD";
            String odd = include.contains(Filter.ODD) ? "ODD" : "-EVEN";
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]", even, odd);
            return true;
        } else if (hasDuck && hasSpy) {
            String duck = include.contains(Filter.DUCK) ? "DUCK" : "-SPY";
            String spy = include.contains(Filter.SPY) ? "SPY" : "-DUCK";
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]", duck, spy);
            return true;
        } else if (hasSunny && hasSquare) {
            String sunny = include.contains(Filter.SUNNY) ? "SUNNY" : "-SQUARE";
            String square = include.contains(Filter.SQUARE) ? "SQUARE" : "-SUNNY";
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]", sunny, square);
            return true;
        } else if (hasSad && hasHappy) {
            String sad = include.contains(Filter.SAD) ? "SAD" : "-HAPPY";
            String happy = include.contains(Filter.HAPPY) ? "HAPPY" : "-SAD";
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]", sad, happy);
            return true;
        }

        return false;
    }

    static boolean isSad(long number) {
        return !isHappy(number);
    }

    static boolean isHappy(long number) {
        String str = String.valueOf(number);
        List<Long> visited = new ArrayList<>();
        visited.add(number);

        while (true) {
            long sum = 0;

            for (char ch : str.toCharArray()) {
                int digit = Integer.parseInt(String.valueOf(ch));
                sum += (long) digit * digit;
            }

            if (sum == 1) {
                return true;
            }
            
            if (visited.contains(sum)) return false;
            visited.add(sum);
            str = String.valueOf(sum);
        }

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
