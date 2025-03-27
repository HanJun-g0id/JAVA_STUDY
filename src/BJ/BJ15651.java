package BJ;
/*15649 15650과 유사한 문제라 이어서 풀었으며
* 코드의 구성 자체는 15649에서 거의 바뀐 바가 없다
* 중복을 허용해줬기에 오히려 더 쉬운 코드가 됐다
* visited를 삭제하고 반복문이 0부터 탐색하게해서
* 중복 선택 또한 가능하게했다.
* + 그 후 시간초과로 걸려서 스캐너가 아닌 sb를 사용해서 통과했다*/
import java.util.*;

public class BJ15651 {
    public static int[] arr;
    //탐색과정에서 값을 담는 용도
    public static StringBuilder sb = new StringBuilder(); //결과 저장용

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        arr = new int[M];
        backtracking(N,M,0);
        //dfs 함수에서 깊이를 추가로 받아서 재귀가 깊어질 때마다 깊이를 1씩 증가시켜
        //M과 같아지면 재귀호출을 중단하고 값을 담았던 arr을 출력해주고 리턴할 것이다.
        System.out.println(sb);
    }
    public static void backtracking(int N, int M, int depth) {
        if(depth == M) { //만약 M값만큼 만족했다면 최종 결과 출력
            for(int i = 0; i < M; i++) sb.append(arr[i]+" ");
            sb.append("\n");
            return;
        }
        for(int i = 0; i < N; i++) { //반복의 시작을 0으로 두어서 중복 선택이 가능하게 함
            arr[depth] = i + 1;
            backtracking(N,M,depth+1);
        }
    }
}