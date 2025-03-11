package BJ;

/*푼지 좀 됐는데 커밋해두고 싶어서 다시 올리는 코드
* 전공평점을 계산해주는 프로그램이다*/
import java.util.Scanner;

public class BJ25206 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total = 0;  // 총 성적 합산 (학점 * 등급)
        double sum_credits = 0;  // 학점 총합 (P 제외)

        for (int i = 0; i < 20; i++) {
            String[] input = sc.nextLine().split(" ");  // 공백 기준으로 입력 분리
            double credit = Double.parseDouble(input[1]);  // 학점 변환
            String grade = input[2];  // 등급

            double rating = 0.0;
            switch (grade) {
                case "A+":
                    rating = 4.5;
                    break;
                case "A0":
                    rating = 4.0;
                    break;
                case "B+":
                    rating = 3.5;
                    break;
                case "B0":
                    rating = 3.0;
                    break;
                case "C+":
                    rating = 2.5;
                    break;
                case "C0":
                    rating = 2.0;
                    break;
                case "D+":
                    rating = 1.5;
                    break;
                case "D0":
                    rating = 1.0;
                    break;
                case "F":
                    rating = 0.0;
                    break;
                case "P":
                    continue;  // "P" 학점인 경우 계산 제외
            }

            total += credit * rating;
            sum_credits += credit;
        }

        System.out.printf("%.6f\n", total / sum_credits);  // 소수점 6자리까지 출력
        sc.close();
    }
}