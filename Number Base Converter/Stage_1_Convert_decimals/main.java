package converter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number in decimal system:");
        int numberInput = Integer.parseInt(scanner.next());

        System.out.print("Enter target base:");
        int baseInput = Integer.parseInt(scanner.next());

        String result = parseNumber(numberInput, baseInput);
        System.out.print("Conversion result: " + result);
    }

    static char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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
