package Day6;


// Problem Statement - 1  Create a Java program using inheritance with two classes: Animal and Dog.Requirements:

class Animal{
    String name;
    
   void eat(){
      System.out.println("Animal eats");
    } 
}

class Dog extends Animal{
    void bark(){
        System.out.println("Dog barks");
    }
}

public class PS1{
public static void main(String args[]){
    
    Dog d = new Dog();

    d.name = "Tommy";
    System.out.println(d.name);

    d.eat();
    d.bark();

}
}