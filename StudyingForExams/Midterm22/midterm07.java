public class midterm07 implements animal {
    public String name;

    @Override
    public String walk() { return "able to walk, "; }

    @Override
    public String fly() { return "able to fly, "; }

    @Override
    public String sing() { return "ablo to sing, "; }

    public midterm07(String name) { this.name = name; }

    public String toString() { return this.name + " is " + walk() + fly() + sing(); }

    public static void main(String[] args) {
        midterm07 obj = new midterm07("Bird");
        System.out.println(obj);

    }
}
