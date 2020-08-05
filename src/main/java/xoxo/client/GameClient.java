package xoxo.client;

import java.util.logging.Logger;

import xoxo.client.net.Network;
import xoxo.net.request.user.LoginRequest;

public class GameClient {

    private final Network network;
    
    public GameClient(String ip, int port) {
        network = new Network(ip, port);
    }

    public void run() {
        network.connect();
        test2();
    }

    public void test2() {
        network.request(new LoginRequest("aeirya", "1234"));
        network.request(new LoginRequest("arya", "1234"));
        final String msg1 = network.getResponse().body;
        Logger.getGlobal().info(() -> msg1 + "\n" + network.getResponse().body);
    }
}
