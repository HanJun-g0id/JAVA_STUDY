package BJ;
/*
M 배열에 있는 수가
N 배열에 있으면 1 없으면 0이 나오게 하는 문제
* 내가 최초로 작성한 코드가 시간제한에 걸려서
인터넷을 뒤지다가 이분 탐색을 사용해서 다시 작성한 코드
참고로 이분 탐색을 코드로 직접 구현했지만(추가적인 기능을 위해)
자바에는 Arrays 클래스에 binarySearch 메소드를 제공하고 있다고 하며
해당 문제 해결에서도 사용은 가능하다
* 추가적으로 굳이 M의 배열은 필요한 것 같지 않아서 제외했다.*/

import java.util.Arrays; //해당 클래스에 대해서 노션에 정리해둘 계획이다.
import java.util.Scanner;

public class BJ1920_s {
    static int N; //첫번째 입력 할 인자 수
    static int M; //두번째 입력 할 인자 수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[N]; //첫번째 인자 배열
        for(int i=0; i<N; i++) arr[i] = sc.nextInt();
        M = sc.nextInt();

        // 탐색 할 배열은 반드시 정렬되어있어야한다.
        Arrays.sort(arr); //Arrays 클래스에 있는 sort 메소드로 오름차 정렬시켜준다.

        StringBuilder sb = new StringBuilder(); //문자열 객체 생성
        for(int i = 0; i < M; i++) {

            // 찾고자 하는 값이 있을 경우 1, 없을 경우 0을 출력해야한다.
            if(binarySearch(arr, sc.nextInt()) >= 0) {
                sb.append(1).append('\n'); //문자열 추가
            }
            else {
                sb.append(0).append('\n');
            }
        }
        sc.close();
        System.out.println(sb);
    }

    //이분 탐색 알고리즘
    public static int binarySearch(int[] arr, int key) {

        int lo = 0;					// 탐색 범위의 왼쪽 끝 인덱스
        int hi = arr.length - 1;	// 탐색 범위의 오른쪽 끝 인덱스

        // lo가 hi보다 커지기 전까지 반복한다.
        while(lo <= hi) {
            int mid = (lo + hi) / 2;	// 중간위치를 구한다.

            // key값이 중간 위치의 값보다 작을 경우
            if(key < arr[mid]) {
                hi = mid - 1;
            }
            // key값이 중간 위치의 값보다 클 경우
            else if(key > arr[mid]) {
                lo = mid + 1;
            }
            // key값과 중간 위치의 값이 같을 경우
            else {
                return mid;
            }
        }

        // 찾고자 하는 값이 존재하지 않을 경우
        return -1;
    }
    /*
    * 이분 탐색 알고리즘이란?
    * 경우의 수가 많을 때 탐색을 더 빨리 하기 위한 방법으로
    * 모든 경우의 수의 중간 값을 찾고 우리가 찾으려는 값과 비교하여 같지 않은 경우
    * 그 값이 중간 값보다 크면 우측으로(오름차순 정렬 기준)
    * 작으면 좌측으로 탐색 범위를 옮기고
    * 다시 해당 위치의 중간 값을 찾고 비교하고를 반복해서 찾아내는 방법이다.
    * 주의 할 점은 비교 할 배열은 반드시 정렬돼야한다.
    * */
}