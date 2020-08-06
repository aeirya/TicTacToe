package xoxo.client;

import java.util.logging.Logger;

import xoxo.client.net.Network;
import xoxo.client.net.ServerAPI;
import xoxo.net.request.NetRequest;
import xoxo.net.request.Request;
import xoxo.net.request.user.LoginRequest;
import xoxo.net.request.user.LogoutRequest;
import xoxo.net.request.user.SignupRequest;

public class GameClient {

    private final Network network;
    protected final ServerAPI server;

    public GameClient(String ip, int port) {
        network = new Network(ip, port);
        server = new ServerAPI(network);
    }

    public void run() {
        network.connect();
        // test2();
        // test3();
        // test4();
        // test6();
    }

    public void test2() {
        network.request(new LoginRequest("aeirya", "1234"));
        network.request(new SignupRequest("arya", "1234"));
        network.request(new LoginRequest("arya", "1234"));
        final String msg1 = network.getResponse().body;
        final String msg2 = network.getResponse().body;
        final String msg3 = network.getResponse().body;
        Logger.getGlobal().info(() -> msg1 + "\n" + msg2 + "\n" + msg3);
    }

    public void test3() {
        System.out.println("going with test3");
        network.request(new LoginRequest("arya" , "1234"));
        network.request(new LoginRequest("arya" , "1234"));
        System.out.println("send two requestss");
        final String msg1 = network.getResponse().body;
        final String msg2 = network.getResponse().body;
        Logger.getGlobal().info(() -> msg1 + "\n" + msg2);
    }

    public void test4() {
        final String username = "arya";
        final String password = "1234";
        network.request(new SignupRequest(username, password));
        network.request(new LoginRequest(username, password));
        network.request(new LogoutRequest(username, password));
        network.request(new LoginRequest(username, password));
        while (true) {
            System.out.println(network.getResponse().body);
        }
    }

    public void test5() {
        network.request(new LoginRequest("arya", "1234"));
        network.request(new Request(NetRequest.START_GAME));
        
    }

    public void test6() {
        // server.login("arya", "1234");
        server.findMatch();
    }
}
