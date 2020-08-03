package xoxo.client.net;

import java.net.Socket;

import xoxo.net.connection.IDataReceiver;
import xoxo.net.connection.NetworkConnection;

public class Server implements IDataReceiver {
    private final Socket socket;
    private final NetworkConnection connection;
    private final INetworkService netService;

    public Server(INetworkService networkService, Socket socket) {
        this.socket = socket;
        this.netService = networkService;
        this.connection = new NetworkConnection(this, socket);
    }

    @Override
    public void receive(byte[] data) {
        netService.receive(data);
    }

    public void send(byte[] data) {
        connection.send(data);
    }
}