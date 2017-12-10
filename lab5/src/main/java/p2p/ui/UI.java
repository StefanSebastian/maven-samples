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
            } else {
                break;
            }
        }
    }

    private void printMenu(){
        System.out.println("1.Initialize connection");
        System.out.println("2.See all connections");
        System.out.println("3.Send message");
    }

    private void initializeConnection(){
        System.out.println("Ip: ");
        String ip = reader.nextLine();
        System.out.println("Port: ");
        String port = reader.nextLine();

        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setIp(ip);
        connectionInfo.setPort(Integer.parseInt(port));

        try {
            server.connectTo(connectionInfo);
        } catch (P2PException e) {
            System.out.println(e.getMessage());
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
}
