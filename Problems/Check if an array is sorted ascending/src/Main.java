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
        for (int i = 0; i <= size - 2; i++) {
            if(numbers[i] > numbers[i + 1]) {
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");
    }
}