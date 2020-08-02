package xoxo.client.net;

import java.io.IOException;
import java.net.Socket;

public class Network implements INetwork {
    private final String ip;
    private final int port;
    private Server server;

    public Network(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        connect(ip, port);
    }

    private void connect(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            server = new Server(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] data) {
        server.send(data);
    }

    public void disconnect() {
        //
    }
}