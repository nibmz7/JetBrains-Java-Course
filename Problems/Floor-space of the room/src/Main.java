import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        double floorage = 0;
        switch(input) {
            case "triangle": {
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                double c = scanner.nextDouble();
                double s = (a + b + c) / 2;
                floorage = Math.sqrt(s * (s - a) * (s - b) * (s - c));
                break;
            }
            case "rectangle": {
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                floorage = a * b;
                break;
            }
            case "circle": {
                double r = scanner.nextDouble();
                floorage = 3.14 * r * r;
                break;
            }
        }
        System.out.println(floorage);
    }
}
