package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        int income;
        int totalSeats = rows * columns;
        if (totalSeats < 60) {
            income = totalSeats * 10;
        } else {
            int frontHalfRows = rows / 2;
            int backHalfRows = rows - frontHalfRows;
            income = frontHalfRows * columns * 10 + backHalfRows * columns * 8;
        }
        System.out.printf("Total income:\n$%d", income);
    }
}