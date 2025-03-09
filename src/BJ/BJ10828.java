package BJ;
/*명령어에 따라 스택내용을 바꾸는 문제인데
* 과거 C언어로 했을 때는 실패했었으나 자바에는 이미 Stack 클래스가 존재하여
* 훨씬 쉽게 해결 할 수 있었다
* 그저 거기 있는 기능들을 실행하기만 하면 됐다
* 각 기능에 대해선 노션에 정리해뒀으며
* 기능을 직접 코드로 구현하는 편이 공부엔 더 적합한 듯 하여
* 후에 직접 구현한 코드도 커밋할 예정
* 새로 학습한 BufferedReader또한 사용했다*/
import java.io.BufferedReader;
import java.io.IOException; //readLine을 위한 예외처리
import java.io.InputStreamReader;
import java.util.Stack; //자바제공 클래스
import java.util.StringTokenizer; //자바제공 클래스

public class BJ10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); // 입력 버퍼 생성
        int N = Integer.parseInt(bf.readLine()); // 명령 개수 입력
        System.out.print(col(N, bf)); // 결과 출력
        //위 메서드가 sb객체를 반환하고 sb객체에는 개행문자가 포함되어 있기에 print로 출력시킴
    }

    static StringBuilder col(int N, BufferedReader bf) throws IOException {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(); //최종적으로 출력할 문자열 객체

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine()); // 매 줄마다 새로운 StringTokenizer 생성
            String command = st.nextToken(); // 각 입력을 분리한 것을 메서드로 확인해서 switch문에 적용

            switch (command) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken())); // push할 값도 가져오기
                    break;
                case "pop":
                    sb.append(stack.isEmpty() ? "-1\n" : stack.pop() + "\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.isEmpty() ? "1\n" : "0\n");
                    break;
                case "top":
                    sb.append(stack.isEmpty() ? "-1\n" : stack.peek() + "\n");
                    break;
            }
        }
        return sb; // 결과 반환
    }
}
