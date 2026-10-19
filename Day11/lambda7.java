package Day11;
import java.util.function.*;
import java.util.Random;

public class lambda7{
    public static void main(String args[]){
        Supplier<String> otpGen = () -> {
            Random random = new Random();
            int number = 100000 + random.nextInt(9000000);
            return String.valueOf(number);
        };

        String userOtp = otpGen.get();
        System.out.println("The OTP is :" + userOtp);

    }

}

    

