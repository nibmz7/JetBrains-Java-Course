package cinema;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Cinema:");
        for (int row = 0; row <= 7; row++) {
            for (int column = 0; column <= 8; column++) {
                if (row == 0) {
                    if (column == 0) System.out.print("  ");
                    else System.out.printf("%d ", column);
                } else {
                    if (column == 0) System.out.printf("%d ", row);
                    else System.out.print("S ");
                }
            }
            System.out.println();
        }
    }
}