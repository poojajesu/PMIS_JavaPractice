package Day8;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class excep3 {
    static void readFile() throws IOException{
        FileReader file = new FileReader("data.txt");
        System.out.println("File Opened");
    }

    public static void main(String args[]){
        try{
            readFile();
        } catch(IOException e){
            System.out.println("Could not find the file");
        }
    }
    
}
