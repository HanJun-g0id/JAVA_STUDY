package BJ;
/*
* 다이얼 문제
* 다이얼을 돌려서 ABC까지는 3초 DEF는 4초등 입력한 단어까지 다이얼 돌리는 시간의 총합을 구하는 문제
* 한번 돌린 뒤에는 다시 처음부터 돌리는 방식이다.
* 아스키 코드 값으로 비교하여 해결했다.*/
import java.util.Scanner;

public class BJ5622 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        sc.close();

        System.out.println(getDialTime(input));
    }

    static int getDialTime(String str) {
        int totalTime = 0; //총 시간값

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i); //입력받은 문자열에서 한 문자씩 데려와서 아스키값을 비교

            if (c >= 'A' && c <= 'C') totalTime += 3; // 다이얼 2
            else if (c >= 'D' && c <= 'F') totalTime += 4; // 다이얼 3
            else if (c >= 'G' && c <= 'I') totalTime += 5; // 다이얼 4
            else if (c >= 'J' && c <= 'L') totalTime += 6; // 다이얼 5
            else if (c >= 'M' && c <= 'O') totalTime += 7; // 다이얼 6
            else if (c >= 'P' && c <= 'S') totalTime += 8; // 다이얼 7
            else if (c >= 'T' && c <= 'V') totalTime += 9; // 다이얼 8
            else if (c >= 'W' && c <= 'Z') totalTime += 10; // 다이얼 9
        }

        return totalTime;
    }
}
