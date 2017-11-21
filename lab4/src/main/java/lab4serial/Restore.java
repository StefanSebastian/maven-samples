package lab4serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class Restore {

    public static void main(String[] args) {
        try {
            String text = new String(Files.readAllBytes(Paths.get("E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4serial\\input.txt")));
            System.out.println(text.length());

            String destination = "E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4serial\\output4.txt";

            ParseToPersons.parse(text, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
