package lab4parallel;

import java.io.IOException;

/**
 * Created by Sebi on 21-Nov-17.
 */
public class Main {
    public static void main(String[] args) {
        String inputPath = "E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4serial\\input.txt";
        String outputPath = "E:\\Info\\anu3\\princip\\git\\maven-samples\\lab4\\src\\main\\resources\\lab4serial\\output4.txt";

        ProducerConsumer producerConsumer = new ProducerConsumer(inputPath, outputPath);
        Thread producer = new Thread(() -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
