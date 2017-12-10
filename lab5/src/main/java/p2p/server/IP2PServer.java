package p2p.server;

import p2p.server.helloProtocol.Message;

import java.util.List;

/**
 * Created by Sebi on 10-Dec-17.
 */
public interface IP2PServer {
    /*
    Start the server
     */
    void run() throws P2PException;

    /*
    Initialize a connection
     */
    void connectTo(ConnectionInfo info) throws P2PException;

    /*
    Get a list of open connections
     */
    List<ConnectionInfo> getOpenConnections() throws P2PException;

    /*
    Send a message to a peer
     */
    void sendMessage(Message message) throws P2PException;
}
