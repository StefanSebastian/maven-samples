package p2p;

import p2p.server.IP2PServer;
import p2p.server.P2PException;
import p2p.server.P2PServer;
import p2p.ui.UI;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        InputStream inputStream = Main.class.getResourceAsStream("connection.properties");
        try {
            properties.load(inputStream);
            Integer port = Integer.parseInt(properties.getProperty("port"));
            String name = properties.getProperty("name");

            System.out.println("Starting server at " + port + " " + name);
            IP2PServer server = new P2PServer(port, name);
            server.run();

            UI ui = new UI(server);
            ui.runMenu();
        } catch (IOException | P2PException e) {
            e.printStackTrace();
        }
    }
}
