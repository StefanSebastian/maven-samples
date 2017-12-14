package p2p.server;

import p2p.server.heartbeatProtocol.ConnectionsChecker;
import p2p.server.heartbeatProtocol.HeartbeatSender;
import p2p.server.helloProtocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class P2PServer implements IP2PServer {
    private Integer port;
    private String name;

    // used by the heartbeat protocol
    private Long pulse;
    private Long timeout;

    /*
    Information about each connections ; socket, input and output streams
     */
    private ConcurrentHashMap<String, ConnectionData> connections;

    // messages received from peers
    private BlockingQueue<Message> receivedMessages;

    // messages that need to be sent to peers
    private BlockingQueue<Message> toSendMessages;

    // executor service
    private ExecutorService executorService;

    public P2PServer(Integer port, String name, Long pulse, Long timeout){
        this.port = port;
        this.name = name;
        this.pulse = pulse;
        this.timeout = timeout;
        connections = new ConcurrentHashMap<>();
        receivedMessages = new LinkedBlockingQueue<>();
        toSendMessages = new LinkedBlockingQueue<>();
        executorService = Executors.newFixedThreadPool(20);
    }

    public void run() throws P2PException{
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new P2PException(e.getMessage());
        }
        executorService.submit(new IncomingConnectionHandler(serverSocket, name, connections));
        executorService.submit(new MessageWriter(connections, toSendMessages));
        executorService.submit(new MessageReader(connections, receivedMessages));
        executorService.submit(new HeartbeatSender(connections, toSendMessages, pulse));
        executorService.submit(new ConnectionsChecker(connections, timeout));
    }


    @Override
    public void connectTo(ConnectionInfo info) throws P2PException {
        info.setName(name);
        executorService.submit(new ConnectionTask(connections, info));
    }

    @Override
    public List<ConnectionInfo> getOpenConnections() throws P2PException {
        List<ConnectionInfo> connectionInfos = new ArrayList<>();
        for (String key : connections.keySet()){
            Socket socket = connections.get(key).getSocket();
            connectionInfos.add(new ConnectionInfo(socket.getInetAddress().getCanonicalHostName(), socket.getPort(), key));
        }
        return connectionInfos;
    }

    @Override
    public void sendMessage(Message message) throws P2PException {
        toSendMessages.add(message);
    }

    @Override
    public void closeConnection(String name) throws P2PException {
        toSendMessages.add(new Message(name, "!bye"));
    }

    @Override
    public void closeAllConnections() throws P2PException {
        for (String name : connections.keySet()){
            closeConnection(name);
        }
    }
}
