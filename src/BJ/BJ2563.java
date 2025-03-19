package BJ;
/*
* 생긴게 어려워보여서 조금 두려웠는데
* 실버 5다운 난이도였다 생각보다 쉬웠다
* 입력해주는것은 정사각형의 꼭짓점 중 왼쪽 아래의 좌표를 준다
* 모든 정사각형의 가로세로 크기는 10으로 고정이라하니
* 해당 좌표부터 x+10 y+10의 좌표가 우측 상단이 되므로 그 사이에 있는
* 모든 좌표를 1과 0중 하나로 삼아두면 되고
* 좌표내 모든 1*1 정사각형의 갯수만 새면 된다. > 넓이는 1이된다.*/
import java.io.*;
import java.util.*;

public class BJ2563 {
    static int total = 0; //넓이
    static boolean[][] coordinates = new boolean[100][100]; //가로세로가 100으로 문제에서 고정시켜줬다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());//입력할 정사각형의 수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());//x좌표
            int b = Integer.parseInt(st.nextToken());//y좌표
            looking_for_width(a, b); //가서 넓이를 찾아와라
        }
        System.out.println(total); //총 넓이를 출력해라
    }
    static void looking_for_width(int a, int b){
        for (int i = a; i < a+10; i++) { //a+9까지 볼거니까 a+10으로 반복
            for (int j = b; j < b+10; j++) { //위와 동일
                if(!coordinates[i][j]) { //만약 해당 좌표가 false > 아직 없는 좌표점이면
                    //이렇게 하면 겹치는 좌표는 자동으로 제외된다
                    coordinates[i][j] = true; //추가해줘
                    total++; //넓이 1 늘려줘
                }
            }
        }
    }
}
