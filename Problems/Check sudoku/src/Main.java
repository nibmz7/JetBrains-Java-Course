import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int n2 = n * n;
        int[][] sudoku = new int[n * n][n * n];
        int correctSum = 0;
        for (int i = 1; i <= n2; i++) correctSum += i;

        //check rows
        for (int i = 0; i < n2; i++) {
            int rowsSum = 0;
            for (int j = 0; j < n2; j++) {
                int number = scanner.nextInt();
                sudoku[i][j] = number;
                rowsSum += number;

            }
            if (rowsSum != correctSum) {
                System.out.println("NO");
                return;
            }

        }

        //check columns
        for (int i = 0; i < n2; i++) {
            int columnsSum = 0;
            for (int j = 0; j < n2; j++) {
                int number = sudoku[j][i];
                columnsSum += number;

            }
            if (columnsSum != correctSum) {
                System.out.println("NO");
                return;
            }
        }

        //check box
        for (int i = 0; i <= n2 - n; i += n) {
            for (int j = 0; j <= n2 - n; j += n) {
                int boxSum = 0;
                for (int k = j; k < j + n; k++) {
                    for (int l = i; l < i + n; l++) {
                        int number = sudoku[l][k];
                        boxSum += number;
                    }
                }
                if (boxSum != correctSum) {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println("YES");
    }
}