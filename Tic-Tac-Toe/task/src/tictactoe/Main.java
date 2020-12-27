package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] board = makeEmptyBoard();
        printBoard(board);
        makeNextMove(board, 'X');
    }

    private static char[][] makeEmptyBoard() {
        char[][] board = new char[3][3];
        Arrays.fill(board[0], '_');
        Arrays.fill(board[1], '_');
        Arrays.fill(board[2], '_');
        return board;
    }

    private static void makeNextMove(char[][] board, char symbol) {
        Scanner scanner = new Scanner(System.in);
        int[] coordinates = new int[2];
        boolean success = false;
        do {
            System.out.print("Enter the coordinates: ");
            try {
                int first = scanner.nextInt();
                int second = scanner.nextInt();
                if (first > 3 || first < 1 || second > 3 || second < 1)
                    throw new Exception("Coordinates should be from 1 to 3!");
                if (board[first - 1][second - 1] != '_')
                    throw new Exception("This cell is occupied! Choose another one!");
                coordinates[0] = first - 1;
                coordinates[1] = second - 1;
                success = true;
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!success);
        board[coordinates[0]][coordinates[1]] = symbol;
        printBoard(board);
        checkBoardScores(board, symbol);
    }

    private static char[][] getInputBoard() {
        System.out.print("Enter cells: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[][] cells = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = input.charAt(j + (i * 3));
            }
        }
        return cells;
    }

    private static void printBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                char symbol = board[i][j];
                System.out.print(symbol);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static void checkBoardScores(char[][] board, char currentSymbol) {
        int[] winner = {0, 0};

        //isRowSame
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                char symbol = board[i][0];
                if (symbol == '_') continue;
                if (symbol == 'X') winner[0]++;
                else winner[1]++;
            }
        }

        //isColumnSame
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                char symbol = board[0][i];
                if (symbol == '_') continue;
                if (symbol == 'X') winner[0]++;
                else winner[1]++;
            }
        }

        //isDiagonalsSame
        boolean isDiagonalSame =
                (board[1][1] == board[0][0] && board[1][1] == board[2][2]) ||
                        (board[1][1] == board[0][2] && board[1][1] == board[2][0]);
        char symbol = board[1][1];
        if (isDiagonalSame && symbol != '_') {
            if (symbol == 'X') winner[0]++;
            else winner[1]++;
        }

        if (winner[0] > 0) {
            System.out.println("X wins");
        } else if (winner[1] > 0) {
            System.out.println("O wins");
        } else {
            int[] movesCount = getMovesCount(board);
            if (movesCount[2] == 0) {
                System.out.println("Draw");
            } else {
                makeNextMove(board, currentSymbol == 'X' ? 'O' : 'X');
            }
        }
    }

    private static int[] getMovesCount(char[][] board) {
        int[] count = {0, 0, 0};

        for (char[] row : board) {
            for (char symbol : row) {
                if (symbol == 'X') count[0]++;
                else if (symbol == 'O') count[1]++;
                else count[2]++;
            }
        }

        return count;
    }
}

