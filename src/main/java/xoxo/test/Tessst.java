package xoxo.test;

import java.io.IOException;

import xoxo.client.GameClient;
import xoxo.game.Game;
import xoxo.server.ServerMain;

public class Tessst {
    private static final String IP = "localhost";
    private static final int PORT = 8000;


    public static int read() {
        try {
            int a = System.in.read() - 48;
            System.in.read();
            return a;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
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
        boolean isAlive = true;
        while (isAlive) {
            int a = read();
            System.out.println("it is: " + a);
            System.out.println("looping");
            switch(a) {
                case 0:
                isAlive = false;
                break;
                case 1:
                c.get().play(read(), read());
                break;
                case 2:
                Game game = c.get().getUpdate();
                if (game == null) System.err.println("no update");
                else {
                    System.out.println(game.toString());
                }
                break;
                default:
                continue;
            }
        }
    }
}