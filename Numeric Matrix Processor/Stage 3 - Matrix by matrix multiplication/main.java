package processor;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Add matrices\n" +
                    "2. Multiply matrix by a constant\n" +
                    "3. Multiply matrices\n" +
                    "0. Exit\n");

            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("\nEnter size of first matrix: ");
                    double[][] matrixOne = readInputMatrix();
                    System.out.print("\nEnter size of second matrix: ");
                    double[][] matrixTwo = readInputMatrix();

                    try {
                        double[][] result = addMatrices(matrixOne, matrixTwo);
                        System.out.println("\nThe result is:");
                        printMatrix(result);
                    } catch (Exception e) {
                        System.out.println("The operation cannot be performed.");
                    }
                    break;
                }
                case 2: {
                    System.out.print("\nEnter size of matrix: ");
                    double[][] matrix = readInputMatrix();
                    System.out.print("\nEnter constant: ");
                    int constant = sc.nextInt();
                    double[][] result = multiplyMatrixByConstant(matrix, constant);
                    printMatrix(result);
                    break;
                }
                case 3: {
                    System.out.print("\nEnter size of first matrix: ");
                    double[][] matrixOne = readInputMatrix();
                    System.out.print("\nEnter size of second matrix: ");
                    double[][] matrixTwo = readInputMatrix();

                    try {
                        double[][] result = multiplyMatrices(matrixOne, matrixTwo);
                        System.out.println("\nThe result is:");
                        printMatrix(result);
                    } catch (Exception e) {
                        System.out.println("The operation cannot be performed.");
                    }
                    break;
                }
                default:
                    return;
            }

        }

    }

    static double[][] multiplyMatrixByConstant(double[][] matrix, double constant) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
            System.out.println();
        }
        return result;
    }

    static double[][] multiplyMatrices(double[][] first, double[][] second) throws Exception {
        int rowsFirst = first.length;
        int columnsFirst = first[0].length;
        int rowsSecond = second.length;
        int columnsSecond = second[0].length;

        if (columnsFirst != rowsSecond) throw new Exception();

        double[][] matrix = new double[rowsFirst][columnsSecond];

        for (int i = 0; i < rowsFirst; i++) {
            for (int j = 0; j < columnsSecond; j++) {
                double sum = 0;
                for (int h = 0; h < rowsSecond; h++) {
                    sum += first[i][h] * second[h][j];
                }
                matrix[i][j] = sum;
            }
        }

        return matrix;
    }

    static double[][] addMatrices(double[][] first, double[][] second) throws Exception {
        if (first.length != second.length && first[0].length != second[0].length) {
            throw new Exception();
        }
        int rows = first.length;
        int columns = first[0].length;
        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = first[i][j] + second[i][j];
            }
        }
        return matrix;
    }

    static void printMatrix(double[][] matrix) {
        DecimalFormat format = new DecimalFormat("0.####");
        format.setRoundingMode(RoundingMode.DOWN);

        for (double[] ints : matrix) {
            for (double num : ints) {
                System.out.print(format.format(num) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static double[][] readInputMatrix() {
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }

        return matrix;
    }
}
