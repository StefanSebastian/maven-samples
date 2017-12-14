package p2p.server.heartbeatProtocol;

import p2p.server.helloProtocol.ConnectionData;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 14-Dec-17.
 *
 * Checks hearbeat of each connection
 */
public class ConnectionsChecker implements Runnable {
    private ConcurrentHashMap<String, ConnectionData> connections;
    private Long timeout;

    public ConnectionsChecker(ConcurrentHashMap<String, ConnectionData> connections, Long timeout) {
        this.connections = connections;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException ignored) {
            }

            for (String key : connections.keySet()){
                ConnectionData connectionData = connections.get(key);

                Date currentDate = new Date();
                if (currentDate.getTime() - connectionData.getHeartbeat().getTime() > timeout){
                    System.out.println("Connection closed for " + key);
                    try {
                        connectionData.getSocket().close();
                        connections.remove(key);
                    } catch (IOException ignored) {
                    }
                }
            }
        }
    }
}
