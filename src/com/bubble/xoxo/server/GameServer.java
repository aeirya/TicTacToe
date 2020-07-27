package com.bubble.xoxo.server;

import com.bubble.xoxo.server.net.INetwork;
import com.bubble.xoxo.server.net.Network;

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
    public void handle(String request) {
        core.handle(request);
    }
}
