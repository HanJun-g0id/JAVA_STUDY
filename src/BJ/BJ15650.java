package BJ;
/*바로 전에 풀었던 BJ15649와 거의 유사한 문제
* arr의 크기를 N에서 M으로 충분하기에 수정했으며
* 백트래킹 매개변수로 start를 추가해서 반복문 시작값으로 지정했다
* visited 배열은 필요없어서 삭제*/
import java.util.Scanner;

public class BJ15650 {
    public static int[] arr;
    //탐색과정에서 값을 담는 용도
    public static boolean[] visited;
    //재귀를 하면서 이미 방문한 노드라면 다음 노드를 탐색하기 위해 생성

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //아무리 생각해도 숫자를 연속으로 입력 받을 때는 스캐너가 나은 듯 하다
        //속도를 빠르게 하고 싶은게 아니라면 번거롭지 않아서 좋다

        //[문제설명]
        int N = sc.nextInt(); //1부터 N까지 자연수 중
        int M = sc.nextInt(); //중복없이 M개를 고른 수열을 출력하는 문제
        //이 둘은 자체값이 변경될 일이 없기에 전역변수로 선언해도 무방했다

        arr = new int[M]; //M만큼만 있어도 충분함
        backtracking(N,M,0,1);
        //dfs 함수에서 깊이를 추가로 받아서 재귀가 깊어질 때마다 깊이를 1씩 증가시켜
        //M과 같아지면 재귀호출을 중단하고 값을 담았던 arr을 출력해주고 리턴할 것이다.
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
            backtracking(N,M,depth+1,i+1); //다음 수는 i보다 큰 수 > 오름차순 유지
        }
    }
}