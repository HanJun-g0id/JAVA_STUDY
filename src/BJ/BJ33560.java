package BJ;
/*
* 대회에 흥미로워 보이는 이름의 문제가 있어서
* 문제를 읽어보니 단순하길래 그대로 들어갔는데
* 문제를 제대로 안 읽어서 3시간 동안 코딩한 비극의 문제
* 실버 3다운 난이도는 없는 거 같다
* 문제가 설명이 불친절해서 푸는게 어려운거지
* 문제를 이해하면 내용 자체는 쉬운 문제다
* 주사위 면에 따라 나오는 효과가 다르고
* 최종적으로 점수에 따라 보상이 몇갠지 출력하는 문제*/
import java.io.*;

public class BJ33560 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 몇 개의 주사위를 굴릴 것인가
        String[] inputs = br.readLine().split(" ");
        br.close();

        int time = 0, score = 0, plus_time = 4, plus_point = 1; //초깃값 설정
        int result1 = 0, result2 = 0, result3 = 0, result4 = 0;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(inputs[i]); //굴려서 나온 주사위 눈금

            // 게임 종료 조건일 때, 점수 정산 먼저
            if (time > 240 || input == 1) { //턴 시간이 240이 넘거나 1이 들어왔을 경우
                if (score >= 35 && score < 65) result1++;
                else if (score >= 65 && score < 95) result2++;
                else if (score >= 95 && score < 125) result3++;
                else if (score >= 125) result4++;

                // 초기화
                time = 0;
                score = 0;
                plus_time = 4;
                plus_point = 1;

                if (input == 1) continue; //1일 경우 남은 주사위를 이어서 굴린다
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

        // 정답 출력
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            bw.write(result1 + "\n" + result2 + "\n" + result3 + "\n" + result4 + "\n");
        }
    }
}