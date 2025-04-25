import javax.swing.Timer;

public class CallbackTest {

    public static void main(String[] args) {
        MyClass listener = new MyClass();

        Timer t = new Timer(1000, listener);
        t.start();

        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        t.stop();
    }

}

/*public class CallbackTest_Lamda {

    public static void main(String[] args) {
        Timer t = new Timer(1000, event -> System.out.println("beep"));
        t.start();

        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}*/
