package xoxo.client.net;

import java.net.Socket;

import xoxo.net.connection.IDataReceiver;
import xoxo.net.connection.NetworkConnection;

public class Server implements IDataReceiver {
    private final Socket socket;
    private final NetworkConnection connection;

    public Server(Socket socket) {
        this.socket = socket;
        this.connection = new NetworkConnection(this, socket);
    }

    @Override
    public void receive(byte[] data) {
        //
    }

    public void send(byte[] data) {
        connection.send(data);
    }
}