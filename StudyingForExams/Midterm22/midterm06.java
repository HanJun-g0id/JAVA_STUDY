public class midterm06 implements flyable, drivable {
    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
    @Override
    public void drive() {
        System.out.println("I'm driving");
    }

    public static void main(String[] args) {
        midterm06 obj = new midterm06();
        flyable a = obj;
        drivable b = obj;

        a.fly();
        b.drive();
    }
}
