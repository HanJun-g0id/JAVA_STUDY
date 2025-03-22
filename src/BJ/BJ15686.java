package BJ;
/*오랜만에 많은 걸 공부하고 적용시켜서 만든 코드이다
* 골드5인만큼 나에게 확실히 어려웠고 GPT의 도움도 여러번 받아서 겨우 완성했다
* 문제는 도시 구조를 입력하는데 이때 치킨집과 집이 찍힌다 그리고 우리는 몇개의 치킨집만을
* 제외하고 나머진 폐업시켜야한다.
* 좌표를 찾고 집과의 거리를 잰다음 거리가 제일 짧은걸 선호해서
* 우리가 입력한 m개 만큼의 집만 남겨야하는 문제로
* 최종 출력값은 그때 도시의 모든 치킨거리의 합을 출력한다
* 설명을 좀 못한 거 같은데 문제를 읽어보면 금방 이해가 될 것이다.
* 또한 브루트포스에서 이분탐색 다음으로 새로운 기법인
* 백트래킹을 학습하고 사용해본 코드기도 하다
* 그것에 대해서는 이번에 처음써본 리스트랑 함께 노션에 정리해둘 예정.*/
import java.util.*;

public class BJ15686 {
    public static class Node{ //좌표를 저장하는 클래스
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m; //크기와 치킨집 개수
    static int[][] city; //도시 구조 배열
    static int min = Integer.MAX_VALUE; //최소 치킨 거리 > 일단 최대값으로 초기화
    static ArrayList<Node> chickenPoint = new ArrayList<>(); //치킨집 좌표를 저장할 리스트
    static ArrayList<Node> housePoint = new ArrayList<>(); //집 좌표를 저장할 리스트
    static boolean[] chickenChecked; //선택된 치킨집 체크용 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); //도시크기와 치킨집 개수 입력
        m = sc.nextInt();

        city = new int[n][n]; //도시 크기 지정

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                city[i][j] = sc.nextInt(); //도시 구조 입력
                if(city[i][j] == 1) housePoint.add(new Node(i, j)); //집이면 리스트에 위치 추가
                else if(city[i][j] == 2) chickenPoint.add(new Node(i, j)); //치킨집이면 리스트에 좌표 추가
            }
        }
        sc.close(); //입력 오류가 나길래 이상해서 추가했더니 오류 안나서 그냥 넣었다..리소스 누수 방지정도,,?

        chickenChecked = new boolean[chickenPoint.size()]; //치킨집 개수만큼 체크 배열 생성
        backtracking(0, 0); //m개의 치킨집을 선택하는 백트래킹 진행
        System.out.println(min); //결과 출력
    }

    public static void backtracking(int count, int index){ //백트래킹으로 m개의 치킨집 선택
        if(count == m){ //만약 m개의 치킨집을 다 선택했다면
            int total = 0; //총 치킨거리
            for(int i = 0; i < housePoint.size(); i++) { //모든 집에 대해서
                int sum = Integer.MAX_VALUE; //해당 집으로부터 최소 치킨거리 초기화
                for (int j = 0; j < chickenPoint.size(); j++) { //모든 치킨집에 대해
                    if(chickenChecked[j]){ //만약 선택됐다면
                        int dist = Math.abs(housePoint.get(i).x - chickenPoint.get(j).x)
                                + Math.abs(housePoint.get(i).y - chickenPoint.get(j).y); //집과의 거리 계산
                        sum = Math.min(sum, dist); //최소거리 갱신
                    }

                }
                total += sum; //모든집 최소거리 합산
            }
            min = Math.min(total, min); //도시 최소거리 합산
            return;
        }
        for(int i = index; i < chickenPoint.size(); i++) { //치킨집 선택하기
            if(!chickenChecked[i]){ //아직 선택되지 않은 치킨집이면
                chickenChecked[i] = true; //선택
                backtracking(count+1, i+1);
                //다음 치킨집 선택을 위해 카운트랑 인덱스 증가 후 재귀 호출
                chickenChecked[i] = false; //백트래킹을 위해 선택을 취소하고 이전 단계로 돌아감
            }
        }
    }
    /*백 트래킹이 뭔데 그래서?
    * 가능성이 없는 경우를 배제하면서 탐색하는 알고리즘이라는데
    * 내가 이해한 바로 적어보자면
    * 그냥 경우의 수를 찾다가
    * 중간에서 가망없는 애는 끝까지 보지도 않고 가지치기(pruning) 해버리는 것이다.
    * 그뒤엔 이전단계로 복귀해버린다.
    * 비선형구조로 보면 이해가 좀 더 빠른데
    * 부모노드 밑에 자식노드 밑에 또 자식노드가 있을 때 각각을 부모, a, b라 부르면
    * a에서 조건이 안 맞으면 b는 확인도 안해보고 자른뒤 a의 부모노드로 백트래킹 해버린다.
    * 아주아주 간단하게는
    * 아파트라는 부모노드 밑에
    * 옆집과 우리집이라는 자식노드가 있고
    * 각각의 자식노드 밑에는 또 자식노드로 거실과 안방 등이 있다
    * 이때 우리가 찾아 갈 곳은 우리집 안방이라고 정해주자
    * 그럼 옆집에서 우리집 안방을 찾을 수 없을테니 들어갔다가도
    * 가능성이 없으니 그 밑 노드인 거실과 안방은 갈 필요도 없이
    * 다시 부모노드인 아파트로 돌아가는 것이다.
    * 그리곤 다시 가능성이 있는 곳인 우리 집을 탐색하는 것이다.*/
}
