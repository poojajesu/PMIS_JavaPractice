package Day7;


// Media Player - Runtime Polymorphism
import java.io.*;

class Result {

    public static String playMedia(String mediaType, String title) {
        if (mediaType.equals("Song")) {
            return "Playing song: " + title;
        } else if (mediaType.equals("Video")) {
            return "Playing video: " + title;
        } else if (mediaType.equals("Podcast")) {
            return "Playing podcast: " + title;
        } else {
            return "Unknown media type";
        }
    }
}

public class Q3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        BufferedWriter bufferedWriter = new BufferedWriter(
            new OutputStreamWriter(System.out)
        );

        String mediaType = bufferedReader.readLine();
        String title = bufferedReader.readLine();

        String result = Result.playMedia(mediaType, title);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
