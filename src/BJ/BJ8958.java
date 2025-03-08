package BJ;

import java.util.Scanner;
/*
* O와 X가 반복되는 문자열을 입력 받아서 O가 연속일때 누적 점수를 받으며 X가 나오면
* 해당 누적치가 리셋된다*/
public class BJ8958 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        for(int i=0; i<N; i++) {
            String str = sc.next(); //OX문자열 입력
            System.out.println(solution(str));
        }
        sc.close();
    }

    static int solution(String str){
        int point = 0;
        int combo = 0;

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)=='X') combo = 0; //X가 나오면 콤보 초기화
            else {
                combo++; //O가 나오면 콤보 증가
                point += combo; //점수 증가
            }
        }
        return point;
    }
}
