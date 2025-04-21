public class Midterm04 {
    private String model;
    private String manufacture;
    private int id = 100;

    public static int numberOfCar = 0;

    public Midterm04(String manu) {
        this.manufacture = manu;
        ++numberOfCar;
        this.id += numberOfCar;

        System.out.println("Production of Car " + id + ", C1" + " From : " + this.manufacture);
    }

    public Midterm04(String model, String manufact) {
        this.model = model;
        this.manufacture = manufact;
        ++numberOfCar;
        this.id += numberOfCar;

        System.out.println("Production of Car " + id + ", " + this.model + " From : " + this.manufacture);
    }

    public static int getNumberOfCars() {
        return numberOfCar;
    }

    public static void showNumberOfCars() {
        System.out.println("Cumulative production : " + numberOfCar);
    }

    public void setManufacture(String manu) {
        if (this.manufacture == null) {
            this.manufacture = manu;
        } else {
            System.out.println("[error] Maker can't be changed after production");
        }
    }

    public static void main(String[] args) {
        Midterm04 obj1 = new Midterm04("A3", "seoul");
        Midterm04 obj2 = new Midterm04("busan");
        Midterm04 obj3 = new Midterm04("A3", "china");

        int num = Midterm04.getNumberOfCars();
        System.out.println("Num of Car : " + num);
        obj3.setManufacture("seoul");
        Midterm04.showNumberOfCars();
    }
}
