package p2p.server;

/**
 * Created by Sebi on 10-Dec-17.
 */
public class ConnectionInfo {
    private String ip;
    private Integer port;
    private String name;

    public ConnectionInfo() {
    }

    public ConnectionInfo(String ip, Integer port, String name) {
        this.ip = ip;
        this.port = port;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ConnectionInfo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                '}';
    }
}
