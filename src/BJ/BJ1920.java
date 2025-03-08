package BJ;
/*
M 배열에 있는 수가
N 배열에 있으면 1 없으면 0이 나오게 하는 문제
* 내가 최초로 작성한 코드이나
* 시간제한에 걸리게 됐다
* 실행은 정상적으로 되나 코드가 느리다는 이유로 실패를 받음*/
import java.util.Scanner;

public class BJ1920 {
    static int N; //첫번째 입력 할 인자 수
    static int M; //두번째 입력 할 인자 수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr1 = new int[N]; //첫번째 인자 배열
        for(int i=0; i<N; i++) arr1[i] = sc.nextInt();
        M = sc.nextInt();
        int[] arr2 = new int[M]; //두번째 인자 배열
        for(int i=0; i<M; i++) arr2[i] = sc.nextInt();
        sc.close();
        for(int i=0; i<M; i++){
            System.out.println(cheakMachine(arr1, arr2[i])); //N의 배열과 M의 수 하나씩 보내서 비교
        }
    }
    static int cheakMachine(int[] arr, int a){
        int res = 0; //기본값은 0
        for(int i=0; i<M; i++){
            if(arr[i] == a){ //만약 N의 수 중 같은 글자가 있다면
                res = 1; //값을 1로 변경 >> 중복이여도 무조건 1이 나오게함
            }
        }
        return res; //반환
    }
}