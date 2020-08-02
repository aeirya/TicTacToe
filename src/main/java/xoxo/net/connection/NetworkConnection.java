package xoxo.net.connection;

import java.net.Socket;

public class NetworkConnection implements IDataReceiver {

    private final IDataReceiver handler;
    private final IConnectionListener listener;
    private final IConnectionDispatcher dispatcher;
    private boolean isAlive;

    public NetworkConnection(IDataReceiver receiver, Socket socket) {
        this.handler = receiver;
        listener = new DataConnectionListener(this, socket);
        dispatcher = new DataConnectionDispacher(socket);
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