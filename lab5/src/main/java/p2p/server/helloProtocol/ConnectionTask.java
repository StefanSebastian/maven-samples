package p2p.server.helloProtocol;

import p2p.server.ConnectionInfo;
import p2p.server.P2PException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 10-Dec-17.
 *
 * Sends a connection request to a given ip+port
 */
public class ConnectionTask implements Runnable{
    private ConcurrentHashMap<String, ConnectionData> connections;
    private ConnectionInfo connectionInfo;

    public ConnectionTask(ConcurrentHashMap<String, ConnectionData> connections,
                          ConnectionInfo connectionInfo) {
        this.connections = connections;
        this.connectionInfo = connectionInfo;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            // open socket
            checkExisting(connectionInfo.getIp(), connectionInfo.getPort());
            socket = new Socket(connectionInfo.getIp(), connectionInfo.getPort());
            sendHelloProtocol(socket);
        } catch (IOException | P2PException e) {
            // if any exceptions occur, close the socket
            System.out.println(e.getMessage());
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    /*
    Checks if the socket is already created
    Obs. if the connection already exists but on the other peer, it will be validated by name

    throws exception if connection already set
     */
    private void checkExisting(String ip, int port) throws P2PException{
        for (ConnectionData connectionData : connections.values()){

            String convIp = ip.equals("localhost") ? "127.0.0.1" : ip;

            if (connectionData.getSocket().getPort() == port &&
                    connectionData.getSocket().getInetAddress().getHostAddress().equals(convIp)){
                throw new P2PException("Connection already exists");
            }
        }
    }

    private void sendHelloProtocol(Socket socket) throws P2PException{
        // get input / output streams
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String destination = socket.getInetAddress().getCanonicalHostName() + " " + socket.getPort();
            System.out.println("Writing hello message to " + destination);

            out.println("!hello " + connectionInfo.getName());
            out.flush();

            String reply = in.readLine();

            System.out.println("Received " + reply + " from " + destination);

            if (reply.equals("duplicate")){
                throw new P2PException("Conneciton already set");
            }

            String[] replyArr = reply.split(" ");
            if (replyArr.length != 2){
                throw new P2PException("Invalid reply from " + destination);
            }
            if (!replyArr[0].equals("!ack")){
                throw new P2PException("Connection to " + connectionInfo.getIp() + " " + connectionInfo.getPort() + " refused");
            }

            if (connections.containsKey(replyArr[1])){
                throw new P2PException("Connection already set");
            }

            System.out.println("Connection to " + connectionInfo.getIp() + " " + connectionInfo.getPort() + " accepted");


            connections.put(replyArr[1], new ConnectionData(socket, in, out, new Date()));
        } catch (IOException | P2PException e){
            throw new P2PException(e.getMessage());
        }
    }
}
