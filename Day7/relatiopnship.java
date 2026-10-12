package Day7;

/* 
class Engine{
    void start(){
        System.out.println("The engine has Started");

    }
}

class Car{
    private Engine engine;
    Car(){
        this.engine = new Engine();

    }
     void drive (){
        engine.start();
        System.out.println("Car is moving");
     }
    
}

*/




/* 

class Address{
    String city;
    String state;
    int pincode;


Address (String city, String state, int pincode){
    this.city = city;
    this.state = state;
    this.pincode = pincode;
}

void displayAddress(){
    System.out.println(city + "," + state + "," + pincode);
 }
}


class Student{
    String name;
    int rollNo;
    Address address;

    Student(String name, int rollNo, Address address){
        this.name = name;
        this.rollNo = rollNo;
        this.address = address;
    }



void displayProfile(){
    System.out.println("Student:" + name);
    System.out.println("Roll no " + rollNo);
    System.out.println("Lives at: ");


    address.displayAddress();
}
}

*/




/* 
class Battery{
    int capacity;
    Battery(int capacity){
        this.capacity = capacity;
    }
    void drain(){
        System.out.println("battery drained by" + capacity + "maH");
    }
}


class Camera{
    int pix;

    Camera(int pix){
        this.pix = pix;
    }

    void takePhoto(){
        System.out.println("Click... Captured a photo with" + pix + "sensor");
    }
}

class Phone{
    String brand;
    Battery battery;
    Camera camera;

    Phone(String brand, Battery battery, Camera camera){
        this.brand = brand;
        this.battery = battery;
        this.camera = camera;
    }

    void snapPiture(){
        System.out.println("[" + brand + "]" + " is active. ");
        camera.takePhoto();
        battery.drain();
    }
}
    */


/* 
class Student{
   String name;
   static String CollegeName = "IIT Bombay";
   Student(String name){
    this.name = name;
   }

   void stucol(){
    System.out.println(name + "studies in" + CollegeName);
   }

}
*/

class Shape{
    private String color;

    void setColor(String color){
        this.color = color;

    }

    String getColor(){
        return color;
    }

    abstract double  calculateArea();
}

class Circle extends Shape{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    @Override
    double calculateArea(){
        return (3.14) * radius * radius;
    }
}

class Rectangle extends Shape{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }

    @Override 
    double calculateArea(){
        return (3.14) * radius * radius;
    }
}

class rectangle extends Shape{
    double length, breadth;
    Rectangle( double length, double breadth){
        this.length = length;
        this.breadth = breadth;
    }
}