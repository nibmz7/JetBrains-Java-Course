package processor;

import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] matrixOne = readInputMatrix();
        int[][] matrixTwo = readInputMatrix();

        if(matrixOne.length != matrixTwo.length && matrixOne[0].length != matrixTwo[0].length) {
            System.out.println("ERROR");
        } else {
            int rows = matrixOne.length;
            int columns = matrixOne[0].length;

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    int sum = matrixOne[i][j] + matrixTwo[i][j];
                    System.out.print(sum + " ");
                }
                System.out.println();
            }
        }

    }

    static int[][] readInputMatrix() {
        String[] dimensions = sc.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][columns];

        for(int i = 0; i < rows; i++) {
            String[] items = sc.nextLine().split(" ");
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(items[j]);
            }
        }

        return matrix;
    }
}
