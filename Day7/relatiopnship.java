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

