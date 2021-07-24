package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean[][] seats;
        int rows, columns;

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();
        System.out.println();

        seats = new boolean[rows + 1][columns + 1];

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "0. Exit");
            int selection = scanner.nextInt();
            System.out.println();
            switch (selection) {
                case 1: {
                    printSeats(seats);
                    System.out.println();
                    break;
                }
                case 2: {
                    buyATicket(seats, rows, columns);
                    System.out.println();
                    break;
                }
                default:
                    return;
            }
        }

    }

    public static void buyATicket(boolean[][] seats, int rows, int columns) {
        int chosenRow, chosenColumn, ticketPrice;

        System.out.println("Enter a row number:");
        chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenColumn = scanner.nextInt();

        seats[chosenRow][chosenColumn] = true;

        int totalSeats = rows * columns;
        if (totalSeats < 60) {
            ticketPrice = 10;
        } else {
            int frontHalfRows = rows / 2;
            if (chosenRow <= frontHalfRows) ticketPrice = 10;
            else ticketPrice = 8;
        }

        System.out.printf("Ticket price:$%d", ticketPrice);

    }

    public static void printSeats(boolean[][] seats) {
        System.out.println("Cinema:");
        int columnSize = seats[0].length;
        for (int row = 0; row < seats.length; row++) {
            for (int column = 0; column < columnSize; column++) {
                if (row == 0) {
                    if (column == 0) System.out.print("  ");
                    else System.out.printf("%d ", column);
                } else if (column == 0) System.out.printf("%d ", row);
                else {
                    if (seats[row][column]) System.out.print("B ");
                    else System.out.print("S ");
                }

            }
            System.out.println();
        }
    }

}