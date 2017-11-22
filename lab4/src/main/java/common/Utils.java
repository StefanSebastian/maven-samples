package common;

import common.Person;
import common.Regex;

/**
 * Created by Sebi on 21-Nov-17.
 */
public class Utils {
    public static Person parsePerson(String pers){
        String[] attrib = pers.split("~");
        if (!attrib[0].matches(Regex.name)){
            System.out.println("Invalid name " + attrib[0]);
            System.out.println("for " + pers);
            return null;
        }
        if (!attrib[1].matches(Regex.name)){
            System.out.println("Invalid name " + attrib[1]);
            System.out.println("for " + pers);
            return null;
        }
        if (!attrib[2].matches(Regex.name)){
            System.out.println("Invalid name " + attrib[2]);
            System.out.println("for " + pers);
            return null;
        }
        if (!attrib[3].matches(Regex.cnp)){
            System.out.println("Invalid cnp " + attrib[3]);
            System.out.println("for " + pers);
            return null;
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
            return null;
        }

        return new Person(attrib[0], attrib[1], attrib[2], attrib[3], attrib[4]);
    }
}
