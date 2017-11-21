package lab4parallel;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import common.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Sebi on 21-Nov-17.
 */
public class ProducerConsumer {
    private String outputPath;
    private String inputPath;

    public ProducerConsumer(String inputPath, String outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    private Queue<Person> persons = new LinkedList<>();

    private volatile boolean consumerRunning = true;

    public void produce() throws InterruptedException, IOException{
        String inputText = new String(Files.readAllBytes(Paths.get(inputPath)));

        for (String personText : inputText.split("%")) {
            synchronized (this) {

                // wait for the person to be consumed
                while (persons.size() == 1) {
                    wait();
                }

                // get person
                Person person = Utils.parsePerson(personText);
                persons.add(person);

                // notify consumer
                notify();
            }
        }

        consumerRunning = false;

    }

    public void consume() throws InterruptedException,IOException{
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outputPath)))) {
            while (consumerRunning) {
                synchronized (this) {
                    // wait for the person to be produced
                    while (persons.size() == 0) {
                        wait();
                    }

                    // append to file if valid
                    Person person = persons.remove();
                    if (person != null) {
                        bufferedWriter.write(person.toString());
                    }

                    // notify producer
                    notify();
                }
            }
        }
    }
}
