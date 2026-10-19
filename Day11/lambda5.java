package Day11;
import java.util.function.*;
public class lambda5 {
    public static void main(String args[]){
        Consumer<String> smsNotifier = mobNo -> {
            System.out.println("Sending the otp: " + mobNo);

        };
        smsNotifier.accept("+91 12345678");
    }
    
}
