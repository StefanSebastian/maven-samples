package p2p.server.tasks;

import p2p.server.ConnectionInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class ConnectionTask implements Runnable{
    private ConcurrentHashMap<String, Socket> connections;
    private ConcurrentHashMap<String, BlockingQueue<String>> messages;
    private ConnectionInfo connectionInfo;
    private Socket socket;

    public ConnectionTask(ConcurrentHashMap<String, Socket> connections,
                          ConcurrentHashMap<String, BlockingQueue<String>> messages,
                          ConnectionInfo connectionInfo) {
        this.connections = connections;
        this.messages = messages;
        this.connectionInfo = connectionInfo;
    }

    @Override
    public void run() {
        try {
            // open socket
            socket = new Socket(connectionInfo.getIp(), connectionInfo.getPort());
            helloProtocol();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void helloProtocol(){
        // get input / output streams
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            String destination = socket.getInetAddress().getCanonicalHostName() + " " + socket.getPort();
            System.out.println("Writing hello message to " + destination);

            out.println("!hello " + connectionInfo.getName());
            out.flush();

            String reply = in.readLine();

            System.out.println("Received " + reply + " from " + destination);

            String[] replyArr = reply.split(" ");
            if (replyArr.length != 2){
                System.out.println("Invalid reply from " + destination);
                return;
            }
            if (!replyArr[0].equals("!ack")){
                System.out.println("Connection to " + connectionInfo.getIp() + " " + connectionInfo.getPort() + " refused");
                return;
            }

            System.out.println("Connection to " + connectionInfo.getIp() + " " + connectionInfo.getPort() + " accepted");

            connections.put(replyArr[1], socket);
            messages.put(replyArr[1], new LinkedBlockingQueue<>());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
