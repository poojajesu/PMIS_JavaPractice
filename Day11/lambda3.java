package Day11;
import java.util.function.*;

public class lambda3 {
    public static void main(String args[]){
        Function<String, Integer> getLength = name -> name.length();
        System.out.println(getLength.apply("jav"));
        System.out.println(getLength.apply("John"));
    }


    
}
