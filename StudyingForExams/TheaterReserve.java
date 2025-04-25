import java.util.*;

public class TheaterReserve {
    public static void main(String[] args) {
        int[] seats = new int[10]; // 좌석 10개, 0: 비어있음, 1: 예약됨
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 좌석 번호 출력
            System.out.println("-------------------------------");
            for (int i = 1; i <= 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            // 좌석 상태 출력
            for (int i = 0; i < 10; i++) {
                System.out.print(seats[i] + " ");
            }
            System.out.println();
            System.out.println("-------------------------------");

            // 좌석 번호 입력
            System.out.print("원하시는 좌석번호를 입력하세요 (종료는 -1): ");
            int seatNum = sc.nextInt();

            if (seatNum == -1) {
                System.out.println("예약을 종료합니다.");
                break;
            }

            // 좌석 번호 유효성 검사
            if (seatNum < 1 || seatNum > 10) {
                System.out.println("잘못된 좌석번호입니다. 1~10 사이로 입력하세요.");
                continue;
            }

            // 이미 예약된 좌석인지 확인
            if (seats[seatNum - 1] == 1) {
                System.out.println("이미 예약된 자리입니다.");
            } else {
                seats[seatNum - 1] = 1; // 좌석 예약
                System.out.println("예약되었습니다.");
            }
        }

        sc.close();
    }
}
