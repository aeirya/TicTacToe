package xoxo.server;

import xoxo.net.request.Request;
import xoxo.server.net.INetwork;
import xoxo.server.net.Network;

public class GameServer implements IRequestHandler {

    private final INetwork net;
    private final IRequestHandler core;

    public GameServer(int port) {
        core = new RequestHandler();
        net = new Network(port, this);
    }

    public void run() {
        net.start();
    }

    @Override
    public void handle(Request request) {
        core.handle(request);
    }
}
