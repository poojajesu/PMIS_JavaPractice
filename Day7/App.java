package Day7;

/* 
public class App {
    public static void main(String args[]){

        Car c = new Car();

        c.drive();

        
    }
    
}
    */



public class App {
    public static void main(String[] args) {
        Address a = new Address("Mumbai", "Maharashtra", 43111);

        Student s = new Student("Pooja", 11, a);

        a.displayAddress();
        s.displayProfile();
    }
}