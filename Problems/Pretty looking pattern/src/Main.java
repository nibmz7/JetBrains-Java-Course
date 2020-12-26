import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                char color = matrix[i][j];
                if (color == matrix[i][j + 1] &&
                        color == matrix[i + 1][j] &&
                        color == matrix[i + 1][j + 1]) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}