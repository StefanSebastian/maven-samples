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
    private ConcurrentHashMap<String, ConnectionData> connections;
    private BlockingQueue<Message> messages;

    public MessageReader(ConcurrentHashMap<String, ConnectionData> connections,
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
            Socket connection = connections.get(key).getSocket();
            if (!connection.isClosed()){
                // read all messages on socket
                try{
                    BufferedReader in = connections.get(key).getReader();

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
