package BJ;
/*
* 그리디 알고리즘 문제를 풀어보고 싶어서(현기의 추천)
* 풀어본 문제로 문제의 구현이 생각보다 어려웠다
* 이전에도 그리디 알고리즘을 푼 적은 있었으나 이 정도는 아니었어서
* 사실상 제대로는 처음해본 문제같았다
* 놀랍게도 이 코드는 틀렸습니다를 받았고
* 접근이 좀 틀린 듯 했지만 이 또한 학습의 흔적이라 생각해서 커밋하기로했다
* 문제의 중점은 그룹화를 하고 그룹의 개수를 세는게 중점이었으며
* 내 코드는 몇몇 예제에서 오류가 좀 나는 듯 하다.
* 반례를 GPT한테 물어봐서 찾았는데
* BBBRBB 같은 코드는 결과값이 기대값과 다를 거라는데
* 잘 나와서 왜 이게 틀렸다고 하는지 모르겠다
* 문제는 파랑과 빨강의 나열을 색칠하되 같이 붙어있는 건 하나의 그룹으로 보고
* 최소한의 색칠 횟수를 찾는 것이다
* 그리디 알고리즘 문제답게 최소를 구해야하며
* 그냥 새는 것이 아닌 이전 값이 B냐 R이냐에 따라 수가 달라지기에
* 경우를 복잡하게 따져야해서 좀 머리아팠다(심지어 틀렸다)
* 일단 더 많은 놈으로 다 칠하고 세부적인 것들을 하는 방식을 사용했으며
* 구체적인 풀이는 주석을 따라가다보면 이해되게 써두었다.
* */
import java.io.*;
import java.util.*;

public class BJ20365 {
    public static int N;
    public static char[] problemList;
    public static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()); //새 입력을 위한 토큰 초기화
        problemList = new char[N];
        problemList = st.nextToken().toCharArray();
        //첫번째 토큰을 가져오고 char배열로 전환함
        System.out.println(HowMany());
    }

    //이것 저것 계산할 녀석이다
    public static int HowMany() {
        int countB = 0;

        for (int i = 0; i < N; i++) if (problemList[i] == 'B') countB += 1;
        //입력 받은 배열에서 B의 개수를 세어둔다.

        if (countB >= (N - countB)) {
            answer += 1; //다 칠해둔 값
            int idx = 0; //탐색할 놈
            //만약 입력 받은 B의 개수가 절반 이상이면 B로 모두 칠한다
            //이 부분이 탐욕적 선택 즉 그리디 알고리즘인 부분이다

            boolean beforeIsRed = false; //이전 색이 빨간색인지 판단할 것

            while (idx < N) {
                if (problemList[idx] == 'R') { //만약 R을 만났다면
                    if (!beforeIsRed) beforeIsRed = true;
                    //그런데 R이 처음이라면 만났다고 바꾼다
                } else if (problemList[idx] == 'B') {
                    //만약 B를 만났다면
                    if (beforeIsRed) {
                        //그런데 전에 R을 만났다면(R이 연속적으로 나왔을 경우에도)
                        answer += 1; //해당 구간 이전은 칠하고
                        beforeIsRed = false; //칠했으니까 다시 값 변경
                    }
                }
                idx += 1; //다음문자로 넘어간다
            }
            if (beforeIsRed) answer += 1;
            //BBBR과 같은 예제인 경우 R이 마지막이라 다음에 B가 오지않아서
            //카운트가 안될 경우를 위한 놈으로 최종 beforeIsRed가 true라면 1증가 시킨다

        //위 코드의 반대의 경우로 변수명 말고 알고리즘은 동일하다
        }else if(countB < (N - countB)) {
            answer += 1;
            int idx = 0;
            boolean beforeIsBlue = false;

            while (idx < N) {
                if (problemList[idx] == 'B') {
                    if (!beforeIsBlue) beforeIsBlue = true;
                }else if (problemList[idx] == 'R') {
                    if (beforeIsBlue) {
                        answer += 1;
                        beforeIsBlue = false;
                    }
                }
                idx += 1;
            }
            if (beforeIsBlue) answer += 1;
        }
        return answer;
    }
}
/*
이 코드가 그리디 알고리즘인 부분은
가장 많은 색을 기본적으로 칠하는 탐욕적 선택을 한 후
필요한 최소의 상황만 색칠하는 방식이기 때문이다
 */
