package com.bubble.xoxo.server.net;

import com.bubble.xoxo.net.connection.IDataReceiver;
import com.bubble.xoxo.net.connection.NetworkConnection;

import java.net.Socket;

public class Client implements IDataReceiver {
    private final NetworkConnection connection;
    private final INetwork server;

    public Client(Socket socket, INetwork server) {
        connection = new NetworkConnection(this, socket);
        this.server = server;
    }

    public void start() {
        connection.start();
    }

    public void receive(byte[] data) {
        server.request(this, data);
    }

    public void send(byte[] data) {
        connection.send(data);    
    }
}