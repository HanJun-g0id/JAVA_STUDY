package BJ;
/* 15650문제에서 몇몇 수정만 하니까 성공한 문제다
* 중복 숫자를 가능하게 했고(백트래킹 재귀에서 i+1 >> i로 변경)
* 비내림차순(다음항이 크거나 같다)정렬을 유지했다*/
import java.util.Scanner;

public class BJ15652 {
    public static int[] arr;
    //탐색과정에서 값을 담는 용도

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        arr = new int[M];
        backtracking(N,M,0,1);
    }
    public static void backtracking(int N, int M, int depth, int start) {
        if(depth == M) { //만약 M값만큼 만족했다면 최종 결과 출력
            for(int i = 0; i < M; i++) System.out.print(arr[i]+" ");
            System.out.println();
            return;
        }
        //start라는 매개변수를 추가해서 이전 숫자보다 큰 값만 고르게함
        for(int i = start; i <= N; i++) { //이전 숫자보다 큰 값만 골라
            arr[depth] = i; //현재 깊이에 숫자 저장
            backtracking(N,M,depth+1,i); //다음 수는 i보다 큰 수 > 오름차순 유지
        }
    }
}