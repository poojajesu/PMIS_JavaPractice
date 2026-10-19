package Day11;
import java.util.function.*;

public class lambda6 {
    public static void main(String args){
        Consumer<String> pr = message -> 
        System.out.println(message);
        pr.accept("Hello i love cricket");
    }
    
}
