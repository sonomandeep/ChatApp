package it.mandeep.libreria.network;

public class Adress {

    private String IP;
    private int port;

    public Adress(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
