package BJ;

import java.util.Scanner;
/*
* 문제가 의미하는 바는 같은 글자가 바로 뒤이어 연속해서 나오거나 아예 1번씩만 나오거나하는
* 단어들을 세라는 것으로 이해했다. 즉 한번 등장한 단어가
* 해당 문제를 이해하는데는 얼마 안 걸렸으나 계산법을 찾아내기까지 오래걸림
* 또한 주변 조언을 받았음을 적어둠
* 풀이방법 :
* 입력받은 단어의 첫 글자와 그 이전 글자를 비교하고 같으면 참 아니면 거짓을 반환하여 갯수를 셈
* 또한 처음 입력한 단어의 경우에 위 조건보다 먼저 체크하여 증가*/
public class BJ1316 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine()); //이후 문자열 입력을 받기에 안전한 방식으로 입력받음
        int count = 0; //그룹문자의 개수
        for(int i = 0; i < N; i++){
            if(checkGroup(sc.next())) count++; //true 값이 반환되면 count값 증가
        }
        System.out.println(count);
        sc.close();
    }

    static boolean checkGroup(String str){ //그룹문자체크, 입력받은 문자를 바로 넘겨받음
        boolean a[] = new boolean[26]; //영단어 중복 여부를 알기 위한 a~z까지의 배열 생성
        for(int i = 0; i < str.length(); i++) { //입력받은 문자열 길이만큼 실행
            int now = str.charAt(i); //현재 문자의 아스키코드값을 저장
            int prev = (i > 0) ? str.charAt(i - 1) : 0; // 이전 문자의 아스키코드값을 저장하는 변수
            //만약 i가 0 즉, 첫글자라면 이전 글자가 없기에 그냥 0이라는 값을 가지게 한다/
            //어차피 밑에 있는 조건문에서 처음 들어가는 글자로 카운트 될 예정이기 때문
            if (a[now - 'a'] == false) a[now - 'a'] = true; //조건문이 false라고 나오면
                                                            // 처음 나온 문자라는 것이므로 값을 true로 바꿈
            else if (now != prev) return false; //위 조건에서 걸러지지 않았고(처음 나온게 아님)
                                                // 이전 문자와 아스키코드값이 다르다면 그룹문자가 아님
        }
        return true; //여기까지 걸러지지 않았다는건 그룹문자이므로 true 값 반환 >> count 증가
    }
}
