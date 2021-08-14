package processor;

import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] matrixOne = readInputMatrix();
        int constant = sc.nextInt();

        int columns = matrixOne[0].length;

        for (int[] ints : matrixOne) {
            for (int j = 0; j < columns; j++) {
                int sum = ints[j] * constant;
                System.out.print(sum + " ");
            }
            System.out.println();
        }

    }

    static int[][] readInputMatrix() {
        String[] dimensions = sc.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            String[] items = sc.nextLine().split(" ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(items[j]);
            }
        }

        return matrix;
    }
}
