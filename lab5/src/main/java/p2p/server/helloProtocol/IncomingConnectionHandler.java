package p2p.server.helloProtocol;

import p2p.server.P2PException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class IncomingConnectionHandler implements Runnable{
    private ServerSocket serverSocket;
    private String name;
    private ConcurrentHashMap<String, Socket> connections;
    private ConcurrentHashMap<String, MessageReader> readers;
    private BlockingQueue<Message> receivedMessages;
    private ConcurrentHashMap<String, PrintWriter> writers;
    private ExecutorService executorService;


    public IncomingConnectionHandler(ServerSocket serverSocket,
                                     String name,
                                     ConcurrentHashMap<String, Socket> connections,
                                     ConcurrentHashMap<String, MessageReader> readers,
                                     BlockingQueue<Message> receivedMessages, ConcurrentHashMap<String, PrintWriter> writers, ExecutorService executorService) {
        this.serverSocket = serverSocket;
        this.name = name;
        this.connections = connections;
        this.readers = readers;
        this.receivedMessages = receivedMessages;
        this.writers = writers;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        while(true){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                receiveHelloProtocol(socket);
            } catch (IOException | P2PException e) {
                System.out.println(e.getMessage());
                if (socket != null){
                    try {
                        socket.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }

    private void receiveHelloProtocol(Socket socket) throws P2PException{
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg = in.readLine();

            String sender = socket.getInetAddress().getCanonicalHostName() + " " + socket.getPort();
            System.out.println("Received connection from " + sender);

            String[] msgArr = msg.split(" ");
            if (msgArr.length != 2 || !msgArr[0].equals("!hello")){
                throw new P2PException("Invalid protocol " + sender);
            }

            if (connections.containsKey(msgArr[1])){
                throw new P2PException("Connection already set for " + sender);
            }

            connections.put(msgArr[1], socket);
            writers.put(msgArr[1], out);

            MessageReader reader = new MessageReader(msgArr[1], connections, in, receivedMessages);
            readers.put(msgArr[1], reader);
            executorService.submit(reader);

            System.out.println("Writing accept message");
            out.println("!ack " + name);
            out.flush();
        } catch (IOException | P2PException e){
            throw new P2PException(e.getMessage());
        }
    }
}
