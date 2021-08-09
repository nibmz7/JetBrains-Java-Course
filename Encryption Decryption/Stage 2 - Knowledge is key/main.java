package encryptdecrypt;

import java.util.Scanner;

public class Main {
    static int ASCII_CODE_A = 97;
    static int ASCII_CODE_Z = 122;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int key = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        for (char i : input.toCharArray()) {
            if (Character.isAlphabetic(i)) {
                int newCode = incrementCode(i, key);
                sb.append((char) newCode);
            } else {
                sb.append(i);
            }
        }
        System.out.println(sb);
    }

    public static int incrementCode(int currentCode, int count) {
        int newCode = currentCode + count;
        int overflow = ASCII_CODE_Z - newCode;

        if (overflow < 0) {
            int remainingSum = count - (ASCII_CODE_Z - currentCode) - 1;
            return incrementCode(ASCII_CODE_A, remainingSum);
        }

        return newCode;
    }
}
