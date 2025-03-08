package BJ;

import java.util.Scanner;

public class BJ2941 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        System.out.println(getAlphabetCount(input));
        sc.close();

    }
    static int getAlphabetCount(String str){ //여기서 문자를 분석함
       int count = 0; //크로아티아 문자의 수, 반복문이 끝날 때(크로아티아 문자를 하나 찾았을 때)마다 값이 증가함
       for(int i = 0; i < str.length(); i++){ //문자열 길이만큼 진행
           if(str.charAt(i) == 'c'){ //만약 c가 있다면?
               if(i < str.length() - 1){ //c가 마지막 문자가 아니라면?
                   if(str.charAt(i + 1) == '=') i++; //만약 c 다음에 = 이 온다면?
                   // i+1까지가 하나의 문자열이므로 다음 문자를 찾기위해 =이 들어간 문자열을 건너뛰고 반복문 실행
                   else if(str.charAt(i + 1) == '-') i++; //c- 일 경우
               }
           }
           else if(str.charAt(i) == 'd') {
               if (i < str.length() - 1) {
                   if (str.charAt(i + 1) == 'z') {
                       if (i < str.length() - 2) if (str.charAt(i + 2) == '=') i += 2; //dz= 일 경우
                       //dz=는 길이가 길기에 str.length()-2를 해주어야한다.
                       //마찬가지로 i값도 2만큼 더 늘린다.
                   } else if (str.charAt(i + 2) == '-') i++; //d- 일 경우
               }
           }
           else if(str.charAt(i) == 'l') {
               if(i < str.length() - 1){
                   if(str.charAt(i + 1) == 'j') i++; //lj 일 경우
               }
           }
           else if(str.charAt(i) == 'n') {
               if(i < str.length() - 1){
                   if(str.charAt(i + 1) ==  'j') i++; //nj 일 경우
               }
           }
           else if(str.charAt(i) == 's') {
               if(i < str.length() - 1){
                   if(str.charAt(i + 1) ==  '=') i++; //s= 일 경우
               }
           }
           else if(str.charAt(i) == 'z') {
               if(i < str.length() - 1){
                   if(str.charAt(i + 1) ==  '=') i++; //z= 일 경우
               }
           }
           count++;
       }
       return count;
    }
}
