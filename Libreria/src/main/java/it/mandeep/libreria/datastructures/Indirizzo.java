package it.mandeep.libreria.datastructures;

import java.io.Serializable;

public class Indirizzo implements Serializable {

    private static final long serialVersionUID = 428259781534407637L;
    private String ip;
    private int port;

    public Indirizzo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
