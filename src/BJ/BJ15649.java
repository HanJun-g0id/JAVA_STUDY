package BJ;
/*백트래킹을 배우긴했지만 아직 익히진 못해서
* 익히기 위해 백트래킹 알고리즘 문제를 골라왔다
* 문제 자체가 어렵진 않지만 백트래킹의 이해도가 아직
* 제대로 확립되어있지 않았어서인지
* 난이도가 좀 있다고 느껴졌다
* 문제 설명은 밑 주석에 넣어뒀다*/
import java.util.Scanner;

public class BJ15649 {
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

        arr = new int[N];
        visited = new boolean[N];
        backtracking(N,M,0);
        //dfs 함수에서 깊이를 추가로 받아서 재귀가 깊어질 때마다 깊이를 1씩 증가시켜
        //M과 같아지면 재귀호출을 중단하고 값을 담았던 arr을 출력해주고 리턴할 것이다.
    }
    public static void backtracking(int N, int M, int depth) {
        if(depth == M) { //만약 M값만큼 만족했다면 최종 결과 출력
            for(int i = 0; i < M; i++) System.out.print(arr[i]+" ");
            System.out.println();
            return;
        }
        for(int i = 0; i < N; i++) {
            if(!visited[i]) { //만약 미방문 ? > 방문으로 변경
                visited[i] = true;
                arr[depth] = i + 1; //배열의 깊이 증가
                backtracking(N, M, depth + 1);
                //depth++은 오류가 생긴다
                //왜 그런가 좀 알아보니까 depth를 하면 변수 값 자체가 증가해서 재귀에서
                //빠져나와도 증가된 값이 유지되니까 계산에 문제가 생긴다
                visited[i] = false;
            }
        }
    }
}
