package lab4.personParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sebi on 14-Nov-17.
 */
public class ParseToPersons {
    public static void parse(String text, String destination){

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(destination)))){
            for (String pers : text.split("%")){
                String[] attrib = pers.split("~");
                if (!attrib[0].matches(Regex.name)){
                    System.out.println("Invalid name " + attrib[0]);
                    System.out.println("for " + pers);
                    continue;
                }
                if (!attrib[1].matches(Regex.name)){
                    System.out.println("Invalid name " + attrib[1]);
                    System.out.println("for " + pers);
                    continue;
                }
                if (!attrib[2].matches(Regex.name)){
                    System.out.println("Invalid name " + attrib[2]);
                    System.out.println("for " + pers);
                    continue;
                }
                if (!attrib[3].matches(Regex.cnp)){
                    System.out.println("Invalid cnp " + attrib[3]);
                    System.out.println("for " + pers);
                    continue;
                }

                String month = attrib[3].substring(3, 5);
                Integer monthConv = Integer.valueOf(month);
                if (monthConv < 1 || monthConv > 12){
                    System.out.println("Invalid cnp month " + attrib[3]);
                    System.out.println("for " + pers);
                }

                String day = attrib[3].substring(5, 7);
                Integer dayConv = Integer.valueOf(day);
                if (dayConv < 1 || dayConv > 31){
                    System.out.println("Invalid cnp day " + attrib[3]);
                    System.out.println("for " + pers);
                }

                if (!attrib[4].matches(Regex.email)){
                    System.out.println("Invalid name " + attrib[4]);
                    System.out.println("for " + pers);
                    continue;
                }

                Person person = new Person(attrib[0], attrib[1], attrib[2], attrib[3], attrib[4]);
                bufferedWriter.write(person.toString());
               // bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
