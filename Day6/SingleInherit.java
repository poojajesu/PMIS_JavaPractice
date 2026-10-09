package Day6;

class Animal {
    void eat(){
        System.out.println("Animal eats ");
    }
}
class Cat extends Animal{
    void meow(){
        System.out.println("Cat does meow meow");
    }
}

class Dog extends Animal{
    void bark(){
        System.out.println("Dog barks");
    }
}
public class SingleInherit {
    public static void main(String args[]){
        Cat myCat = new Cat();
        myCat.eat();
        myCat.meow();

        Dog myDog = new Dog();
        myDog.bark();


    }
    
}
