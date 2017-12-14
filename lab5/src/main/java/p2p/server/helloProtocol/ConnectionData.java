package p2p.server.helloProtocol;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class ConnectionData {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Date heartbeat;

    public ConnectionData() {
    }

    public ConnectionData(Socket socket, BufferedReader reader, PrintWriter writer, Date heartbeat) {
        this.socket = socket;
        this.reader = reader;
        this.writer = writer;
        this.heartbeat = heartbeat;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public Date getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Date heartbeat) {
        this.heartbeat = heartbeat;
    }
}
