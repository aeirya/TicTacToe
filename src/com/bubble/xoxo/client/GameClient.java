package com.bubble.xoxo.client;

import com.bubble.xoxo.client.net.INetwork;
import com.bubble.xoxo.client.net.Network;

public class GameClient {

    private final INetwork network;

    public GameClient(String ip, int port) {
        network = new Network(ip, port);
    }

    public void run() {
        network.connect();
        test();
    }

    private void test() {
        final String text = "this is a test";
        network.send(text.getBytes());
        final String text2 = "hmmm, another one";
        network.send(text2.getBytes());
    }
}
