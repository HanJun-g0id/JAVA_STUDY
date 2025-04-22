public class Midterm06 implements Order, Payment {
    private static int price = 0;

    @Override
    public void addMenu(int value) {
        price += value;
        System.out.println("addMenu Price " + value + " (total " + price + ")");
    }

    @Override
    public int getPay() {
        return price;
    }

    public static void main(String[] args) {
        Midterm06 obj = new Midterm06();
        Order a = obj;
        Payment b = obj;

        a.addMenu(5000);
        a.addMenu(7000);
        System.out.println("Total " + b.getPay());
    }
}
