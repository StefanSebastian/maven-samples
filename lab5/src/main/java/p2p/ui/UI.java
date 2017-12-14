package p2p.ui;

import p2p.server.ConnectionInfo;
import p2p.server.IP2PServer;
import p2p.server.P2PException;
import p2p.server.helloProtocol.Message;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class UI {
    private IP2PServer server;
    private Scanner reader;

    public UI(IP2PServer server) {
        this.server = server;
        reader = new Scanner(System.in);
    }

    public void runMenu(){
        while (true){
            printMenu();
            String opt = reader.nextLine();
            if (opt.equals("1")){
                initializeConnection();
            } else if (opt.equals("2")){
                seeAllConnections();
            } else if (opt.equals("3")){
                sendMessage();
            } else if (opt.equals("4")){
                closeConnection();
            } else if (opt.equals("5")){
                closeAllConnections();
            } else if (opt.equals("6")){
                closeAllConnections();
                break;
            }
        }
    }

    private void printMenu(){
        System.out.println("1.Initialize connection");
        System.out.println("2.See all connections");
        System.out.println("3.Send message");
        System.out.println("4.Close connection");
        System.out.println("5.Close all connections");
        System.out.println("6.Exit");
    }

    private void initializeConnection(){
        System.out.println("Ip: ");
        String ip = reader.nextLine();
        System.out.println("Port: ");
        String port = reader.nextLine();

        try {
            ConnectionInfo connectionInfo = new ConnectionInfo();
            connectionInfo.setIp(ip);
            connectionInfo.setPort(Integer.parseInt(port));
            server.connectTo(connectionInfo);
        } catch (P2PException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Invalid input");
        }
    }

    private void seeAllConnections(){
        List<ConnectionInfo> connectionInfoList;
        try {
            connectionInfoList = server.getOpenConnections();
            for (ConnectionInfo connectionInfo : connectionInfoList){
                System.out.println(connectionInfo);
            }
        } catch (P2PException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendMessage(){
        System.out.println("Destination: ");
        String dest = reader.nextLine();
        System.out.println("Text: ");
        String text = reader.nextLine();

        try {
            server.sendMessage(new Message(dest, text));
        } catch (P2PException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeConnection(){
        System.out.println("Peer name: ");
        String peer = reader.nextLine();

        try {
            server.closeConnection(peer);
        } catch (P2PException e) {
            System.out.println(e.getMessage());
        }
    }

    private void closeAllConnections(){
        try {
            server.closeAllConnections();
        } catch (P2PException e) {
            System.out.println(e.getMessage());
        }
    }
}
