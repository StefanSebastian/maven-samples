package lab4;

import lab4.personParser.ParseToPersons;
import lab4.personParser.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class Restore {

    public static void main(String[] args) {
        try {
            String text = new String(Files.readAllBytes(Paths.get("E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4\\input.txt")));
            System.out.println(text.length());

            String destination = "E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4\\output4.txt";

            ParseToPersons.parse(text, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
