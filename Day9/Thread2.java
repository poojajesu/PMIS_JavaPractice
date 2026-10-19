package Day9;

public class Thread2 extends Thread{
    public static void main(String args[]){

        Thread t = new Thread("my first Thread");

        t.start();

        String str = t.getName();

        System.out.println(str);

    }
    
}
