package com.bubble.xoxo.net.connection;

import java.net.Socket;

public class NetworkConnection {

    private final IDataReceiver handler;
    private final ConnectionListener listener;
    private final ConnectionDispatcher dispatcher;
    private boolean isAlive;

    public NetworkConnection(IDataReceiver receiver, Socket socket) {
        this.handler = receiver;
        listener = new ConnectionListener(this, socket);
        dispatcher = new ConnectionDispatcher(socket);
    }

    public void start() {
        isAlive = true;
        connect();
        listen();
        // holdOn();
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
        handler.receive(data);
    }

    public void send(byte[] data) {
        dispatcher.send(data);
    }

    public void terminate() {
        listener.terminate();
    }
}