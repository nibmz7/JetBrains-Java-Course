package converter;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
        String nextMove = scanner.next();

        while (!nextMove.equals("/exit")) {
            if (nextMove.equals("/from")) convertFrom();
            else convertTo();
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            nextMove = scanner.next();
            System.out.println("");
        }


    }

    static char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    static void convertTo() {
        System.out.print("Enter source number: ");
        String numberInput = scanner.next();

        System.out.print("Enter source base: ");
        int baseInput = scanner.nextInt();

        int result = parseString(numberInput, baseInput);
        System.out.println("Conversion to decimal result: " + result);
    }

    static int parseString(String number, int base) {
        int total = 0;

        for (int i = 0; i < number.length(); i++) {
            int index = number.length() - i - 1;
            char digit = Character.toUpperCase(number.charAt(i));
            for (int n = 0; n < symbols.length; n++) {
                if (symbols[n] == digit) {
                    double value = n * Math.pow(base, index);
                    total += value;
                    break;
                }
            }
        }

        return total;
    }

    static void convertFrom() {
        System.out.print("Enter number in decimal system: ");
        int numberInput = scanner.nextInt();

        System.out.print("Enter target base: ");
        int baseInput = scanner.nextInt();

        String result = parseNumber(numberInput, baseInput);
        System.out.println("Conversion result: " + result);
    }

    static String parseNumber(int decimal, int radix) {
        if (decimal == 0) return "0";

        int quotient = decimal;
        StringBuilder result = new StringBuilder();

        while (quotient != 0) {
            int remainder = quotient % radix;
            quotient = quotient / radix;
            result.insert(0, symbols[remainder]);
        }

        return result.toString();
    }
}
