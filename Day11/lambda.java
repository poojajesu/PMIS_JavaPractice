package Day11;

@FunctionalInterface
interface Greeter{
    void greet(String name);
}
public class lambda {
    public static void main(String args[]){
        Greeter g = new Greeter(){

            @Override
            public void greet(String name){
                System.out.println("Hello" + name);

            }
        };
        g.greet("Pooja");
    }
    
}
