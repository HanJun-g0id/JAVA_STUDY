public class box extends rect {
    int high;

    public box(int width, int height, int high) {
        super(width, height);
        this.high = high;
    }

    @Override
    public double volume() {
        return width * height * high;
    }
}
