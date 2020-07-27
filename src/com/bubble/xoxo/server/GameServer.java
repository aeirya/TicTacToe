package com.bubble.xoxo.server;

import com.bubble.xoxo.server.net.INetwork;
import com.bubble.xoxo.server.net.Network;

public class GameServer {

    private final INetwork net;

    public GameServer(int port) {
        net = new Network(port);
    }

    public void run() {
        net.start();
    }
}
