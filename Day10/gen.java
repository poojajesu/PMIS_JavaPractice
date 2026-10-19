package Day10;



  class Box<T>{
    private T item;
    void set(T item){
        this.item = item;
    }
  }
public class gen{
    public static void main(String args[]){
        Box<String> box = new Box<>();
        box.set("heloo");

    }
    
}
