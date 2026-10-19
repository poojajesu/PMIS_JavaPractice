package Day9;

public class Thread1 extends Thread {
    public void run(){
        System.out.println("Thread is running...");

    }

    public static void main(String args[]){

        Thread1 t = new Thread1();
        Thread t1 = new Thread(t);
        t1.start();
    }
}