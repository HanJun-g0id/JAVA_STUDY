package BJ;

import java.util.Scanner;
//전 코드가 잘 실행되는 줄 알았으나 오류가 나서 몇가지 수정을 한 코드
/*
* 3가지를 챗지피티의 조언에 따라 수정했다.
* 1. dz=을 제대로 처리하지 못한다고 하여 dz= 체크를 맨 앞에서 처리한다.
* 2. d- 처리에서 IndexOutOfBoundsException 발생 가능성이 있다고하여 문자열 길이 검사 과정을 재검토함
* 3. Scanner 사용 후 close()의 위치 문제를 지적받았는데 아직 잘 이해는 안되긴함
* Scanner를 System.in에서 직접 닫으면, 이후 입력을 받는 다른 코드에서 문제 발생 가능이라고 한다*/
public class BJ2941_s {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.next();
            System.out.println(getAlphabetCount(input));
        }
    }

    static int getAlphabetCount(String str) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 2 && str.substring(i, i + 3).equals("dz=")) {
                i += 2; // "dz="은 3글자이므로 2칸 추가
            } else if (i < str.length() - 1) {
                String twoChars = str.substring(i, i + 2);
                /*
                * substring()메서드
                * 문자열의 일부를 잘라서 새 문자열을 만드는 메서드다
                * 인덱스로는 추출 시작 위치(포함)
                * 추출 종료 위치(미포함)이 있다. : 미입력시 문자열 끝까지 자름
                * */
                if (twoChars.equals("c=") || twoChars.equals("c-") ||
                        twoChars.equals("d-") || twoChars.equals("lj") ||
                        twoChars.equals("nj") || twoChars.equals("s=") ||
                        twoChars.equals("z=")) {
                    i++; // 크로아티아 알파벳이므로 i를 하나 추가
                }
            }
            count++; // 문자 하나를 확인했으므로 증가
        }
        return count;
    }
}
    /*
    * 코드 수정 내용
    1. "dz="을 최우선적으로 검사
    -  "dz="이 "d-"보다 먼저 처리되도록 순서 조정
    -  substring(i, i + 3)을 사용할 때 i < str.length() - 2 조건 추가하여 예외 방지
    2. d- 등 2글자 크로아티아 알파벳을 그다음 검사
    - substring(i, i+2)를 사용하여 "c=", "c-", "d-", "lj", "nj", "s=", "z="을 확인
    3. 반복문을 돌면서 문자 개수를 정확히 카운트
    - count++를 모든 문자에서 증가하도록 유지
    4. try-with-resources를 적용하여 Scanner를 안전하게 닫음
    */

