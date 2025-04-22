public class midterm03 {
    public void sum(int a, int b){
        System.out.printf("%d\n", a+b);
    }
    public void sum(int a, int b, int c){
        System.out.printf("%d\n", a+b+c);
    }

    public static void main(String[] args) {
        midterm03 obj = new midterm03();
        obj.sum(10,20,30);
        obj.sum(10,20);
    }
}
