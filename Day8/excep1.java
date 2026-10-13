package Day8;

public class excep1 {

    public static void main(String args []){
        try{
            int[] num = {10,20,30};
            System.out.println(num[5]);
            int res = 10/10;
            System.out.println(res);

        } catch (ArrayIndexOutOfBoundsException e){

            System.out.println("Invalid array Index");

        } catch(ArithmeticException e){
              System.out.println("Cannot divide by 0");

        } finally{
            System.out.println()
        }
    }
    
}
