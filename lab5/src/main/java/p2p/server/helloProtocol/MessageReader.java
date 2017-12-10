package p2p.server.helloProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class MessageReader implements Runnable {
    private String name;
    private ConcurrentHashMap<String, Socket> connections;
    private BufferedReader reader;
    private BlockingQueue<Message> messages;

    public MessageReader(String name,
                         ConcurrentHashMap<String, Socket> connections,
                         BufferedReader reader,
                         BlockingQueue<Message> messages) {
        this.name = name;
        this.connections = connections;
        this.reader = reader;
        this.messages = messages;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + " from " + name);
                if (line.equals("!bye")) {
                    connections.get(name).close();
                    connections.remove(name);
                } else {
                    messages.add(new Message(name, line));
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
