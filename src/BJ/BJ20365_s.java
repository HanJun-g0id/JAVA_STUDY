package BJ;
/*
* 이전 코드가 안된다고 하고 그룹 별로 카운트 해야한다는 GPT의 조언에 따라
* 코드를 완전히 갈아엎었다 필요한 것만 넣다보니 코드가 갑자기 너무 단축돼서
* 약간 현타왔지만 이렇게 또 하나 배웠다고 생각한다
* 방식은 이전 글자와 다음 글자가 다른 경우 그룹이 바뀐 것으로 보고
* 마지막에 총 그룹 수를 계산 + 출력한다*/
import java.io.*;

public class BJ20365_s {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String problemList = br.readLine(); //문자열로 입력받았다

        int blueGroup = 0, redGroup = 0; //그룹별 개수

        // 첫 번째 색깔 기준으로 첫 그룹 증가
        if (problemList.charAt(0) == 'B') blueGroup++;
        else redGroup++;

        // 그룹 개수 세기
        for (int i = 1; i < N; i++) {

            if (problemList.charAt(i) != problemList.charAt(i - 1)) { //앞글자와 뒷글자가 다르다면
                if (problemList.charAt(i) == 'B') blueGroup++; //근데 그게 B라면 B그룹 증가
                else redGroup++; //아니면 R이겠지 뭐
            }
        }

        // 최종 최소값 계산
        System.out.println(Math.min(blueGroup, redGroup) + 1);
        //둘 중 최소값인걸 찾고(= 한 색으로 다 칠하고 남은걸 칠할 때 더 적은 케이스)
        //1을 더해준다(처음에 덮어버릴 색)
    }
}
