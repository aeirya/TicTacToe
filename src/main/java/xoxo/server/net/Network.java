package xoxo.server.net;

import xoxo.net.connection.NetworkListener;
import xoxo.server.IRequestHandler;

public class Network implements INetwork {

    private final ClientManager clients;
    private final NetworkListener listener;
    private final IRequestHandler handler;

    public Network(int port, IRequestHandler handler) {
        clients = new ClientManager();
        listener = new NetworkListener(this, port);
        this.handler = handler;
    }

    public void accept(Client client) {
        clients.add(client);
        System.out.println("accepted");
    }

    @Override
    public void start() {
        listener.listen();
    }

    @Override
    public void request(Client client, byte[] data) {
        final String request = decode(data);
        handler.handle(request);
    }

    private String decode(byte[] data) {
        return new String(data);
    }
}