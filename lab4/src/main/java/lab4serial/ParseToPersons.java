package lab4serial;

import common.Person;
import common.Regex;
import common.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class ParseToPersons {
    public static void parse(String text, String destination){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(destination)))){
            for (String pers : text.split("%")){
                Person person = Utils.parsePerson(pers);
                if (person == null){
                    continue;
                }
                bufferedWriter.write(person.toString());
               // bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
