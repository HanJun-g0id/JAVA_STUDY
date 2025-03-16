package BJ;
/*
숫자카드2라서 방식은 숫자카드 1과 비슷하나 이번엔 몇개 가지고 있는지가 필요한 문제다
앞선 배열을 정렬한 다음 M 카드들이 N 배열에 몇개나 있는지 찾아내면 되는 듯 했으나
카드가 많아질 수록 시간이 오래걸리기에 그걸 해결할 방법을 찾아야했으며 찾고 이해하기까지 시간이 좀 걸렸다
상한값가 하한값을 찾아내는 방법으로 해결하는데 방식은 다음과 같다
먼저 하한값 lower bound, 즉 하한은 찾고자 하는 값 이상의 값이 처음으로 나타나는 위치를 의미한다.
그리고 upper bound, 즉 상한은 찾고자 하는 값을 초과한 값을 처음 만나는 위치다.
생각해보면 중복 원소에 대한 길이 =  (상한 - 하한) 인걸 알 수 있게 된다.
이제 그 다음으로 할 일은 우리가 찾아야하는 값(key)를 주면 그게 몇갠지 찾아내는 것인데 여기서 이분탐색을 쓴다.
이때 하한값과 상한값의 초깃값은 각각 0과 배열의 길이다.
키값과 중앙 값을 비교한 뒤 상한값이나 하한값을 변화시킨다.
키값보다 중앙 값이 크면 상한값을 중앙값으로 내린다.
키값보다 중앙 값이 작으면 하한값을 중앙값+1로 올린다.
이것을 반복하여 upper에서의 lo값과 lower에서의 lo값을
아까 알아냈듯 빼주면 그게 우리가 찾아야할 갯수가 된다.
*/

import java.util.*;
import java.io.*;

public class BJ10816 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //N의 입력
        int[] arr = new int[N]; //우리가 수를 '찾을' 장소
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); //우리가 수를 '찾을' 장소에 입력받기
        }
        Arrays.sort(arr); //이분탐색을 위해선 반드시 정렬을 해야함
        int M = Integer.parseInt(br.readLine()); //M의 입력

        st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            int key = Integer.parseInt(st.nextToken()); //키 값(찾아야하는 값) 입력받기

            // upperBound와 lowerBound의 차이 값을 구한다.
            sb.append(upperBound(arr, key) - lowerBound(arr, key)).append(' ');
        }
        System.out.println(sb); //정답 출력
    }

    private static int lowerBound(int[] arr, int key) { //하한값 구하기
        int lo = 0;
        int hi = arr.length; //초깃값 설정
        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2; // 중간위치를 구한다.
            /*
             *  key 값이 중간 위치의 값보다 작거나 같을 경우
             *  중복 원소에 대해 왼쪽으로 탐색하도록 상계를 내린다.
             */
            if (key <= arr[mid]) hi = mid;
            else lo = mid + 1; //아닐 경우 하한값을 중앙값+1로 이동
        }
        return lo; //하한값 반환
    }

    private static int upperBound(int[] arr, int key) { //상한값 구하기
        int lo = 0;
        int hi = arr.length;

        // lo가 hi랑 같아질 때 까지 반복
        while (lo < hi) {
            int mid = (lo + hi) / 2; // 중간위치를 구한다.
            // key값이 중간 위치의 값보다 작을 경우
            if (key < arr[mid]) hi = mid;
            // 중복원소의 경우 else에서 처리된다.
            else lo = mid + 1;
        }
        return lo; //하한값 반환
    }
}
