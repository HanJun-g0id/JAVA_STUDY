package BJ;
/*
* 솔직히 좀 까다로웠던 거 같다
* 골드바흐 파티션을 찾아내는 문제로
* 2보다 큰 짝수는 두 소수의 합으로 나타낼 수 있다는 것이다
* 문제는 주어진 수에 골드바흐 파티션의 수를 출력하는 것으로
* 예를 들어 6의 경우 3 + 3이라는 소수의 합으로 나타낼 수 있고
* 이외에 다른 조합이 없기에 1을 출력한다는 방식이다
* 소수를 걸러내기위해 에라토스테네스의 체 방식을 사용했다*/
import java.io.*;

public class BJ17103 {
    static boolean[] isPrime = new boolean[1000001]; //소수 판별 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Sieve_of_Eratosthenes();//소수 배열 불러오기

        int N = Integer.parseInt(br.readLine()); //실행횟수
        for(int i = 0; i < N; i++){

            int M  = Integer.parseInt(br.readLine()); //2보다 큰 짝수 입력

            int partitionCount = 0; // 파티션의 짝수

            if(M % 2 == 0 && M != 0) { //짝수인 경우만
                // 순서만 다르고 두 소수가 같은 경우는 같은 파티션임으로 M/2한다.
                for (int j = 2; j <= M / 2; j++) {
                    // 두 수의 합이 M일때 그 두수가 모두 소수일 때
                    if (!isPrime[j]) { // 소수일 때(not isPrime >> 소수라면 값이 false일 것)
                        if (!isPrime[M - j]) { // (입력한 수)-소수 = 소수일때
                            partitionCount++; //파티션을 찾은 것이다
                        }
                    }
                }
                bw.write(partitionCount + "\n");
            }else{
                bw.write(0 + " \n");
            }
        }
        br.close();
        bw.flush();
        bw.close();

    }

    static void Sieve_of_Eratosthenes(){ //에라토스테네스의 체(소수 거르기)
        isPrime[0] = isPrime[1] = true;//0과 1은 소수가 아니니까 미리 제외시키자

        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i] == false) {//만약 현 위치값이 거짓이라면(디폴트 :  거짓)

                for (int j = 2; i * j < isPrime.length; j++) {
                    //2의 배수부터 전부 참으로 변경시킨다
                    //의미 : 소수 배열에서 얘네를 전부 제외 시킨다.
                    //단 해당 숫자(2,3등은 제외, 4는 이미 2에서 제외되었을 것)
                    isPrime[i * j] = true;
                }
            }

        }
    }
}
