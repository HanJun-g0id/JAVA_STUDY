import javax.swing.Timer;

public class midterm08 {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {1, 2}, {1}, {1, 2, 3}};

        class RowIndex {
            int value = 0;
        }
        //람다에선 final 또는 유사 final 값만 수정 가능하기에
        //그걸 굳이 안쓰고 해결하려면 다음과 같이 내부 클래스 필드를
        //사용하는 방법이 있다

        RowIndex index = new RowIndex(); // 변경 가능한 객체 생성

        //Timer 클래스 = 딜레이마다 행동함, <사용법> Timer(딜레이, 행동);
        //이때 행동은 '매개변수->{기능}' 의 형태
        Timer t = new Timer(1000, e -> {
            if (index.value < a.length) {

                for (int j = 0; j < a[index.value].length; j++) {
                    System.out.print(a[index.value][j] + " ");
                }

                /*참고로 위 반복문은 아래 코드로 대체할수도 있는데
                for (int num : a[index.value]) { System.out.print(num + " "); }
                해당 코드는 for-each문으로 a[index.value] 배열 안에 있는 값을
                앞에서부터 하나씩 꺼내서 num에 저장하고, 그걸 가지고 반복해! 라는 의미를 지닌다*/

                System.out.println();
                index.value++;
            } else {
                ((Timer) e.getSource()).stop(); // 타이머 종료
            }
        });

        t.start();

        // 콘솔 창이 너무 빨리 닫히지 않도록 대기
        try {
            Thread.sleep(5000); // 1초 × 행 개수만큼 기다리기
        } catch (InterruptedException e) {//예외처리
            e.printStackTrace();
        }
    }
}
