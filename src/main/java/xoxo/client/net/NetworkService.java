package xoxo.client.net;

import java.io.IOException;
import java.net.Socket;

public class NetworkService implements INetworkService {
    private final String ip;
    private final int port;
    private Server server;

    public NetworkService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        connect(ip, port);
    }

    private void connect(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            server = new Server(this, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(byte[] data) {
        server.send(data);
    }

    public void send(String data) {
        send(data.getBytes());
    }

    

    public void disconnect() {
        //
    }

    @Override
    public void receive(byte[] data) {
        // TODO Auto-generated method stub

    }
}