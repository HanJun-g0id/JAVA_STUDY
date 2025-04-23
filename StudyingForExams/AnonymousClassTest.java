interface RemoteControl {
    void turnOff();
    void tutnOn();
}

public class AnonymousClassTest {
    public static void main(String[] args) {
        RemoteControl ac = new RemoteControl() { //무명 클래스 정의
            @Override
            public void turnOff() {
                System.out.println("TV turn Off()");
            }

            @Override
            public void tutnOn() {
                System.out.println("TV turn On()");
            }
        };
        ac.tutnOn();
        ac.turnOff();
    }
}
