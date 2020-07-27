package com.bubble.xoxo.server.net;

import com.bubble.xoxo.net.connection.NetworkListener;

public class Network implements INetwork {

    private final ClientManager clients;
    private final NetworkListener listener;

    public Network(int port) {
        clients = new ClientManager();
        listener = new NetworkListener(this, port);
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
        decode(data);
    }

    private void decode(byte[] data) {
        final String string = new String(data);
        System.out.println(string);
    }
}