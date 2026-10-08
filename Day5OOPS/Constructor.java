 package Day5OOPS;
 
 class Car {

    String color;
    String brand;
    int speed;

    Car(String color, String brand, int speed) {
        this.color = color;
        this.brand = brand;
        this.speed = speed;
    }

    void accelerate(int incr) {
        speed = speed + incr;
    }

    void display() {
        System.out.println("Color: " + color);
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed);
    }
}

public class Constructor {

    public static void main(String[] args) {

        Car car1 = new Car("Red", "BMW", 100);

        car1.display();

        car1.accelerate(20);

        System.out.println("After acceleration:");

        car1.display();
    }
}