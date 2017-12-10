package p2p.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class IncomingConnectionHandler implements Runnable{
    private ServerSocket serverSocket;
    private Socket socket;
    private String name;
    private ConcurrentHashMap<String, Socket> connections;
    private ConcurrentHashMap<String, BlockingQueue<String>> messages;


    public IncomingConnectionHandler(ServerSocket serverSocket,
                                     String name,
                                     ConcurrentHashMap<String, Socket> connections,
                                     ConcurrentHashMap<String, BlockingQueue<String>> messages) {
        this.serverSocket = serverSocket;
        this.name = name;
        this.connections = connections;
        this.messages = messages;
    }

    @Override
    public void run() {
        while(true){
            try {
                socket = serverSocket.accept();
                System.out.println("Received connection request");
                helloProtocol();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void helloProtocol(){
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            System.out.println("Reading from socket");
            String msg = in.readLine();
            System.out.println("Read " + msg);

            String[] msgArr = msg.split(" ");
            if (msgArr.length != 2 || !msgArr[0].equals("!hello")){
                System.out.println("Invalid protocol " + socket.getInetAddress().getCanonicalHostName() + " " + socket.getPort());
                return;
            }

            System.out.println("Received connection from " + socket.getInetAddress().getCanonicalHostName() + " " + socket.getPort());
            System.out.println("Writing accept message");

            connections.put(msgArr[1], socket);
            messages.put(msgArr[1], new LinkedBlockingQueue<>());

            out.println("!ack " + name);
            out.flush();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
