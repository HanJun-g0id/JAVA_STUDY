package BJ;
/*코드 해결이 안됨
* 다른 날에 다시 해결해서 올려둘 예정*/
import java.io.*;

public class BJ33560 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" ");
        br.close();

        int time = 0, score = 0, plus_time = 4, plus_point = 1;
        int result1 = 0, result2 = 0, result3 = 0, result4 = 0;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(inputs[i]);

            // 게임 종료 조건일 때, 점수 정산 먼저
            if (time > 240 || input == 1) {
                if (score >= 35 && score < 65) result1++;
                else if (score >= 65 && score < 95) result2++;
                else if (score >= 95 && score < 125) result3++;
                else if (score >= 125) result4++;

                // 초기화
                time = 0;
                score = 0;
                plus_time = 4;
                plus_point = 1;

                if (input == 1) continue;
            }

            // 입력값에 따른 변화
            switch (input) {
                case 2: // 점수 증가량 감소 or 시간 증가
                    if (plus_point > 1) plus_point /= 2;
                    else if(plus_point == 1) plus_time += 2;
                    break;
                case 3: // 변화 없음
                    break;
                case 4: // 시간 56 증가
                    time += 56;
                    break;
                case 5: //plus_time 감소
                    if (plus_time > 1) plus_time -= 1;
                    break;
                case 6: // 점수 증가량 증가 (최대 32)
                    if (plus_point < 32) plus_point *= 2;
                    break;
            }

            //점수 및 시간 증가
            score += plus_point;
            time += plus_time;
        }

        //마지막 게임 결과 정산 (240초 초과 안 했을 때)
        if (time <= 240) {
            if (score >= 35 && score < 65) result1++;
            else if (score >= 65 && score < 95) result2++;
            else if (score >= 95 && score < 125) result3++;
            else if (score >= 125) result4++;
        }

        // 정답 출력
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(result1 + "\n" + result2 + "\n" + result3 + "\n" + result4 + "\n");
        }
    }
}