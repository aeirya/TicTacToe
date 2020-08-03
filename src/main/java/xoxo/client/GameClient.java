package xoxo.client;

import xoxo.client.net.Network;
import xoxo.net.request.user.LoginRequest;

public class GameClient {

    private final Network network;
    
    public GameClient(String ip, int port) {
        network = new Network(ip, port);
    }

    public void run() {
        network.connect();
    }

    public void test2() {
        network.request(new LoginRequest("aeirya", "1234"));
    }
}
