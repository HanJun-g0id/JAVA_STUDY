import java.util.*;

public class midterm02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.print("\n양의 정수를 입력하시오 : ");
            int a = scan.nextInt();

            if(a == -1){
                System.out.println("프로그램 종료");
                break;
            }
            if (a < 0) {
                System.out.println("양의 정수가 아닙니다.");
            } else {
                System.out.printf("%d의 약수는 다음과 같습니다.\n", a);

                for (int i = 1; i <= a; i ++) {
                    if (a % i == 0) {
                        System.out.print(i + " ");
                    }
                }
                System.out.print("\n");
            }
        }
    }
}
