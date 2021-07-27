package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        int number;
        try {
            number = scanner.nextInt();
            if(number <= 0) throw new Exception("Un-natural number");
        } catch (Exception e) {
            System.out.println("This number is not natural!");
            return;
        }

        boolean isEven = number % 2 == 0;
        System.out.printf("This number is %s\n", isEven ? "Even" : "Odd");

        boolean isDivisibleBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;

        if(isDivisibleBy7 || endsWith7) {
            System.out.println("It is a Buzz number.");
            System.out.println("Explanation:");
            System.out.print(number);
            if(isDivisibleBy7) {
                System.out.print(" is divisible by 7");
                System.out.print(endsWith7 ? " and" : ".");
            }
            if(endsWith7) {
                System.out.print(" ends with 7.");
            }
        } else {
            System.out.println("It is not a Buzz number.");
            System.out.println("Explanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.", number);
        }

    }
}
