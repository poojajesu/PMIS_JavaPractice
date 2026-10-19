package Day10;


class Util{
    static <T> void printTwice(T value){
        System.out.println(value);
        System.out.println(value);

    }

    static <T> T firstof(T a, T b){
        return b;
    }
}

public class gen2 {
    public static void main(String args[]){
        Util.printTwice(100);
        System.out.println(Util.firstof("hi", "Hello"));
    }
    
}
