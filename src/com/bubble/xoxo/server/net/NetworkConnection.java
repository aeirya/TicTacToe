package com.bubble.xoxo.server.net;

import java.net.Socket;

public class NetworkConnection {

    private final Client client;
    private final ConnectionListener listener;
    private final ConnectionDispatcher dispatcher;
    private boolean isAlive;

    public NetworkConnection(Client client, Socket socket) {
        this.client = client;
        listener = new ConnectionListener(this, socket);
        dispatcher = new ConnectionDispatcher(socket);
    }

    public void start() {
        isAlive = true;
        connect();
        listen();
        holdOn();
    }

    private void holdOn() {
        while (isAlive) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                isAlive = false;
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        terminate();
    }

    private void connect() {
        dispatcher.connect();
    }

    private void listen() {
        listener.listen();
    }

    public void receive(byte[] data) {
        client.receive(data);
    }

    public void send(byte[] data) {
        dispatcher.send(data);
    }

    public void terminate() {
        listener.terminate();
    }
}