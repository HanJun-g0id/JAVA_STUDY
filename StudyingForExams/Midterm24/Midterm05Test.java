public class Midterm05Test {
    public static void main(String[] args) {
        Midterm05 obj[] = new Midterm05[2];
        obj[0] = new box(2, 3, 1);
        obj[1] = new cylinder(2, 1);

        for (int i = 0; i < 2; i++) {
            System.out.println("volume:" + obj[i].volume());
        }
    }
}
