import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(n == 1) {
            System.out.println('*');
            return;
        }
        String[][] matrix = new String[n][n];
        int midPoint = (n - 1) / 2;
        for (int i = 0; i < n; i++) {
            boolean isMidRow = i == midPoint;
            int diagonalOffset = Math.abs((midPoint - i) % midPoint);
            for (int j = 0; j < n; j++) {
                boolean isMidColumn = j == midPoint;
                boolean isCorners = (i == 0 || i == n - 1) && (j == 0 || j == n - 1);
                boolean isDiagonalPoint = isCorners || (j + diagonalOffset) == midPoint || Math.abs(j - diagonalOffset) == midPoint;
                if (isMidRow || isMidColumn || isDiagonalPoint) matrix[i][j] = "*";
                else matrix[i][j] = ".";
            }
        }
        for (String[] row : matrix) {
            System.out.println(String.join(" ", row));
        }

    }
}