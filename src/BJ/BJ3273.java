package BJ;
/*연재형이 추천해준 투 포인터 문제
* 양쪽에 포인터를 잡음으로서 시간복잡도를 줄이는 방식
* 시간제한에 걸리지 않으면 반드시 투 포인터를 써야하는 듯 하다
*/
import java.util.*;
import java.io.*;

public class BJ3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N]; //입력받는 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int x = Integer.parseInt(br.readLine());
        Arrays.sort(arr); //정렬 > 중복조합방지용
        int left = 0; //좌측 포인터
        int right = N - 1; //우측 포인터
        int count = 0; //쌍의 개수

        while (left < right) { //중복 방지를 위해 정렬 후 조건 부여
            int sum = arr[left] + arr[right];

            if (sum == x) { //만약 우리가 원하던 합이라면
                count++;
                left++;   // 다음 조합 찾기 위해 이동
                right--;
            } else if (sum < x) {
                left++;   // 합이 작으니까 더 큰 값 필요 > left 이동
            } else {
                right--;  // 합이 크니까 더 작은 값 필요 > right 이동
            }
        }
        System.out.println(count);
    }
}
