package BJ;
/*연재형이 추천해준 두번째 문제
* 골드5에 스페셜저지라 쫄았는데
* 알고리즘이 어렵지 푸는 건 쉬운 문제다
* 절댓값을 비교하는 방식으로 풀었는데
* 절댓값을 비교해서 0에 더 가까운 수를 찾아냈다
* 포인터들의 이동이 조금 헷갈리는 문제였다.*/
import java.io.*;
import java.util.*;

public class BJ2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N]; //입력받는 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); //나열되는 수 배열에 입력 받기
        }
        Arrays.sort(arr); //투 포인터 쓰기 위해 정렬

        int left = 0; //좌측 포인터
        int right = N - 1; //우측 포인터
        int near_value = Integer.MAX_VALUE; //일단 정수 값 중 가장 큰 걸로 지정해둠
        int ans1 = 0, ans2 = 0; //최종 출력할 조합

        while(left < right) { //중복 방지
            int sum = arr[left] + arr[right]; //sum에 좌 우측 포인터를 더하자
            if(Math.abs(sum) < near_value) { //더한 값의 절댓값이 근접값보다 작다면
                near_value = Math.abs(sum); //바꿔주자
                ans1 = arr[left]; //마찬가지
                ans2 = arr[right];
            }
            if(sum < 0) left++;
            //또한 만약 합이 0보다 작다면 좌측 포인터를 이동해서 값을 키워주자
            else right--; //위 코드의 반대의 경우다. 크면 우측 포인터를 줄여주자
        }
        System.out.println(ans1 + " " + ans2);
    }
}
