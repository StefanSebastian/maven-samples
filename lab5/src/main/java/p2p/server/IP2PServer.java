package p2p.server;

import java.util.List;

/**
 * Created by Sebi on 10-Dec-17.
 */
public interface IP2PServer {
    void run() throws P2PException;


    /*
    Initialize a connection
     */
    void connectTo(ConnectionInfo info) throws P2PException;

    /*
    Get a list of open connections
     */
    List<ConnectionInfo> getOpenConnections() throws P2PException;
}
