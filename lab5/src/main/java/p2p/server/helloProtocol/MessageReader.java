package p2p.server.helloProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class MessageReader implements Runnable {
    private ConcurrentHashMap<String, Socket> connections;
    private BlockingQueue<Message> messages;

    public MessageReader(ConcurrentHashMap<String, Socket> connections,
                         BlockingQueue<Message> messages) {
        this.connections = connections;
        this.messages = messages;
    }

    @Override
    public void run() {
        // polls sockets for messages
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getCurrentMessages();
        }
    }

    private void getCurrentMessages(){
        // iterate all connections ; in case new ones appear
        for (String key : connections.keySet()){
            // if connection is opened
            Socket connection = connections.get(key);
            if (!connection.isClosed()){
                // read all messages on socket
                try{
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;
                    while (connection.getInputStream().available() != 0){
                        line = in.readLine();
                        System.out.println(line + " from " + key);
                        messages.add(new Message(key, line));
                    }
                } catch (IOException ignored){
                }
            }
        }
    }
}
