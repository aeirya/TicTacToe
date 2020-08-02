package xoxo.server.net;

import xoxo.net.connection.NetworkListener;
import xoxo.net.connection.Protocol;
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
        final String requests = decode(data);
        for (String req : requests.split(Protocol.PROTO_END)) {
            handler.handle(req);
        }
    }

    private String decode(byte[] data) {
        return new String(data);
    }
}