package BJ;
/*
* 이분탐색을 좀 더 공부해보고 싶어서 찾은 문제
* 여전히 약간 어렵긴한데 슬슬 방식에 익숙해지긴 하는 거 같다
* 집 사이 거리를 최대로 하되 집들마다 설치해야하는 공유기 개수를 정해놔서
* 머리로 생각해야하는게 조금 더 많다
* 최소거리를 구해두고 그 거리 만큼씩 띄워서 설치가 가능한지 경우를 따지는건데
* 만약 안되는 경우 high low값을 바꿔서 중앙값(최소거리)를 바뀌게하고
* 또 새로운 경우를 테스트하며 찾아내는 방식이다
* 추가적으로 버퍼를 쓰는 거 보다 스캐너가 더 편할 거 같은 문제라 스캐너를 사용했다
* 버퍼는 한 줄을 다 읽어서 첫 입력 때 2개를 입력하다가 한번에 입력되기에 또 토큰으로
* 나눠주는 작업도 해야해서 그냥 스캐너로 했다.*/
import java.util.*;

public class BJ2110 {
    public static int[] house;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //집의 개수
        int C = sc.nextInt(); //공유기 개수

        house = new int[N]; //집 좌표 배열

        for(int i = 0; i < N; i++) {
            house[i] = sc.nextInt();
        }
        Arrays.sort(house); //이분 탐색을 위한 배열

        int low = 1; //최소 거리가 가지는 최솟값
        int high = house[N-1] - house[0] + 1; //최소거리가 가지는 최댓값

        while(low < high) {
            int mid = (low + high)/2; //이분탐색을 위한 중앙값 계산

            if(install(mid) < C) high = mid;
            //만약 리턴한 카운트 값이 공유기 갯수보다 작다면
            //거리를 좁혀야해서 high 값을 줄인다
            else low = mid + 1;
            //반대의 경우 low 값을 늘려서 거리를 벌리고 최소거리가 가질 수 있는 최대거리를 찾는다
        }
        System.out.println(low - 1); //UpperBound가 탐색값을 초과하는 첫번째 값을 찾아내므로 -1을 해준다
    }

    public static int install(int dis){
        int count = 1; //첫번째 집에는 무조건 설치한다고 가정
        int last_house = house[0];
        for(int i = 1; i < house.length; i++) {
            int locate = house[i]; //현재 탐색하는 집
            if(locate - last_house >= dis) {
                //직전 설치 집과 현재 탐색 집의 거리가
                //최소거리보다 크거나 같을때 공유기 설치 개수를 늘려주기
                count++;
                last_house = locate; //마지막 설치 위치 갱신
            }
        }
        return count;
    }
}
