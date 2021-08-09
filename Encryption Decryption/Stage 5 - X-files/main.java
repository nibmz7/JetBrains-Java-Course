package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static int ASCII_CODE_FIRST = 32;
    static int ASCII_CODE_LAST = 126;

    public static void main(String[] args) {
        final String MODE_ARGUMENT = "-mode";
        final String KEY_ARGUMENT = "-key";
        final String DATA_ARGUMENT = "-data";
        final String IN_ARGUMENT = "-in";
        final String OUT_ARGUMENT = "-out";

        String mode = "enc";
        String data = "";
        String outFile = "";
        String inFile = "";
        int key = 0;

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case MODE_ARGUMENT:
                    mode = args[i + 1];
                    break;
                case KEY_ARGUMENT:
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case DATA_ARGUMENT:
                    data = args[i + 1];
                    break;
                case IN_ARGUMENT:
                    inFile = args[i + 1];
                    break;
                case OUT_ARGUMENT:
                    outFile = args[i + 1];
                    break;
                default:
                    break;
            }
        }

        if (data.isEmpty() && !inFile.isEmpty()) {
            try {
                data = new String(Files.readAllBytes(Paths.get(inFile)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean isEncryption = mode.equals("enc");
        StringBuilder sb = new StringBuilder();
        for (char i : data.toCharArray()) {
            int newCode = isEncryption ? incrementCode(i, key) : decrementCode(i, key);
            sb.append((char) newCode);
        }

        if (outFile.isEmpty()) {
            System.out.println(sb);
        } else {
            File file = new File(outFile);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.print(sb);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
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
