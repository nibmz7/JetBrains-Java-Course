import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }
        int tripletCount = 0;
        for (int i = 0; i <= size - 3; i++) {
            int count = 0;
            for (int j = i; j <= i + 1; j++) {
                if (numbers[j] + 1 == numbers[j + 1])
                    count++;
                else break;
            }
            if (count == 2) tripletCount++;
        }
        System.out.println(tripletCount);
    }
}