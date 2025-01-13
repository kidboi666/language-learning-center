package ch06.sec07;

public class CarExample {
    public static void main(String[] args) {
        Car car1 = new Car("자가용");
        System.out.println("Car company: " + car1.company);
        System.out.println("Car model: " + car1.model);
        System.out.println("Car color: " + car1.color);

        Car car2 = new Car("택시", "은색");
        System.out.println("Car company: " + car2.company);
        System.out.println("Car model: " + car2.model);
        System.out.println("Car color: " + car2.color);
    }
}
