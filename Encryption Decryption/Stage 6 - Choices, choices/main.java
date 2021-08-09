package encryptdecrypt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    static int ASCII_CODE_A = 97;
    static int ASCII_CODE_FIRST = 0;
    static int ASCII_CODE_LAST = 127;

    public static void main(String[] args) {
        final String MODE_ARGUMENT = "-mode";
        final String KEY_ARGUMENT = "-key";
        final String DATA_ARGUMENT = "-data";
        final String IN_ARGUMENT = "-in";
        final String OUT_ARGUMENT = "-out";
        final String ALG_ARGUMENT = "-alg";

        String mode = "enc";
        String data = "";
        String outFile = "";
        String inFile = "";
        String alg = "shift";
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
                case ALG_ARGUMENT:
                    alg = args[i + 1];
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
        boolean isShift = alg.equals("shift");
        StringBuilder sb = new StringBuilder();
        for (char i : data.toCharArray()) {
            int newCode = getNewCode(i, key, isEncryption, isShift);
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

    public static int getNewCode(int currentCode, int count, boolean isEncryption, boolean isShift) {
        if (isShift && !Character.isAlphabetic(currentCode)) return currentCode;
        boolean isCapital = Character.isUpperCase(currentCode);
        int firstCode = isShift ? (isCapital ? ASCII_CODE_A - 32 : ASCII_CODE_A) : ASCII_CODE_FIRST;
        int lastCode = isShift ? firstCode + 25 : ASCII_CODE_LAST;
        int range = lastCode - firstCode + 1;
        int positionCode = currentCode - firstCode;
        int newPositionCode = isEncryption ? (positionCode + count) % range : (positionCode - count + range) % range;

        return newPositionCode + firstCode;
    }

}
