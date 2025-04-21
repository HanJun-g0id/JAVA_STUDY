import java.util.Scanner;

public class Midterm02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Positive Number : ");
            int input = scanner.nextInt();

            if (input == -1) {
                System.out.println("End the program");
                break;
            } else if (input < 0) {
                System.out.println("It is not a positive value");
            } else {
                System.out.println("The factors of " + input + " are : ");

                for (int i = 1; i <= input; i++) {
                    if (input % i == 0) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println();
                System.out.println("End the program");

                break;
            }
        }
    }
}
