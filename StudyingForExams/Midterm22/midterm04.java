public class midterm04 {
    static int numberOfCar = 0;
    private String model;
    private String make;

    public midterm04(String make){
        this.make = make;
        System.out.println("자동차 1대 생산, 생산지 : "+make);
        ++numberOfCar;
    }
    public midterm04(String model, String make){
        this.model = model;
        this.make = make;
        System.out.println("자동차 1대 생산, 생산지 : "+make);
        ++numberOfCar;
    }

    public static int getNumberOfCars() {return numberOfCar;}


    public static void ShowNumberOfCar(){
        System.out.println("누적 생산량 : " + numberOfCar + "대");
    }

    public static void main(String[] args) {
        midterm04 obj1 = new midterm04("A3", "seoul");
        midterm04 obj2 = new midterm04("busan");
        midterm04 obj3 = new midterm04("A3", "china");

        int num = getNumberOfCars();
        System.out.println(num+"대");
        midterm04.ShowNumberOfCar();
    }
}
