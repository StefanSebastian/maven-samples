package p2p.server;

import p2p.server.helloProtocol.*;

import java.io.IOException;
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

    private ConcurrentHashMap<String, Socket> connections;
    private BlockingQueue<Message> receivedMessages;
    private BlockingQueue<Message> toSendMessages;

    private ExecutorService executorService;

    public P2PServer(Integer port, String name){
        this.port = port;
        this.name = name;
        connections = new ConcurrentHashMap<>();
        receivedMessages = new LinkedBlockingQueue<>();
        toSendMessages = new LinkedBlockingQueue<>();
        executorService = Executors.newFixedThreadPool(8);
    }

    public void run() throws P2PException{
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new P2PException(e.getMessage());
        }
        executorService.submit(new IncomingConnectionHandler(serverSocket, name, connections));
        executorService.submit(new MessageReader(connections, receivedMessages));
        executorService.submit(new MessageWriter(connections, toSendMessages));
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
            Socket socket = connections.get(key);
            connectionInfos.add(new ConnectionInfo(socket.getInetAddress().getCanonicalHostName(), socket.getPort(), key));
        }
        return connectionInfos;
    }

    @Override
    public void sendMessage(Message message) throws P2PException {
        toSendMessages.add(message);
    }
}
