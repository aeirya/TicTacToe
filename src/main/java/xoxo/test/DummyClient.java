package xoxo.test;

import java.io.IOException;
import java.util.logging.Logger;

import xoxo.client.GameClient;

public class DummyClient extends GameClient {
    private static final String IP = "localhost";
    private static final int PORT = 8000;

    String username;
    String password;

    public DummyClient(String username, String password) {
        super(IP, PORT);
        Logger.getGlobal().info("starting dummy client");
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        super.run();
        server.login(username, password);
        server.findMatch();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.play(0, 0);
    }
}