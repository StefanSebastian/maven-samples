package p2p.server.heartbeatProtocol;

import p2p.server.helloProtocol.ConnectionData;
import p2p.server.helloProtocol.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sebi on 14-Dec-17.
 *
 * Responsible for sending a !heartbeat message to all peers
 * Used to identify closed connections
 */
public class HeartbeatSender implements Runnable {
    private ConcurrentHashMap<String, ConnectionData> connections;
    private BlockingQueue<Message> toSendMessages;
    private Long pulse;

    public HeartbeatSender(ConcurrentHashMap<String, ConnectionData> connections,
                           BlockingQueue<Message> toSendMessages,
                           Long pulse) {
        this.connections = connections;
        this.toSendMessages = toSendMessages;
        this.pulse = pulse;
    }


    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(pulse);
            } catch (InterruptedException ignored) {
            }

            for (String peer : connections.keySet()) {
                toSendMessages.add(new Message(peer, "!heartbeat"));
            }
        }
    }
}
