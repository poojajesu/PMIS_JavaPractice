
/*Built in function interfaces

 Predicated<T> test(t) T -> t/f 1/0

*/

package Day11;
import java.util.*;
import java.util.function.Predicate;


public class lambda2 {
    public static void main(String args[]){
        Predicate<Integer> isAdult = age -> age >=18;

        System.out.println(isAdult.test(20));
        System.out.println(isAdult.test(15));
    }
    
}
