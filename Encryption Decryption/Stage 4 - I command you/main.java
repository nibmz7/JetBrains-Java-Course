package encryptdecrypt;

import java.util.Scanner;

public class Main {
    static int ASCII_CODE_FIRST = 32;
    static int ASCII_CODE_LAST = 126;
    static int ASCII_CODE_A = 97;
    static int ASCII_CODE_Z = 122;

    public static void main(String[] args) {
        String operation = "enc";
        String input = "";
        int key = 0;

        for (int i = 0; i < args.length; i += 2) {
            String arg = args[i];
            String value = args[i + 1];

            if (arg.contains("mode")) {
                operation = value;
            } else if (arg.contains("key")) {
                key = Integer.parseInt(value);
            } else {
                input = value;
            }
        }

        boolean isEncryption = operation.equals("enc");
        StringBuilder sb = new StringBuilder();
        for (char i : input.toCharArray()) {
            int newCode = isEncryption ? incrementCode(i, key) : decrementCode(i, key);
            sb.append((char) newCode);
        }
        System.out.println(sb);
    }

    public static int incrementCode(int currentCode, int count) {
        int newCode = currentCode + count;
        int overflow = ASCII_CODE_LAST - newCode;

        if (overflow < 0) {
            int remainingSum = count - (ASCII_CODE_LAST - currentCode) - 1;
            return incrementCode(ASCII_CODE_FIRST, remainingSum);
        }

        return newCode;
    }

    public static int decrementCode(int currentCode, int count) {
        int newCode = currentCode - count;
        int overflow = newCode - ASCII_CODE_FIRST;

        if (overflow < 0) {
            int remainingSum = count - (currentCode - ASCII_CODE_FIRST) - 1;
            return decrementCode(ASCII_CODE_LAST, remainingSum);
        }

        return newCode;
    }
}
