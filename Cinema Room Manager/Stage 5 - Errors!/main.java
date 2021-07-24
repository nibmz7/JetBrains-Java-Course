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
                    "3. Statistics\n" +
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
                case 3: {
                    showStats(seats, rows, columns);
                    System.out.println();
                    break;
                }
                default:
                    return;
            }
        }

    }

    public static void showStats(boolean[][] seats, int rows, int columns) {
        int purchasedTickets = 0, currentIncome = 0, maxIncome;
        double purchasedTicketsPercentage;
        int totalSeats = rows * columns;
        if (totalSeats < 60) {
            maxIncome = totalSeats * 10;
        } else {
            int frontHalfRows = rows / 2;
            int backHalfRows = rows - frontHalfRows;
            maxIncome = frontHalfRows * columns * 10 + backHalfRows * columns * 8;
        }

        int columnSize = seats[0].length;
        for (int row = 0; row < seats.length; row++) {
            if (row == 0) continue;
            for (int column = 0; column < columnSize; column++) {
                if (column == 0) continue;
                boolean isPurchased = seats[row][column];
                if (isPurchased) {
                    purchasedTickets++;
                    if (totalSeats < 60) {
                        currentIncome += 10;
                    } else {
                        int frontHalfRows = rows / 2;
                        if (row <= frontHalfRows) currentIncome += 10;
                        else currentIncome += 8;
                    }
                }
            }
        }

        purchasedTicketsPercentage = purchasedTickets / (totalSeats * 1.0) * 100;

        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", purchasedTicketsPercentage);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d", maxIncome);
    }

    public static void buyATicket(boolean[][] seats, int rows, int columns) {
        int chosenRow, chosenColumn, ticketPrice;

        System.out.println("Enter a row number:");
        chosenRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        chosenColumn = scanner.nextInt();

        try {
            if (chosenRow == 0 || chosenColumn == 0) throw new Exception("Invalid input");
            boolean isSeatSelected = seats[chosenRow][chosenColumn];
            if (isSeatSelected) {
                System.out.println("That ticket has already been purchased!");
                buyATicket(seats, rows, columns);
                return;
            }
        } catch (Exception e) {
            System.out.println("Wrong input!");
            buyATicket(seats, rows, columns);
            return;
        }


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