public class Midterm03 {
    public static int sum(int a, int b) {
        System.out.println("Result " + (a + b) + " is the sum of " + a + "," + b);
        return a + b;
    }

    public static int sum(int a, int b, int c) {
        System.out.println("Result " + (a + b + c) + " is the sum of " + a + "," + b + "," + c);
        return a + b + c;
    }

    public static void main(String[] args) {
        int num1 = Midterm03.sum(10, 20, 30);
        int num2 = Midterm03.sum(10, 20);

        System.out.println("num1 : " + num1);
        System.out.println("num2 : " + num2);
    }
}
