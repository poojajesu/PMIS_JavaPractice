package Day11;

@FunctionalInterface
interface SquareRoot{
    double find(double number);
}

public class lambda4 {
    public static void main(String args[]){
        SquareRoot s = number -> Math.sqrt(number);

        System.out.println(s.find(25));
    }


    
}
