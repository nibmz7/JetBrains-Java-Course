package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows, columns, chosenRow, chosenColumn, ticketPrice;

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        printSeats(rows, columns);
        System.out.println();

        System.out.println("Enter a row number:");
        chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenColumn = scanner.nextInt();

        int totalSeats = rows * columns;
        if (totalSeats < 60) {
            ticketPrice = 10;
        } else {
            int frontHalfRows = rows / 2;
            if (chosenRow <= frontHalfRows) ticketPrice = 10;
            else ticketPrice = 8;
        }

        System.out.printf("Ticket price:$%d", ticketPrice);

        printSeats(rows, columns, chosenRow, chosenColumn);
        System.out.println();

    }

    public static void printSeats(int rows, int columns) {
        printSeats(rows, columns, -1, -1);
    }

    public static void printSeats(int rows, int columns, int chosenRow, int chosenColumn) {
        System.out.println("Cinema:");
        for (int row = 0; row <= rows; row++) {
            for (int column = 0; column <= columns; column++) {
                if (row == 0) {
                    if (column == 0) System.out.print("  ");
                    else System.out.printf("%d ", column);
                } else if (row == chosenRow && column == chosenColumn)
                    System.out.print("B ");
                else {
                    if (column == 0) System.out.printf("%d ", row);
                    else System.out.print("S ");
                }
            }
            System.out.println();
        }
    }

}