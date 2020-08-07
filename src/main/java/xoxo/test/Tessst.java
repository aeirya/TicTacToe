package xoxo.test;

import java.io.IOException;

import xoxo.client.GameClient;
import xoxo.server.ServerMain;

public class Tessst {
    private static final String IP = "localhost";
    private static final int PORT = 8000;


    public static void read() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ServerMain.main(args);
        final GameClient c = new GameClient(IP, PORT);
        final GameClient c2 = new GameClient(IP, PORT);
        c.run();
        c2.run();
        c.get().login("arya", "1234");
        c2.get().login("ali", "a");
        c.get().findMatch();
        c2.get().findMatch();
        read();
        c.get().play(0, 1);
        c2.get().play(2,2);
        // System.out.println(c.get().getUpdate());
    }
}