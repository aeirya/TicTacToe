package com.bubble.xoxo.server.net;

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
}