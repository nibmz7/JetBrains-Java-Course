package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
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
                String result = parseString(nextMove, sourceBase, targetBase);
                System.out.println("Conversion result: " + result);
            }
        }

    }

    // I don't actually know what I'm doing
    static String parseString(String number, int sourceBase, int targetBase) {
        if (number.contains(".")) {
            String[] parts = number.split("\\.");
            String integerPart = new BigInteger(parts[0], sourceBase).toString(targetBase);
            BigDecimal fractionalValue = BigDecimal.ZERO;
            for (int i = 0; i < parts[1].length(); i++) {
                String x = String.valueOf(parts[1].charAt(i));
                BigDecimal divisor = new BigDecimal(sourceBase).pow(i + 1);
                int multiplier = Integer.parseInt(new BigInteger(x, sourceBase).toString(10));
                BigDecimal partValue = new BigDecimal(multiplier).divide(divisor, new MathContext(300));
                fractionalValue = fractionalValue.add(partValue);
            }
            StringBuilder fractionalPart = new StringBuilder();
            BigDecimal multiplier = new BigDecimal(targetBase);
            BigDecimal divisor = BigDecimal.ONE.divide(new BigDecimal(targetBase), new MathContext(300));
            BigDecimal quotient = fractionalValue.divideAndRemainder(divisor)[0];
            BigDecimal remainder = fractionalValue.remainder(divisor).multiply(multiplier);
            for (int i = 0; i < 5; i++) {
                fractionalPart.append(quotient.toBigInteger().toString(targetBase));
                quotient = remainder.divideAndRemainder(divisor)[0];
                remainder = remainder.remainder(divisor).multiply(multiplier);
            }
            return integerPart + "." + fractionalPart;
        } else return new BigInteger(number, sourceBase).toString(targetBase);
    }

}
