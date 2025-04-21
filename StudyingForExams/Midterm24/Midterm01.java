import java.util.Scanner;

public class Midterm01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("width of triangle : ");
        double width = scanner.nextDouble();

        System.out.print("height of triangle : ");
        double height = scanner.nextDouble();

        System.out.println("Area of triangle : " + (width * height / 2));
        System.out.println("Length of triangle : " + (width + height + Math.sqrt(width * width + height * height)));
    }
}
