package Day10;



class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    void display() {
        System.out.println("Key: " + key);
        System.out.println("Value: " + value);
    }
}

public class gen1 {
    public static void main(String[] args) {
        Pair<Integer, String> p = new Pair<>(1, "Java");
        p.display();
    }
}
