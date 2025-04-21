public class cylinder extends circle {
    int high;

    public cylinder(int r, int high) {
        super(r);
        this.high = high;
    }

    @Override
    public double volume() {
        return r * r * 3.14 * high;
    }
}
