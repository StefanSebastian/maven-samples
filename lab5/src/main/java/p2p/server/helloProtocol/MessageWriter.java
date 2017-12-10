package p2p.server.helloProtocol;

import p2p.server.P2PException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class MessageWriter implements Runnable {

    private ConcurrentHashMap<String, ConnectionData> connections;
    private BlockingQueue<Message> messages;

    public MessageWriter(ConcurrentHashMap<String, ConnectionData> connections,
                         BlockingQueue<Message> messages) {
        this.connections = connections;
        this.messages = messages;
    }

    @Override
    public void run() {
        while(true){
            writeMessages();
        }
    }

    private void writeMessages(){
        try {
            Message message = messages.take();
            Socket socket = connections.get(message.getName()).getSocket();

            if (socket == null){
                throw new P2PException("Invalid destination for " + message);
            }

            if (socket.isClosed()){
                throw new P2PException("Connection closed for " + message);
            }

            PrintWriter out = connections.get(message.getName()).getWriter();
            out.println(message.getText());
            out.flush();

        } catch (InterruptedException | P2PException e) {
            System.out.println(e.getMessage());
        }
    }
}
