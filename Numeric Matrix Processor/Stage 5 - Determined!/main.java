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
                    "4. Transpose matrix\n" +
                    "5. Calculate a determinant\n" +
                    "0. Exit\n");

            System.out.print("Your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("\nEnter size of first matrix: ");
                    int[] sizeOne = readInputSize();
                    System.out.println("\nEnter first matrix: ");
                    double[][] matrixOne = readInputMatrix(sizeOne);
                    System.out.print("\nEnter size of second matrix: ");
                    int[] sizeTwo = readInputSize();
                    System.out.println("\nEnter second matrix: ");
                    double[][] matrixTwo = readInputMatrix(sizeTwo);

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
                    int[] size = readInputSize();
                    System.out.println("\nEnter matrix: ");
                    double[][] matrix = readInputMatrix(size);
                    System.out.print("\nEnter constant: ");
                    int constant = sc.nextInt();
                    double[][] result = multiplyMatrixByConstant(matrix, constant);
                    System.out.println("\nThe result is:");
                    printMatrix(result);
                    break;
                }
                case 3: {
                    System.out.print("\nEnter size of first matrix: ");
                    int[] sizeOne = readInputSize();
                    System.out.println("\nEnter first matrix: ");
                    double[][] matrixOne = readInputMatrix(sizeOne);
                    System.out.print("\nEnter size of second matrix: ");
                    int[] sizeTwo = readInputSize();
                    System.out.println("\nEnter second matrix: ");
                    double[][] matrixTwo = readInputMatrix(sizeTwo);

                    try {
                        double[][] result = multiplyMatrices(matrixOne, matrixTwo);
                        System.out.println("\nThe result is:");
                        printMatrix(result);
                    } catch (Exception e) {
                        System.out.println("The operation cannot be performed.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Your choice: ");
                    int transposeChoice = sc.nextInt();
                    System.out.print("\nEnter size of matrix: ");
                    int[] size = readInputSize();
                    System.out.println("\nEnter matrix: ");
                    double[][] matrix = readInputMatrix(size);
                    double[][] result = new double[matrix.length][matrix[0].length];
                    switch (transposeChoice) {
                        case 1: {
                            result = transposeMainDiagonal(matrix);
                            break;
                        }
                        case 2: {
                            result = transposeSideDiagonal(matrix);
                            break;
                        }
                        case 3: {
                            result = transposeVerticalLine(matrix);
                            break;
                        }
                        case 4: {
                            result = transposeHorizontalLine(matrix);
                            break;
                        }
                        default:
                            break;
                    }
                    System.out.println("\nThe result is:");
                    printMatrix(result);
                    break;
                }
                case 5: {
                    System.out.print("\nEnter matrix size: ");
                    int[] size = readInputSize();
                    System.out.println("\nEnter matrix: ");
                    double[][] matrix = readInputMatrix(size);
                    double result = calculateDeterminant(matrix);
                    System.out.printf("\nThe result is:\n%f\n\n", result);
                    break;
                }
                default:
                    return;
            }

        }

    }

    static double calculateDeterminant(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        if (rows == 2 & columns == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double result = 0;
        boolean shouldSubtract = false;

        for (int j = 0; j < columns; j++) {
            double[][] subMatrix = new double[rows - 1][columns - 1];
            for (int i = 0; i < rows; i++) {
                for (int k = 0; k < rows - 1; k++) {
                    int columnIndex = 0;
                    for (int l = 0; l < columns; l++) {
                        if (l == j) continue;
                        subMatrix[k][columnIndex] = matrix[k + 1][l];
                        columnIndex++;
                    }
                }
            }
            result += matrix[0][j] * (!shouldSubtract ? calculateDeterminant(subMatrix) : -(calculateDeterminant(subMatrix)));
            shouldSubtract = !shouldSubtract;
        }

        return result;
    }

    static double[][] transposeMainDiagonal(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    static double[][] transposeSideDiagonal(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[rows - j - 1][columns - i - 1] = matrix[i][j];
            }
        }
        return result;
    }

    static double[][] transposeVerticalLine(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][columns - j - 1] = matrix[i][j];
            }
        }
        return result;
    }

    static double[][] transposeHorizontalLine(double[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            result[rows - i - 1] = matrix[i];
        }
        return result;
    }

    static double[][] multiplyMatrixByConstant(double[][] matrix, double constant) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        double[][] result = new double[rows][columns];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix[i][j] * constant;
            }
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

    static int[] readInputSize() {
        int rows = sc.nextInt();
        int columns = sc.nextInt();
        return new int[]{rows, columns};
    }

    static double[][] readInputMatrix(int[] size) {
        int rows = size[0];
        int columns = size[1];
        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }

        return matrix;
    }
}
