import java.util.Scanner;

public class Midterm02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.print("\nPositive Number : ");
            int a = sc.nextInt();

            if(a == -1){
                System.out.println("End the program");
                break;
            }
            if(a < 0) System.out.println("It is not a positive value");
            else{
                System.out.printf("The factors of %d are : \n", a);
                for(int i = 1; i <= a; i++){
                    if(a % i == 0){
                        System.out.println(i + " ");
                    }
                }
            }
        }
    }
}
