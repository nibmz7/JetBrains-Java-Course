package encryptdecrypt;

public class Main {
    static int ASCII_CODE_A = 97;
    static int ASCII_CODE_Z = 122;

    public static void main(String[] args) {
        String input = "we found a treasure!";
        StringBuilder sb = new StringBuilder();
        for (char i : input.toCharArray()) {
                if(Character.isAlphabetic(i)) {
                    int difference = Math.abs(ASCII_CODE_Z - i);
                    int newCode = ASCII_CODE_A + difference;
                    sb.append((char) newCode);
                } else {
                    sb.append(i);
                }
        }
        System.out.println(sb);
    }
}
