package converter;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
            String choice = scanner.nextLine();
            if (choice.isEmpty()) continue;
            else if (choice.equals("/exit")) break;
            String[] inputs = choice.split(" ");
            int sourceBase = Integer.parseInt(inputs[0]);
            int targetBase = Integer.parseInt(inputs[1]);
            while (true) {
                System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", sourceBase, targetBase);
                String nextMove = scanner.next();
                if (nextMove.equals("/back")) break;
                BigInteger decimalValue = parseString(nextMove, sourceBase);
                String result = parseNumber(decimalValue, targetBase);
                System.out.println("Conversion result: " + result);
            }
        }

    }

    static BigInteger parseString(String number, int base) {
        return new BigInteger(number, base);
    }

    static String parseNumber(BigInteger number, int radix) {
        return number.toString(radix);
    }
}
